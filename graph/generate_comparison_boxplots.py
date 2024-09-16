
import os
import sys
import pandas as pd
import matplotlib.pyplot as plt

def get_all_timestamps(measurement_dir, class_name):
    class_dir = os.path.join(measurement_dir, class_name)
    if not os.path.isdir(class_dir):
        print(f"No data found for class '{class_name}' in {measurement_dir}")
        sys.exit(1)
    timestamps = [d for d in os.listdir(class_dir) if os.path.isdir(os.path.join(class_dir, d))]
    if not timestamps:
        print(f"No timestamp directories found in {class_dir}")
        sys.exit(1)
    return [os.path.join(class_dir, ts) for ts in timestamps]

def load_csv_data_all_timestamps(measurement_paths, function_name):
    dfs = []
    for measurement_path in measurement_paths:
        csv_file = os.path.join(measurement_path, f"{function_name}.csv")
        if os.path.isfile(csv_file):
            df = pd.read_csv(csv_file)
            dfs.append(df)
        else:
            print(f"CSV file not found: {csv_file}")
    if not dfs:
        print(f"No data found for function '{function_name}'")
        sys.exit(1)
    combined_df = pd.concat(dfs, ignore_index=True)
    return combined_df

def main():
    measurement_dir = '../measurements'
    class_name_mapping = {
        'rest': 'RestMessengerBackend',
        'grpc': 'GrpcMessengerBackend'
    }

    implementations = ['rest', 'grpc']
    function_name = 'testBandwidthAndThroughput'
    metrics = ['bandwidthReceiveMBps', 'throughputSendNbMessagesPs', 'throughputReceiveNbMessagesPs']

    data = {}
    for impl in implementations:
        class_name = class_name_mapping[impl]
        measurement_paths = get_all_timestamps(measurement_dir, class_name)
        df = load_csv_data_all_timestamps(measurement_paths, function_name)
        data[impl] = df

    min_data_points = 2
    for metric in metrics:
        plt.figure(figsize=(8, 6))

        metric_data = []
        valid_implementations = []
        for impl in implementations:
            md = data[impl][metric].dropna()
            if len(md) >= min_data_points:
                metric_data.append(md)
                valid_implementations.append(impl)
            else:
                print(f"Not enough data for ")

        if not metric_data:
            print(f"No sufficient data for '{metric}' to generate a chart.")
            continue

        boxprops = dict(linestyle='-', linewidth=2, color='black')
        medianprops = dict(linestyle='-', linewidth=2.5, color='red')
        whiskerprops = dict(linewidth=2)
        capprops = dict(linewidth=2)
        flierprops = dict(marker='o', markerfacecolor='green', markersize=6, linestyle='none')

        plt.boxplot(metric_data, labels=valid_implementations, showfliers=True,
                    boxprops=boxprops, medianprops=medianprops,
                    whiskerprops=whiskerprops, capprops=capprops,
                    flierprops=flierprops)

        plt.title(f'Comparison of {metric}')
        plt.ylabel(metric)
        plt.grid(True, axis='y')

        for impl, md in zip(valid_implementations, metric_data):
            min_val = md.min()
            q1 = md.quantile(0.25)
            median = md.quantile(0.5)
            q3 = md.quantile(0.75)
            max_val = md.max()
            mean_val = md.mean()
            print(f"{impl} - {metric} Summary:")
            print(f"  Min: {min_val:.2f}")
            print(f"  Q1: {q1:.2f}")
            print(f"  Median: {median:.2f}")
            print(f"  Q3: {q3:.2f}")
            print(f"  Max: {max_val:.2f}")
            print(f"  Mean: {mean_val:.2f}")

        plt.savefig(f'boxplot_{metric}.png')
        plt.close()

        print(f"Generated boxplot for {metric}.")

    print("finish")

if __name__ == '__main__':
    main()
