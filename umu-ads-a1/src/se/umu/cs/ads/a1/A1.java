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
      final String fqn = arguments.length > 0 ? arguments[0] : GrpcMessengerBackend.class.getCanonicalName();
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
        PerformanceTest test = new PerformanceTest(messenger);

        System.out.println("testing retrieval performance...");
        test.testMessageRetrieval(username,1000,1024);
        //test.testMessageRetrieval(username,100000,1024);
        //test.testMessageRetrieval(username,10000000,1024);
        test.testScalabilityWithNumberOfMessages(username,1000,1024,1000,10000);
        test.testScalabilityWithMessageSize(username,1000,1000,1000,10000);
        test.testBandwidthAndThroughput(username, 1000,1024);
        test.testBandwidthAndThroughput(username, 100000,1024);

      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
