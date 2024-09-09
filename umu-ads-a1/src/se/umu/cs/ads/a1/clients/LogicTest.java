package se.umu.cs.ads.a1.clients;

import se.umu.cs.ads.a1.interfaces.Messenger;
import se.umu.cs.ads.a1.types.Message;
import se.umu.cs.ads.a1.types.MessageId;
import se.umu.cs.ads.a1.types.Topic;
import se.umu.cs.ads.a1.types.Username;
import se.umu.cs.ads.a1.util.Util;

import java.util.Arrays;

public class LogicTest
{
  private final Messenger messenger;


  //----------------------------------------------------------
  public LogicTest (Messenger messenger)
  {
    this.messenger = messenger;
  }

  //----------------------------------------------------------
  // example logic test
	public void testStoreAndDelete (Message message) {
        Username username = message.getUsername();

        int nrMessagesBeforeStore = messenger.listMessages(username).length;
        messenger.store(message);
        int nrMessagesAfterStore = messenger.listMessages(username).length;
        if (nrMessagesAfterStore != (nrMessagesBeforeStore + 1))
            throw new IllegalStateException("❌ testStoreAndDelete(): store failure");
        else
            System.out.println("✅ testStoreAndDelete(): store success");

        int nrMessagesBeforeDelete = nrMessagesAfterStore;
        messenger.delete(message.getId());
        int nrMessagesAfterDelete = messenger.listMessages(username).length;
        if (nrMessagesAfterDelete != (nrMessagesBeforeDelete - 1))
            throw new IllegalStateException("❌ testStoreAndDelete(): delete failure");
        else
            System.out.println("✅ testStoreAndDelete(): delete success");
    }

    public void testStoreAndRetrieve(Message message){
        Username username = message.getUsername();
        int nrMessagesBeforeStore = messenger.listMessages(username).length;
        messenger.store(message);
        int nrMessagesAfterStore = messenger.listMessages(username).length;
        if (nrMessagesAfterStore != (nrMessagesBeforeStore + 1))
            throw new IllegalStateException("❌ testStoreAndRetrieve(): store failure");
        else
            System.out.println("✅ testStoreAndRetrieve(): store success");

        Message messages = messenger.retrieve(message.getId());
        if (messages == null)
            throw new IllegalStateException("❌ testStoreAndRetrieve(): retrieve failure");
        else
            System.out.println("✅ testStoreAndRetrieve(): retrieve success");
    }

    public void testStoreAndRetrieve(Message[] messages){
        Username username = messages[0].getUsername();
        int nrMessagesBeforeStore = messenger.listMessages(username).length;
        messenger.store(messages);
        int nrMessagesAfterStore = messenger.listMessages(username).length;
        if (nrMessagesAfterStore != (nrMessagesBeforeStore + messages.length))
            throw new IllegalStateException("❌ testStoreAndRetrieve(): store failure");
        else
            System.out.println("✅ testStoreAndRetrieve(): store success");

        Message[] retrievedMessages = messenger.retrieve(Arrays.stream(messages).map(Message::getId).toArray(MessageId[]::new));
        if (retrievedMessages.length != messages.length)
            throw new IllegalStateException("❌ testStoreAndRetrieve(): retrieve failure");
        else
            System.out.println("✅ testStoreAndRetrieve(): retrieve success");
    }

    public void testSubscribeAndUnsubscribe(Username username, Topic topic){
        int nrTopicsBeforeSubscribe = messenger.listTopics(username).length;
        messenger.subscribe(username, topic);
        int nrTopicsAfterSubscribe = messenger.listTopics(username).length;
        if (nrTopicsAfterSubscribe != (nrTopicsBeforeSubscribe + 1))
            throw new IllegalStateException("❌ testSubscribeAndUnsubscribe(): subscribe failure");
        else
            System.out.println("✅ testSubscribeAndUnsubscribe(): subscribe success");

        int nrTopicsBeforeUnsubscribe = nrTopicsAfterSubscribe;
        messenger.unsubscribe(username, topic);
        int nrTopicsAfterUnsubscribe = messenger.listTopics(username).length;
        if (nrTopicsAfterUnsubscribe != (nrTopicsBeforeUnsubscribe - 1))
            throw new IllegalStateException("❌ testSubscribeAndUnsubscribe(): unsubscribe failure");
        else
            System.out.println("✅ testSubscribeAndUnsubscribe(): unsubscribe success");
    }

}
