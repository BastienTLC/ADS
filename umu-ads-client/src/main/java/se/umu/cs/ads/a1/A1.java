package se.umu.cs.ads.a1;

import se.umu.cs.ads.a1.backend.GrpcMessengerBackend;
import se.umu.cs.ads.a1.backend.InMemoryMessengerBackEnd;
import se.umu.cs.ads.a1.backend.RestMessengerBackend;
import se.umu.cs.ads.a1.clients.LogicTest;
import se.umu.cs.ads.a1.clients.PerformanceTest;
import se.umu.cs.ads.a1.interfaces.Messenger;
import se.umu.cs.ads.a1.types.Topic;
import se.umu.cs.ads.a1.types.Username;
import se.umu.cs.ads.a1.util.Util;

public class A1
{
  //----------------------------------------------------------
  //----------------------------------------------------------
  private static Messenger loadMessenger (String fqn)
  {
    try
    {
      Class<?> _class = Class.forName(fqn);
      return (Messenger)_class.getDeclaredConstructor().newInstance();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw new IllegalStateException("unable to instantiate messenger class '" + fqn + "'");
    }
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  public static void main (String[] args)
  {
    try
    {
      // defaults to example messenger implementation
      final String[] arguments = Util.filterFlags(args);
      final String fqn = arguments.length > 0 ? arguments[0] : InMemoryMessengerBackEnd.class.getCanonicalName();
      // dynamic class loading for messenger instantiation
      Messenger messenger = loadMessenger(fqn);

      // test data
      Username username = new Username("testusername");

      // example utility function
      if (Util.containsFlag(args,"-topics"))
      {
        Topic[] topics = messenger.listTopics(username);
        System.out.println(topics.length + " topics");
        for (Topic t : topics)
          System.out.println("  " + t);
      }

      // example logic test
      if (Util.containsFlag(args,"-logic"))
      {
        LogicTest test = new LogicTest(messenger);
        Topic topic = new Topic("/test/logic");

        //System.out.println("testing store/delete logic...");
        test.testStoreAndDelete(Util.constructRandomMessage(username,topic,1024));
        test.testStoreAndDelete(Util.constructRandomMessages(username,topic,1024,10));
        test.testStoreAndRetrieve(Util.constructRandomMessage(username,topic,1024));
        test.testStoreAndRetrieve(Util.constructRandomMessages(username,topic,1024,10));
        test.testSubscribeAndUnsubscribe(username,topic);

        test.testClearAllMessages();
        test.testWildCard();

      }

      // example performance test
      if (Util.containsFlag(args,"-perf"))
      {
        for (int i=0; i<5; i++)
        {
          System.out.println("testing performance...");
          PerformanceTest test = new PerformanceTest(messenger);
          test.testMessageRetrieval(username,100000,1024);
          test.testScalabilityWithNumberOfMessages(username,5000,1024,5000,50000);
          test.testScalabilityWithMessageSize(username,1000,1000,5000,50000);
          test.testScalabilityWithNumberOfMessagesBatchVsSeq(username,1000,1024,5000,20000);
          test.testBandwidthAndThroughput(username, 50000,1024);
          test.close();
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
