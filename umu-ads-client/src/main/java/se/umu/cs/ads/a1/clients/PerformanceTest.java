package se.umu.cs.ads.a1.clients;

import se.umu.cs.ads.a1.interfaces.Messenger;
import se.umu.cs.ads.a1.types.*;
import se.umu.cs.ads.a1.util.CsvMeasurementWriter;
import se.umu.cs.ads.a1.util.Util;

public class PerformanceTest {
    private final Messenger messenger;
    private final CsvMeasurementWriter csvWriter;

    public PerformanceTest(Messenger messenger) {
        this.messenger = messenger;
        String className = messenger.getClass().getSimpleName();
        this.csvWriter = new CsvMeasurementWriter(className);
    }

    public void testMessageRetrieval(Username username, int nrMessages, int payloadSize) {
        String topicName = "/test/performance";
        Topic topic = new Topic(topicName);

        // Clean up before test
        messenger.delete(messenger.listMessages(topic));

        // Generate fixed-size messages using Util
        Message[] messages = new Message[nrMessages];
        for (int i = 0; i < nrMessages; i++) {
            messages[i] = Util.constructFixedSizeMessage(username, topic, payloadSize);
        }
        messenger.store(messages);

        MessageId[] messageIds = messenger.listMessages(topic);
        if (messageIds.length != nrMessages)
            throw new IllegalStateException("testMessageRetrieval(): setup failure");

        // Sequential retrieval
        long t1 = System.currentTimeMillis();
        for (MessageId messageId : messageIds)
            messenger.retrieve(messageId);
        long t2 = System.currentTimeMillis();
        long sequentialTime = t2 - t1;

        System.out.println("Retrieved " + messageIds.length + " messages in " + sequentialTime + " ms (sequential)");

        // Batch retrieval
        long t3 = System.currentTimeMillis();
        messenger.retrieve(messageIds);
        long t4 = System.currentTimeMillis();
        long batchTime = t4 - t3;

        System.out.println("Retrieved " + messageIds.length + " messages in " + batchTime + " ms (batch)");

        // Clean up after test
        messenger.delete(messenger.listMessages(topic));
        if (messenger.listMessages(topic).length != 0)
            throw new IllegalStateException("testMessageRetrieval(): cleanup failure");

        // Write to CSV
        String functionName = "testMessageRetrieval";
        String[] headers = {"nrMessages", "payloadSize", "sequentialTime", "batchTime"};
        String[] values = {
                String.valueOf(nrMessages),
                String.valueOf(payloadSize),
                String.valueOf(sequentialTime),
                String.valueOf(batchTime)
        };
        csvWriter.writeMeasurement(functionName, headers, values);
    }

    public void testScalabilityWithNumberOfMessages(Username username, int initialNrMessages, int payloadSize, int increment, int maxMessages) {
        Topic topic = new Topic("/test/performance/scalability");
        String functionName = "testScalabilityWithNumberOfMessages";
        String[] headers = {"nrMessages", "payloadSize", "retrievalTime"};

        for (int nrMessages = initialNrMessages; nrMessages <= maxMessages; nrMessages += increment) {
            messenger.delete(messenger.listMessages(topic));

            Message[] messages = new Message[nrMessages];
            for (int i = 0; i < nrMessages; i++) {
                messages[i] = Util.constructFixedSizeMessage(username, topic, payloadSize);
            }
            messenger.store(messages);

            MessageId[] messageIds = messenger.listMessages(topic);

            long t1 = System.currentTimeMillis();
            messenger.retrieve(messageIds);
            long t2 = System.currentTimeMillis();
            long retrievalTime = t2 - t1;

            System.out.println("Retrieved " + nrMessages + " messages in " + retrievalTime + " ms.");

            String[] values = {
                    String.valueOf(nrMessages),
                    String.valueOf(payloadSize),
                    String.valueOf(retrievalTime)
            };
            csvWriter.writeMeasurement(functionName, headers, values);

            messenger.delete(messenger.listMessages(topic));
        }
    }

    public void testScalabilityWithNumberOfMessagesBatchVsSeq(Username username, int initialNrMessages, int payloadSize, int increment, int maxMessages) {
        Topic topic = new Topic("/test/performance/scalability");
        String functionName = "testScalabilityWithNumberOfMessagesBatchVsSeq";
        String[] headers = {"nrMessages", "payloadSize", "sequentialTime", "batchTime"};

        for (int nrMessages = initialNrMessages; nrMessages <= maxMessages; nrMessages += increment) {
            messenger.delete(messenger.listMessages(topic));

            Message[] messages = new Message[nrMessages];
            for (int i = 0; i < nrMessages; i++) {
                messages[i] = Util.constructFixedSizeMessage(username, topic, payloadSize);
            }
            messenger.store(messages);

            MessageId[] messageIds = messenger.listMessages(topic);

            long t1 = System.currentTimeMillis();
            for (MessageId messageId : messageIds) {
                messenger.retrieve(messageId);
            }
            long t2 = System.currentTimeMillis();
            long sequentialTime = t2 - t1;

            System.out.println("Retrieved " + nrMessages + " messages in " + sequentialTime + " ms (sequential)");

            long t3 = System.currentTimeMillis();
            messenger.retrieve(messageIds);
            long t4 = System.currentTimeMillis();
            long batchTime = t4 - t3;

            System.out.println("Retrieved " + nrMessages + " messages in " + batchTime + " ms (batch)");

            String[] values = {
                    String.valueOf(nrMessages),
                    String.valueOf(payloadSize),
                    String.valueOf(sequentialTime),
                    String.valueOf(batchTime)
            };
            csvWriter.writeMeasurement(functionName, headers, values);

            messenger.delete(messenger.listMessages(topic));
        }
    }

    public void testScalabilityWithMessageSize(Username username, int nrMessages, int initialPayloadSize, int increment, int maxPayloadSize) {
        Topic topic = new Topic("/test/performance/payload");
        String functionName = "testScalabilityWithMessageSize";
        String[] headers = {"nrMessages", "payloadSize", "retrievalTime"};

        for (int payloadSize = initialPayloadSize; payloadSize <= maxPayloadSize; payloadSize += increment) {
            messenger.delete(messenger.listMessages(topic));

            Message[] messages = new Message[nrMessages];
            for (int i = 0; i < nrMessages; i++) {
                messages[i] = Util.constructFixedSizeMessage(username, topic, payloadSize);
            }
            messenger.store(messages);

            MessageId[] messageIds = messenger.listMessages(topic);

            long t1 = System.currentTimeMillis();
            messenger.retrieve(messageIds);
            long t2 = System.currentTimeMillis();
            long retrievalTime = t2 - t1;

            System.out.println("Retrieved " + nrMessages + " messages with payload size " + payloadSize + " in " + retrievalTime + " ms.");

            String[] values = {
                    String.valueOf(nrMessages),
                    String.valueOf(payloadSize),
                    String.valueOf(retrievalTime)
            };
            csvWriter.writeMeasurement(functionName, headers, values);

            messenger.delete(messenger.listMessages(topic));
        }
    }

    public void testBandwidthAndThroughput(Username username, int nrMessages, int payloadSize) {
        Topic topic = new Topic("/test/performance/bandwidth");
        messenger.delete(messenger.listMessages(topic));

        Message[] messages = new Message[nrMessages];
        for (int i = 0; i < nrMessages; i++) {
            messages[i] = Util.constructFixedSizeMessage(username, topic, payloadSize);
        }

        long totalDataSize = (long) payloadSize * nrMessages;

        long t1 = System.currentTimeMillis();
        messenger.store(messages);
        long t2 = System.currentTimeMillis();

        double timeInSeconds = (t2 - t1) / 1000.0;
        double bandwidthInBytesPerSecond = totalDataSize / timeInSeconds;
        double bandwidthInMBpsSend = bandwidthInBytesPerSecond / 1_000_000;
        double throughputSend = nrMessages / timeInSeconds;

        System.out.println("-----------------------------------------");
        System.out.println("Sent " + totalDataSize + " bytes in " + timeInSeconds + " seconds. Bandwidth: " + bandwidthInMBpsSend + " MB/s.");
        System.out.println("Stored " + nrMessages + " messages in " + (t2 - t1) + " ms. Throughput: " + throughputSend + " messages/second.");

        MessageId[] messageIds = messenger.listMessages(topic);
        long t3 = System.currentTimeMillis();
        messenger.retrieve(messageIds);
        long t4 = System.currentTimeMillis();

        double retrievalTimeInSeconds = (t4 - t3) / 1000.0;
        double bandwidthReceivedInBytesPerSecond = totalDataSize / retrievalTimeInSeconds;
        double bandwidthReceivedInMBps = bandwidthReceivedInBytesPerSecond / 1_000_000;
        double throughputReceived = nrMessages / retrievalTimeInSeconds;

        System.out.println("-----------------------------------------");
        System.out.println("Received " + totalDataSize + " bytes in " + retrievalTimeInSeconds + " seconds. Bandwidth: " + bandwidthReceivedInMBps + " MB/s.");
        System.out.println("Retrieved " + nrMessages + " messages in " + (t4 - t3) + " ms. Throughput: " + throughputReceived + " messages/second.");

        messenger.delete(messenger.listMessages(topic));

        String functionName = "testBandwidthAndThroughput";
        String[] headers = {
                "nrMessages", "payloadSize", "sendTimeMs", "receiveTimeMs",
                "bandwidthSendMBps", "bandwidthReceiveMBps", "throughputSendNbMessagesPs", "throughputReceiveNbMessagesPs"
        };
        String[] values = {
                String.valueOf(nrMessages),
                String.valueOf(payloadSize),
                String.valueOf(t2 - t1),
                String.valueOf(t4 - t3),
                String.valueOf(bandwidthInMBpsSend),
                String.valueOf(bandwidthReceivedInMBps),
                String.valueOf(throughputSend),
                String.valueOf(throughputReceived)
        };
        csvWriter.writeMeasurement(functionName, headers, values);
    }

    public void close() {
        csvWriter.close();
    }
}
