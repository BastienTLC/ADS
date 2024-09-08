package se.umu.cs.ads.a1.clients;

import se.umu.cs.ads.a1.interfaces.Messenger;
import se.umu.cs.ads.a1.types.Message;
import se.umu.cs.ads.a1.types.Username;

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
	public void testStoreAndDelete (Message message)
	{
	  Username username = message.getUsername();

	  int nrMessagesBeforeStore = messenger.listMessages(username).length;
	  messenger.store(message);
    int nrMessagesAfterStore = messenger.listMessages(username).length;
    if (nrMessagesAfterStore != (nrMessagesBeforeStore + 1))
      throw new IllegalStateException("testStoreAndDelete(): store failure");

    int nrMessagesBeforeDelete = nrMessagesAfterStore;
    messenger.delete(message.getId());
    int nrMessagesAfterDelete = messenger.listMessages(username).length;
    if (nrMessagesAfterDelete != (nrMessagesBeforeDelete - 1))
      throw new IllegalStateException("testStoreAndDelete(): delete failure");
	}

    public void testStoreAndRetrieve(Message message){
        Username username = message.getUsername();
        int nrMessagesBeforeStore = messenger.listMessages(username).length;
        messenger.store(message);
        //int nrMessagesAfterStore = messenger.listMessages(username).length;
        //if (nrMessagesAfterStore != (nrMessagesBeforeStore + 1))
         //   throw new IllegalStateException("testStoreAndRetrieve(): store failure");

        Message messages = messenger.retrieve(message.getId());
        if (messages == null)
            throw new IllegalStateException("testStoreAndRetrieve(): retrieve failure");
    }
}
