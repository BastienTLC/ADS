#!/usr/bin/env python3

import os
import sys
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib
from datetime import datetime

def get_most_recent_timestamp(measurement_dir, class_name):
    class_dir = os.path.join(measurement_dir, class_name)
    if not os.path.isdir(class_dir):
        print(f"No data found'{class_name}'")
        sys.exit(1)
    timestamps = [d for d in os.listdir(class_dir) if os.path.isdir(os.path.join(class_dir, d))]
    if not timestamps:
        print(f"No timestamp directories found in {class_dir}")
        sys.exit(1)
    most_recent_timestamp = max(timestamps)
    return os.path.join(class_dir, most_recent_timestamp)

def load_csv_data(measurement_path, function_name):
    csv_file = os.path.join(measurement_path, f"{function_name}.csv")
    if not os.path.isfile(csv_file):
        print(f"CSV file not found: {csv_file}")
        sys.exit(1)
    return pd.read_csv(csv_file)

def main():
    if len(sys.argv) < 2:
        print("arguments incorrect")
        sys.exit(1)

    implementations = sys.argv[1:]
    class_name_mapping = {
        'memory': 'InMemoryMessengerBackEnd',
        'rest': 'RestMessengerBackend',
        'grpc': 'GrpcMessengerBackend'
    }

    measurement_dir = '../measurements'

    for impl in implementations:
        if impl not in class_name_mapping:
            print(f"Unknown class '{impl}'. Allowed values are: memory, rest, grpc.")
            continue

        class_name = class_name_mapping[impl]
        measurement_path = get_most_recent_timestamp(measurement_dir, class_name)

        #load testMessageRetrieval
        data_message_retrieval = load_csv_data(measurement_path, 'testMessageRetrieval')

        #chart with sequentialTime and batchTime
        plt.figure(figsize=(8, 6))
        times = [data_message_retrieval['sequentialTime'].iloc[0], data_message_retrieval['batchTime'].iloc[0]]
        labels = ['Sequential Retrieval Time', 'Batch Retrieval Time']
        plt.bar(labels, times, color=['blue', 'green'])
        plt.ylabel('Time (ms)')
        plt.title(f"{class_name}\nNumber of Messages: {data_message_retrieval['nrMessages'].iloc[0]}, Payload Size: {data_message_retrieval['payloadSize'].iloc[0]} bytes")
        plt.grid(True, axis='y')
        plt.savefig(f"{class_name}_message_retrieval_times.png")
        plt.close()

        print(f"Generated bar chart for {class_name} message retrieval times.")

        #load testScalabilityWithMessageSize
        data_message_size = load_csv_data(measurement_path, 'testScalabilityWithMessageSize')

        # Plot retrievalTime vs payloadSize
        plt.figure(figsize=(10, 6))
        plt.plot(data_message_size['payloadSize'], data_message_size['retrievalTime'], marker='o')
        plt.xlabel('Payload Size (bytes)')
        plt.ylabel('Retrieval Time (ms)')
        plt.title(f"{class_name}\nNumber of Messages: {data_message_size['nrMessages'].iloc[0]}")
        plt.grid(True)
        plt.savefig(f"{class_name}_retrieval_time_vs_payload_size.png")
        plt.close()

        print(f"Generated graph for {class_name} retrieval time vs payload size.")

        #load testScalabilityWithNumberOfMessages
        data_num_messages = load_csv_data(measurement_path, 'testScalabilityWithNumberOfMessages')

        # Plot retrievalTime vs nrMessages
        plt.figure(figsize=(10, 6))
        plt.plot(data_num_messages['nrMessages'], data_num_messages['retrievalTime'], marker='o')
        plt.xlabel('Number of Messages')
        plt.ylabel('Retrieval Time (ms)')
        plt.title(f"{class_name}\nPayload Size: {data_num_messages['payloadSize'].iloc[0]} bytes")
        plt.grid(True)
        plt.savefig(f"{class_name}_retrieval_time_vs_num_messages.png")
        plt.close()

        print(f"Generated graph for {class_name} retrieval time vs number of messages.")

        #load test testScalabilityWithNumberOfMessagesBatchVsSeq
        data_batch_vs_seq = load_csv_data(measurement_path, 'testScalabilityWithNumberOfMessagesBatchVsSeq')

        # Plot sequential vs batch retrieval times
        plt.figure(figsize=(10, 6))
        plt.plot(data_batch_vs_seq['nrMessages'], data_batch_vs_seq['sequentialTime'], label='Sequential Retrieval Time', marker='o')
        plt.plot(data_batch_vs_seq['nrMessages'], data_batch_vs_seq['batchTime'], label='Batch Retrieval Time', marker='o')
        plt.xlabel('Number of Messages')
        plt.ylabel('Time (ms)')
        plt.title(f"{class_name}: Sequential vs Batch Retrieval Time\nPayload Size: {data_batch_vs_seq['payloadSize'].iloc[0]} bytes")
        plt.legend()
        plt.grid(True)
        plt.savefig(f"{class_name}_batch_vs_sequential_retrieval_time.png")
        plt.close()

        print(f"Generated graph for {class_name} sequential vs batch retrieval times.")

    print("finish")

if __name__ == '__main__':
    main()
