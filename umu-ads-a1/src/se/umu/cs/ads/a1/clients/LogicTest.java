package se.umu.cs.ads.a1.clients;

import se.umu.cs.ads.a1.interfaces.Messenger;
import se.umu.cs.ads.a1.types.*;
import se.umu.cs.ads.a1.util.Util;

import java.util.ArrayList;
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

    public void testStoreAndDelete (Message[] messages) {
        Username username = messages[0].getUsername();

        int nrMessagesBeforeStore = messenger.listMessages(username).length;
        messenger.store(messages);
        int nrMessagesAfterStore = messenger.listMessages(username).length;
        if (nrMessagesAfterStore != (nrMessagesBeforeStore + messages.length))
            throw new IllegalStateException("❌ testStoreAndDelete(): store failure");
        else
            System.out.println("✅ testStoreAndDelete(): store success");

        int nrMessagesBeforeDelete = nrMessagesAfterStore;
        messenger.delete(Arrays.stream(messages).map(Message::getId).toArray(MessageId[]::new));
        int nrMessagesAfterDelete = messenger.listMessages(username).length;
        if (nrMessagesAfterDelete != (nrMessagesBeforeDelete - messages.length))
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

    public void testWildCard(){
        Username username = new Username("testusername");
        Topic topic = new Topic("/test/logic1");
        Topic topic2 = new Topic("/test/logic2");
        Topic topic3 = new Topic("/test/logic*");

        Message message = Message.construct(username, topic, Content.EMPTY, Data.EMPTY);
        Message message2 = Message.construct(username, topic2, Content.EMPTY, Data.EMPTY);
        messenger.store(message);
        messenger.store(message2);
        messenger.subscribe(username, topic3);
        messenger.subscribe(username, topic3);
        int nrUsersInTopic1After = messenger.listSubscribers(topic).length;
        int nrUsersInTopic2After = messenger.listSubscribers(topic2).length;
        if (nrUsersInTopic1After != nrUsersInTopic2After){
            throw new IllegalStateException("❌ testWildCard(): subscribe failure");
        }
        else
            System.out.println("✅ testWildCard(): subscribe success");

        messenger.unsubscribe(username, topic3);
        int nrUsersInTopic1AfterUnsubscribe = messenger.listSubscribers(topic).length;
        int nrUsersInTopic2AfterUnsubscribe = messenger.listSubscribers(topic2).length;
        if (nrUsersInTopic1AfterUnsubscribe != nrUsersInTopic2AfterUnsubscribe){
            throw new IllegalStateException("❌ testWildCard(): unsubscribe failure");
        }
        else
            System.out.println("✅ testWildCard(): unsubscribe success");

    }

    void testClearAllMessagesOfUser(Username username){
        MessageId[] messages = messenger.listMessages(username);
        messenger.delete(messages);
        int nrMessagesAfterStore = messenger.listMessages(username).length;
        if (nrMessagesAfterStore != 0)
            throw new IllegalStateException("❌ testClearAllMessages(): delete failure");
        else
            System.out.println("✅ testClearAllMessages(): delete success");
    }

    void testClearAllMessageOfTopic(Topic topic){
        MessageId[] messages = messenger.listMessages(topic);
        messenger.delete(messages);
        int nrMessagesAfterStore = messenger.listMessages(topic).length;
        if (nrMessagesAfterStore != 0)
            throw new IllegalStateException("❌ testClearAllMessages(): delete failure");
        else
            System.out.println("✅ testClearAllMessages(): delete success");
    }

    void testClearAllUserMessage(){
      Username[] usernames = messenger.listUsers();
        for (Username username : usernames) {
            testClearAllMessagesOfUser(username);
        }
    }

    void testClearAllTopicMessage(){
        Topic[] topics = messenger.listTopics();
        for (Topic topic : topics) {
            testClearAllMessageOfTopic(topic);
        }
    }

    public void testClearAllMessages(){
        testClearAllUserMessage();
        testClearAllTopicMessage();
    }
}
