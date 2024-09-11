package se.umu.cs.ads.a1.backend;

import com.google.protobuf.ByteString;
import se.umu.cs.ads.a1.interfaces.Messenger;
import se.umu.cs.ads.a1.types.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import se.umu.cs.ads.a1.protobuf.java.MessengerOuterClass;
import se.umu.cs.ads.a1.protobuf.grpc.MessengerGrpc;

import java.util.List;

import static java.util.Arrays.asList;

public class GrpcMessengerBackend  implements Messenger
{

    private final MessengerGrpc.MessengerBlockingStub blockingStub;



    public GrpcMessengerBackend() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .maxInboundMessageSize(1024 * 1024 * 1024)
                .build();
        this.blockingStub = MessengerGrpc.newBlockingStub(channel);
    }

    @Override
    public void store(Message message) {
        try {
            MessengerOuterClass.Message ProtoMessage = convertMessage(message);

            // Appel de la méthode storeMessage du serveur
            blockingStub.storeMessage(ProtoMessage);
            //System.out.println("Message successfully stored.");
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    @Override
    public void store(Message[] messages) {
        try {
            MessengerOuterClass.MessageBatch.Builder ProtoMessagesBuilder = MessengerOuterClass.MessageBatch.newBuilder();
            for (Message message : messages) {
                MessengerOuterClass.Message ProtoMessage = convertMessage(message);
                ProtoMessagesBuilder.addMessages(ProtoMessage);
            }
            MessengerOuterClass.MessageBatch ProtoMessages = ProtoMessagesBuilder.build();
            blockingStub.store(ProtoMessages);
            //System.out.println("Batch of messages successfully stored: " + messages.length + " messages");
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
        try {
            MessengerOuterClass.MessageIdBatch.Builder ProtoMessagesBuilder = MessengerOuterClass.MessageIdBatch.newBuilder();
            for (MessageId messageId : message) {
                MessengerOuterClass.MessageId ProtoMessageId = MessengerOuterClass.MessageId.newBuilder().setValue(messageId.getValue()).build();
                ProtoMessagesBuilder.addIds(ProtoMessageId);
            }
            MessengerOuterClass.MessageIdBatch ProtoMessages = ProtoMessagesBuilder.build();
            MessengerOuterClass.MessageBatch ProtoMessagesBatch = blockingStub.retrieveMessages(ProtoMessages);
            Message[] messages = new Message[ProtoMessagesBatch.getMessagesCount()];
            for (int i = 0; i < ProtoMessagesBatch.getMessagesCount(); i++) {
                messages[i] = convertRpcMessage(ProtoMessagesBatch.getMessages(i));
            }
            //System.out.println("Batch of messages successfully retrieved: " + messages.length + " messages");
            return messages;
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return null;
        }
    }

    @Override
    public void delete(MessageId message) {
        try {
            MessengerOuterClass.MessageId ProtoMessageId = MessengerOuterClass.MessageId.newBuilder().setValue(message.getValue()).build();
            blockingStub.deleteMessage(ProtoMessageId);
            //System.out.println("Message successfully deleted.");
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    @Override
    public void delete(MessageId[] messages) {
        try {
            MessengerOuterClass.MessageIdBatch.Builder ProtoMessagesBuilder = MessengerOuterClass.MessageIdBatch.newBuilder();
            for (MessageId message : messages) {
                MessengerOuterClass.MessageId ProtoMessageId = MessengerOuterClass.MessageId.newBuilder().setValue(message.getValue()).build();
                ProtoMessagesBuilder.addIds(ProtoMessageId);
            }
            MessengerOuterClass.MessageIdBatch ProtoMessages = ProtoMessagesBuilder.build();
            blockingStub.deleteMessages(ProtoMessages);
            //System.out.println("Batch of messages successfully deleted: " + messages.length + " messages");
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }

    @Override
    public Topic[] subscribe(Username username, Topic topic) {
        try {
            MessengerOuterClass.Username ProtoUsername = MessengerOuterClass.Username.newBuilder().setValue(username.getValue()).build();
            MessengerOuterClass.Topic ProtoTopic = MessengerOuterClass.Topic.newBuilder().setValue(topic.getValue()).build();
            MessengerOuterClass.SubscribeRequest ProtoRequest = MessengerOuterClass.SubscribeRequest.newBuilder().setUsername(ProtoUsername).setTopic(ProtoTopic).build();
            MessengerOuterClass.Topic[] ProtoTopics = blockingStub.subscribe(ProtoRequest).getTopicsList().toArray(new MessengerOuterClass.Topic[0]);
            Topic[] topics = new Topic[ProtoTopics.length];
            for (int i = 0; i < ProtoTopics.length; i++) {
                topics[i] = new Topic(ProtoTopics[i].getValue());
            }
            //System.out.println("User successfully subscribed to " + topics.length + " topics.");
            return topics;
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return null;
        }
    }

    @Override
    public Topic[] unsubscribe(Username username, Topic topic) {
        try {
            MessengerOuterClass.Username ProtoUsername = MessengerOuterClass.Username.newBuilder().setValue(username.getValue()).build();
            MessengerOuterClass.Topic ProtoTopic = MessengerOuterClass.Topic.newBuilder().setValue(topic.getValue()).build();
            MessengerOuterClass.SubscribeRequest ProtoRequest = MessengerOuterClass.SubscribeRequest.newBuilder().setUsername(ProtoUsername).setTopic(ProtoTopic).build();
            MessengerOuterClass.Topic[] ProtoTopics = blockingStub.unsubscribe(ProtoRequest).getTopicsList().toArray(new MessengerOuterClass.Topic[0]);
            Topic[] topics = new Topic[ProtoTopics.length];
            for (int i = 0; i < ProtoTopics.length; i++) {
                topics[i] = new Topic(ProtoTopics[i].getValue());
            }
            //System.out.println("User successfully unsubscribed from " + topics.length + " topics.");
            return topics;
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return null;
        }
    }

    @Override
    public Username[] listUsers() {
        try {
            MessengerOuterClass.Username[] ProtoUsernames = blockingStub.listUser(MessengerOuterClass.Empty.newBuilder().build()).getUsernamesList().toArray(new MessengerOuterClass.Username[0]);
            Username[] usernames = new Username[ProtoUsernames.length];
            for (int i = 0; i < ProtoUsernames.length; i++) {
                usernames[i] = new Username(ProtoUsernames[i].getValue());
            }
            //System.out.println("List of users successfully retrieved: " + usernames.length + " users");
            return usernames;
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return null;
        }
    }

    @Override
    public Topic[] listTopics() {
        try {
            MessengerOuterClass.Topic[] ProtoTopics = blockingStub.listTopic(MessengerOuterClass.Empty.newBuilder().build()).getTopicsList().toArray(new MessengerOuterClass.Topic[0]);
            Topic[] topics = new Topic[ProtoTopics.length];
            for (int i = 0; i < ProtoTopics.length; i++) {
                topics[i] = new Topic(ProtoTopics[i].getValue());
            }
            //System.out.println("List of topics successfully retrieved: " + topics.length + " topics");
            return topics;
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return null;
        }
    }

    @Override
    public Topic[] listTopics(Username username) {
        try {
            MessengerOuterClass.Username ProtoUsername = MessengerOuterClass.Username.newBuilder().setValue(username.getValue()).build();
            List<MessengerOuterClass.Topic> ProtoTopics = blockingStub.listTopicByUser(ProtoUsername).getTopicsList();
            Topic[] topics = new Topic[ProtoTopics.size()];
            for (int i = 0; i < ProtoTopics.size(); i++) {
                topics[i] = new Topic(ProtoTopics.get(i).getValue());
            }
            //System.out.println("List of topics for user " + username.getValue() + " successfully retrieved: " + topics.length + " topics");
            return topics;
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return null;
        }
    }

    @Override
    public Username[] listSubscribers(Topic topic) {
        try {
            MessengerOuterClass.Topic ProtoTopic = MessengerOuterClass.Topic.newBuilder().setValue(topic.getValue()).build();
            MessengerOuterClass.Username[] ProtoUsernames = blockingStub.listSubscriber(ProtoTopic).getUsernamesList().toArray(new MessengerOuterClass.Username[0]);
            Username[] usernames = new Username[ProtoUsernames.length];
            for (int i = 0; i < ProtoUsernames.length; i++) {
                usernames[i] = new Username(ProtoUsernames[i].getValue());
            }
            //System.out.println("List of subscribers for topic " + topic.getValue() + " successfully retrieved: " + usernames.length + " users");
            return usernames;
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return null;
        }
    }

    @Override
    public MessageId[] listMessages(Username username) {
        try {
            MessengerOuterClass.Username ProtoUsername = MessengerOuterClass.Username.newBuilder().setValue(username.getValue()).build();
            MessengerOuterClass.MessageId[] ProtoMessageIds = blockingStub.listMessageByUser(ProtoUsername).getIdsList().toArray(new MessengerOuterClass.MessageId[0]);
            MessageId[] messageIds = new MessageId[ProtoMessageIds.length];
            for (int i = 0; i < ProtoMessageIds.length; i++) {
                messageIds[i] = new MessageId(ProtoMessageIds[i].getValue());
            }
            //System.out.println("List of messages for user " + username.getValue() + " successfully retrieved: " + messageIds.length + " messages");
            return messageIds;
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return null;
        }
    }

    @Override
    public MessageId[] listMessages(Topic topic){
        try {
            MessengerOuterClass.Topic ProtoTopic = MessengerOuterClass.Topic.newBuilder().setValue(topic.getValue()).build();
            MessengerOuterClass.MessageId[] ProtoMessageIds = blockingStub.listMessageByTopic(ProtoTopic).getIdsList().toArray(new MessengerOuterClass.MessageId[0]);
            MessageId[] messageIds = new MessageId[ProtoMessageIds.length];
            for (int i = 0; i < ProtoMessageIds.length; i++) {
                messageIds[i] = new MessageId(ProtoMessageIds[i].getValue());
            }
            //System.out.println("List of messages for topic " + topic.getValue() + " successfully retrieved: " + messageIds.length + " messages");
            return messageIds;
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return null;
        }
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
