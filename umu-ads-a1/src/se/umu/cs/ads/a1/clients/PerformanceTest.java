package se.umu.cs.ads.a1.clients;

import se.umu.cs.ads.a1.interfaces.Messenger;
import se.umu.cs.ads.a1.types.Content;
import se.umu.cs.ads.a1.types.Data;
import se.umu.cs.ads.a1.types.Message;
import se.umu.cs.ads.a1.types.MessageId;
import se.umu.cs.ads.a1.types.Topic;
import se.umu.cs.ads.a1.types.Username;
import se.umu.cs.ads.a1.util.Util;

public class PerformanceTest
{
  private final Messenger messenger;


  //----------------------------------------------------------
  public PerformanceTest (Messenger messenger)
  {
    this.messenger = messenger;
  }

  //----------------------------------------------------------
  // example performance test




    public void testMessageRetrieval (Username username, int nrMessages, int payloadSize)
	{
	  Topic topic = new Topic("/test/performance");

	  messenger.delete(messenger.listMessages(topic));
    Content content = Content.EMPTY;
    Data data = Util.constructRandomData(payloadSize);
    Message[] messages = Message.construct(username,topic,content,data,nrMessages);
    messenger.store(messages);

	  MessageId[] messageIds = messenger.listMessages(topic);
    if (messageIds.length != nrMessages)
      throw new IllegalStateException("testMessageRetrieval(): setup failure");

    long t1 = System.currentTimeMillis();
	  for (MessageId message : messageIds)
	    messenger.retrieve(message);
    long t2 = System.currentTimeMillis();

    System.out.println("retrieved " + messageIds.length + " messages in " + (t2 - t1) + " ms (sequential)");

    long t3 = System.currentTimeMillis();
    messenger.retrieve(messageIds);
    long t4 = System.currentTimeMillis();

    System.out.println("retrieved " + messageIds.length + " messages in " + (t4 - t3) + " ms (batch)");

    messenger.delete(messenger.listMessages(topic));
    if (messenger.listMessages(topic).length != 0)
      throw new IllegalStateException("testMessageRetrieval(): cleanup failure");
	}

    public void testScalabilityWithNumberOfMessages(Username username, int initialNrMessages, int payloadSize, int increment, int maxMessages) {
        Topic topic = new Topic("/test/performance/scalability");

        for (int nrMessages = initialNrMessages; nrMessages <= maxMessages; nrMessages += increment) {
            messenger.delete(messenger.listMessages(topic));
            Content content = Content.EMPTY;
            Data data = Util.constructRandomData(payloadSize);
            Message[] messages = Message.construct(username, topic, content, data, nrMessages);
            messenger.store(messages);

            long t1 = System.currentTimeMillis();
            messenger.retrieve(messenger.listMessages(topic));
            long t2 = System.currentTimeMillis();

            System.out.println("Retrieved " + nrMessages + " messages in " + (t2 - t1) + " ms.");

            messenger.delete(messenger.listMessages(topic));
        }
    }

    public void testScalabilityWithMessageSize(Username username, int nrMessages, int initialPayloadSize, int increment, int maxPayloadSize) {
        Topic topic = new Topic("/test/performance/payload");

        for (int payloadSize = initialPayloadSize; payloadSize <= maxPayloadSize; payloadSize += increment) {
            messenger.delete(messenger.listMessages(topic));
            Content content = Content.EMPTY;
            Data data = Util.constructRandomData(payloadSize);
            Message[] messages = Message.construct(username, topic, content, data, nrMessages);
            messenger.store(messages);

            long t1 = System.currentTimeMillis();
            messenger.retrieve(messenger.listMessages(topic));
            long t2 = System.currentTimeMillis();

            System.out.println("Retrieved " + nrMessages + " messages with payload size " + payloadSize + " in " + (t2 - t1) + " ms.");

            messenger.delete(messenger.listMessages(topic));
        }
    }

    public void testBandwidthAndThroughput(Username username, int nrMessages, int payloadSize) {
        Topic topic = new Topic("/test/performance/bandwidth");

        messenger.delete(messenger.listMessages(topic));
        Content content = Content.EMPTY;
        Data data = Util.constructRandomData(payloadSize);
        Message[] messages = Message.construct(username, topic, content, data, nrMessages);


        long totalDataSize = (long) payloadSize * nrMessages;

        long t1 = System.currentTimeMillis();
        messenger.store(messages);
        long t2 = System.currentTimeMillis();

        double timeInSeconds = (t2 - t1) / 1000.0;

        double bandwidthInBytesPerSecond = totalDataSize / timeInSeconds;

        //convert byte to MB
        double bandwidthInMBpsSend = bandwidthInBytesPerSecond / 1000000;
        double throughputSens = nrMessages / timeInSeconds;

        System.out.println("-----------------------------------------");
        System.out.println("Sent " + totalDataSize + " bytes in " + timeInSeconds + " seconds. Bandwidth: " + bandwidthInMBpsSend + " MB/s.");
        System.out.println("store " + nrMessages + " messages in " + (t2 - t1) + " ms. Throughput: " + throughputSens + " messages/second.");

        long t3 = System.currentTimeMillis();
        messenger.retrieve(messenger.listMessages(topic));
        long t4 = System.currentTimeMillis();

        double retrievalTimeInSeconds = (t4 - t3) / 1000.0;
        double bandwidthReceivedInMBps = totalDataSize / retrievalTimeInSeconds / 1000000;
        double throughputReceived = nrMessages/ retrievalTimeInSeconds;
        System.out.println("-----------------------------------------");
        System.out.println("Received " + totalDataSize + " bytes in " + retrievalTimeInSeconds + " seconds. Bandwidth: " + bandwidthReceivedInMBps + " MB/s.");
        System.out.println("Retrieved " + nrMessages + " messages in " + (t4 - t3) + " ms. Throughput: " + throughputReceived + " messages/second.");

        messenger.delete(messenger.listMessages(topic));
    }





}
