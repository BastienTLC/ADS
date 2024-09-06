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
}
