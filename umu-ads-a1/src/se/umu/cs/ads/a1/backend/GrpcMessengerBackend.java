package se.umu.cs.ads.a1.backend;

import com.google.protobuf.ByteString;
import se.umu.cs.ads.a1.interfaces.Messenger;
import se.umu.cs.ads.a1.types.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import se.umu.cs.ads.a1.protobuf.java.MessengerOuterClass;
import se.umu.cs.ads.a1.protobuf.grpc.MessengerGrpc;

public class GrpcMessengerBackend  implements Messenger
{

    private final MessengerGrpc.MessengerBlockingStub blockingStub;



    public GrpcMessengerBackend() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        this.blockingStub = MessengerGrpc.newBlockingStub(channel);
    }

    @Override
    public void store(Message message) {
        try {
            MessengerOuterClass.Message ProtoMessage = convertMessage(message);

            // Appel de la méthode storeMessage du serveur
            blockingStub.storeMessage(ProtoMessage);
            System.out.println("Message successfully stored.");
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    @Override
    public void store(Message[] messages) {
        try {
            MessengerOuterClass.Message[] ProtoMessages = new MessengerOuterClass.Message[messages.length];
            for (int i = 0; i < messages.length; i++) {
                ProtoMessages[i] = convertMessage(messages[i]);
            }
            MessengerOuterClass.MessageBatch messageBatch = MessengerOuterClass.MessageBatch.newBuilder()
                    .addAllMessages(java.util.Arrays.asList(ProtoMessages))
                    .build();
            blockingStub.store(messageBatch);
            System.out.println("Batch of messages successfully stored.");
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    @Override
    public Message retrieve(MessageId message) {
        try {
            MessengerOuterClass.MessageId ProtoMessageId = MessengerOuterClass.MessageId.newBuilder().setValue(message.getValue()).build();
            MessengerOuterClass.Message ProtoMessage = blockingStub.retrieveMessage(ProtoMessageId);
            return convertRpcMessage(ProtoMessage);
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return null;
        }
    }

    @Override
    public Message[] retrieve(MessageId[] message) {
        return new Message[0];
    }

    @Override
    public void delete(MessageId message) {

    }

    @Override
    public void delete(MessageId[] messages) {

    }

    @Override
    public Topic[] subscribe(Username username, Topic topic) {
        return new Topic[0];
    }

    @Override
    public Topic[] unsubscribe(Username username, Topic topic) {
        return new Topic[0];
    }

    @Override
    public Username[] listUsers() {
        return new Username[0];
    }

    @Override
    public Topic[] listTopics() {
        return new Topic[0];
    }

    @Override
    public Topic[] listTopics(Username username) {
        return new Topic[0];
    }

    @Override
    public Username[] listSubscribers(Topic topic) {
        return new Username[0];
    }

    @Override
    public MessageId[] listMessages(Username username) {
        return new MessageId[0];
    }

    @Override
    public MessageId[] listMessages(Topic topic) {
        return new MessageId[0];
    }

    private MessengerOuterClass.Message convertMessage(Message message) {
        return MessengerOuterClass.Message.newBuilder()
                .setId(MessengerOuterClass.MessageId.newBuilder().setValue(message.getId().getValue()).build())
                .setTimestamp(MessengerOuterClass.Timestamp.newBuilder().setValue(message.getTimestamp().getValue()).build())
                .setUsername(MessengerOuterClass.Username.newBuilder().setValue(message.getUsername().getValue()).build())
                .setTopic(MessengerOuterClass.Topic.newBuilder().setValue(message.getTopic().getValue()).build())
                .setContent(MessengerOuterClass.Content.newBuilder().setValue(message.getContent().getValue()).build())
                .setData(MessengerOuterClass.Data.newBuilder().setValue(ByteString.copyFrom(message.getData().getValue())).build())
                .build();
    }

    private Message convertRpcMessage(MessengerOuterClass.Message message) {
        return new Message(new MessageId(message.getId().getValue()), new Timestamp(message.getTimestamp().getValue()), new Username(message.getUsername().getValue()), new Topic(message.getTopic().getValue()), new Content(message.getContent().getValue()), new Data(message.getData().getValue().toByteArray()));
    }
}
