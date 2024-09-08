package se.umu.cs.ads.a1.backend;

import se.umu.cs.ads.a1.interfaces.Messenger;
import se.umu.cs.ads.a1.types.Message;
import se.umu.cs.ads.a1.types.MessageId;
import se.umu.cs.ads.a1.types.Topic;
import se.umu.cs.ads.a1.types.Username;

public class GrpcMessengerBackend  implements Messenger
{

    @Override
    public void store(Message message) {

    }

    @Override
    public void store(Message[] messages) {

    }

    @Override
    public Message retrieve(MessageId message) {
        return null;
    }
}
