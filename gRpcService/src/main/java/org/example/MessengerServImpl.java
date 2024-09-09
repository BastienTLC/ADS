package org.example;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.example.protobuf.grpc.MessengerGrpc;
import org.example.protobuf.java.MessengerOuterClass;

import java.util.*;

class MessengerServiceImpl extends MessengerGrpc.MessengerImplBase {

    private final HashMap<MessengerOuterClass.MessageId, MessengerOuterClass.Message> messageMap = new HashMap<>();
    private final HashMap<MessengerOuterClass.SubscriptionId, MessengerOuterClass.Subscription> subscriptionMap = new HashMap<>();
    private final HashMap<MessengerOuterClass.Topic, TopicBackEnd> topicMap = new HashMap<>();

    private TopicBackEnd getTopicBackEnd (MessengerOuterClass.Topic topic)
    {
        synchronized (this)
        {
            TopicBackEnd backend = topicMap.get(topic);
            if (backend == null)
            {
                backend = new TopicBackEnd();
                topicMap.put(topic,backend);
            }
            return backend;
        }
    }

    //----------------------------------------------------------

    public void storeMessage (MessengerOuterClass.Message message, StreamObserver<MessengerOuterClass.Empty> responseObserver)
    {
        synchronized (this)
        {
            MessengerOuterClass.Message messageWithTimestamp = message.toBuilder()
                    .setTimestamp(MessengerOuterClass.Timestamp.newBuilder()
                            .setValue(System.currentTimeMillis())
                            .build())
                    .build();
            messageMap.put(messageWithTimestamp.getId(), messageWithTimestamp);
            getTopicBackEnd(messageWithTimestamp.getTopic()).add(messageWithTimestamp.getId());
            System.out.println("Message stored: " + messageWithTimestamp);
            responseObserver.onNext(MessengerOuterClass.Empty.newBuilder().build());
            responseObserver.onCompleted();
        }
    }
    public void store (MessengerOuterClass.MessageBatch messagesBatch, StreamObserver<MessengerOuterClass.Empty> responseObserver)
    {
        synchronized (this)
        {
            for (MessengerOuterClass.Message message : messagesBatch.getMessagesList())
            {
                MessengerOuterClass.Message messageWithTimestamp = message.toBuilder()
                        .setTimestamp(MessengerOuterClass.Timestamp.newBuilder()
                                .setValue(System.currentTimeMillis())
                                .build())
                        .build();
                messageMap.put(messageWithTimestamp.getId(), messageWithTimestamp);
                getTopicBackEnd(messageWithTimestamp.getTopic()).add(messageWithTimestamp.getId());
            }
            responseObserver.onNext(MessengerOuterClass.Empty.newBuilder().build());
            responseObserver.onCompleted();

        }
    }

    public void retrieveMessage (MessengerOuterClass.MessageId message, StreamObserver<MessengerOuterClass.Message> responseObserver)
    {
        synchronized (this)
        {
            MessengerOuterClass.Message retrievedMessage = messageMap.get(message);
            if (retrievedMessage == null)
            {
                responseObserver.onError(new IllegalArgumentException("Message not found"));
            }
            else
            {
                responseObserver.onNext(retrievedMessage);
                responseObserver.onCompleted();
            }
        }
    }

    public void retrieveMessages (MessengerOuterClass.MessageIdBatch messages, StreamObserver<MessengerOuterClass.MessageBatch> responseObserver)
    {
        synchronized (this)
        {
            MessengerOuterClass.MessageBatch.Builder messageBatchBuilder = MessengerOuterClass.MessageBatch.newBuilder();
            for (MessengerOuterClass.MessageId message : messages.getIdsList())
            {
                MessengerOuterClass.Message retrievedMessage = messageMap.get(message);
                if (retrievedMessage != null)
                {
                    messageBatchBuilder.addMessages(retrievedMessage);
                }
            }
            responseObserver.onNext(messageBatchBuilder.build());
            responseObserver.onCompleted();
        }
    }

    public void deleteMessage (MessengerOuterClass.MessageId message, StreamObserver<MessengerOuterClass.Empty> responseObserver)
    {
        synchronized (this)
        {
            MessengerOuterClass.Message m = messageMap.remove(message);
            if (m != null)
            {
                getTopicBackEnd(m.getTopic()).remove(message);
            }
            responseObserver.onNext(MessengerOuterClass.Empty.newBuilder().build());
            responseObserver.onCompleted();
        }
    }

    public void deleteMessages (MessengerOuterClass.MessageIdBatch messages, StreamObserver<MessengerOuterClass.Empty> responseObserver)
    {
        synchronized (this)
        {
            for (MessengerOuterClass.MessageId message : messages.getIdsList())
            {
                MessengerOuterClass.Message m = messageMap.remove(message);
                if (m != null)
                {
                    getTopicBackEnd(m.getTopic()).remove(message);
                }
            }
            responseObserver.onNext(MessengerOuterClass.Empty.newBuilder().build());
            responseObserver.onCompleted();
        }

    }

    private void deleteSubscription(MessengerOuterClass.SubscriptionId subscription) {
        synchronized (this) {
            MessengerOuterClass.Subscription s = subscriptionMap.remove(subscription);
            if (s != null) {
                getTopicBackEnd(s.getTopic()).remove(subscription);
            }
        }
    }

    public void subscribe(MessengerOuterClass.SubscribeRequest request, StreamObserver<MessengerOuterClass.TopicBatch> responseObserver) {
        synchronized (this) {
            MessengerOuterClass.Username username = request.getUsername();
            MessengerOuterClass.Topic topic = request.getTopic();
            ArrayList<MessengerOuterClass.Topic> list = new ArrayList<>();

            for (MessengerOuterClass.Topic t : topicMap.keySet()) {
                if (t.equals(topic)) {
                    MessengerOuterClass.Subscription subscription = MessengerOuterClass.Subscription.newBuilder()
                            .setId(MessengerOuterClass.SubscriptionId.newBuilder().setValue(UUID.randomUUID().toString()).build())
                            .setTimestamp(MessengerOuterClass.Timestamp.newBuilder().setValue(System.currentTimeMillis()).build())
                            .setUsername(username)
                            .setTopic(topic)
                            .build();
                    store(subscription);
                    list.add(t);
                }
            }
            MessengerOuterClass.Topic[] topics = list.toArray(new MessengerOuterClass.Topic[list.size()]);
            responseObserver.onNext(MessengerOuterClass.TopicBatch.newBuilder().addAllTopics(Arrays.asList(topics)).build());
            responseObserver.onCompleted();
        }
    }

    public void unsubscribe(MessengerOuterClass.SubscribeRequest request, StreamObserver<MessengerOuterClass.TopicBatch> responseObserver) {
        synchronized (this) {
            MessengerOuterClass.Username username = request.getUsername();
            ArrayList<MessengerOuterClass.Topic> list = new ArrayList<>();

            for (MessengerOuterClass.Subscription subscription : subscriptionMap.values().toArray(new MessengerOuterClass.Subscription[subscriptionMap.size()])) {
                if (subscription.getUsername().equals(username)) {
                    deleteSubscription(subscription.getId());
                    list.add(subscription.getTopic());
                }
            }
            MessengerOuterClass.Topic[] topics = list.toArray(new MessengerOuterClass.Topic[list.size()]);
            responseObserver.onNext(MessengerOuterClass.TopicBatch.newBuilder().addAllTopics(Arrays.asList(topics)).build());
            responseObserver.onCompleted();
        }
    }

    public void listUser(MessengerOuterClass.Empty request,
                         StreamObserver<MessengerOuterClass.UsernameBatch> responseObserver) {
        synchronized (this) {
            HashSet<MessengerOuterClass.Username> usernames = new HashSet<>();
            for (MessengerOuterClass.Message message : messageMap.values()) {
                usernames.add(message.getUsername());
            }
            for (MessengerOuterClass.Subscription subscription : subscriptionMap.values()) {
                usernames.add(subscription.getUsername());
            }

            MessengerOuterClass.Username[] data = usernames.toArray(new MessengerOuterClass.Username[usernames.size()]);
            responseObserver.onNext(MessengerOuterClass.UsernameBatch.newBuilder().addAllUsernames(Arrays.asList(data)).build());
            responseObserver.onCompleted();
        }
    }
    
    public void listTopic(MessengerOuterClass.Empty request, StreamObserver<MessengerOuterClass.TopicBatch> responseObserver) {
        synchronized (this) {
            MessengerOuterClass.Topic[] data = topicMap.keySet().toArray(new MessengerOuterClass.Topic[topicMap.size()]);
            responseObserver.onNext(MessengerOuterClass.TopicBatch.newBuilder().addAllTopics(Arrays.asList(data)).build());
            responseObserver.onCompleted();
            
        }
    }

    public void listTopicByUser(MessengerOuterClass.Username username, StreamObserver<MessengerOuterClass.TopicBatch> responseObserver) {
        synchronized (this) {
            ArrayList<MessengerOuterClass.Topic> list = new ArrayList<>();
            for (MessengerOuterClass.Subscription subscription : subscriptionMap.values()) {
                if (subscription.getUsername().equals(username)) {
                    list.add(subscription.getTopic());
                }
            }
            MessengerOuterClass.Topic[] data = list.toArray(new MessengerOuterClass.Topic[list.size()]);
            responseObserver.onNext(MessengerOuterClass.TopicBatch.newBuilder().addAllTopics(Arrays.asList(data)).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void listSubscriber(MessengerOuterClass.Topic request, StreamObserver<MessengerOuterClass.UsernameBatch> responseObserver) {
        synchronized (this) {
            HashSet<MessengerOuterClass.Username> usernames = new HashSet<>();
            TopicBackEnd topicBackEnd = getTopicBackEnd(request);
            for (MessengerOuterClass.SubscriptionId subscription : topicBackEnd.subscriptions) {
                usernames.add(subscriptionMap.get(subscription).getUsername());
            }

            MessengerOuterClass.Username[] data = usernames.toArray(new MessengerOuterClass.Username[usernames.size()]);
            responseObserver.onNext(MessengerOuterClass.UsernameBatch.newBuilder().addAllUsernames(Arrays.asList(data)).build());
            responseObserver.onCompleted();
        }
    }

    public void listMessageByUser(MessengerOuterClass.Username username, StreamObserver<MessengerOuterClass.MessageIdBatch> responseObserver) {
        synchronized (this) {
            ArrayList<MessengerOuterClass.MessageId> list = new ArrayList<>();
            for (MessengerOuterClass.Message message : messageMap.values()) {
                if (message.getUsername().equals(username)) {
                    list.add(message.getId());
                }
            }
            MessengerOuterClass.MessageId[] data = list.toArray(new MessengerOuterClass.MessageId[list.size()]);
            responseObserver.onNext(MessengerOuterClass.MessageIdBatch.newBuilder().addAllIds(Arrays.asList(data)).build());
            responseObserver.onCompleted();
        }
    }

    public void listMessageByTopic(MessengerOuterClass.Topic topic, StreamObserver<MessengerOuterClass.MessageIdBatch> responseObserver) {
        synchronized (this) {
            ArrayList<MessengerOuterClass.MessageId> list = new ArrayList<>();
            for (MessengerOuterClass.Message message : messageMap.values()) {
                if (message.getTopic().equals(topic)) {
                    list.add(message.getId());
                }
            }
            MessengerOuterClass.MessageId[] data = list.toArray(new MessengerOuterClass.MessageId[list.size()]);
            responseObserver.onNext(MessengerOuterClass.MessageIdBatch.newBuilder().addAllIds(Arrays.asList(data)).build());
            responseObserver.onCompleted();
        }
    }

    //----------------------------------------------------------
    //----------------------------------------------------------
    // subscription interface
    //----------------------------------------------------------
    private void store (MessengerOuterClass.Subscription subscription)
    {
        synchronized (this)
        {
            subscriptionMap.put(subscription.getId(),subscription);
            getTopicBackEnd(subscription.getTopic()).add(subscription.getId());
        }
    }



    private static class TopicBackEnd
    {
        private final HashSet<MessengerOuterClass.MessageId> messages;
        private final HashSet<MessengerOuterClass.SubscriptionId> subscriptions;


        //----------------------------------------------------------
        public TopicBackEnd ()
        {
            this.messages = new HashSet<>();
            this.subscriptions = new HashSet<>();
        }

        //----------------------------------------------------------
        public void add (MessengerOuterClass.MessageId message)
        {
            messages.add(message);
        }

        //----------------------------------------------------------
        public void remove (MessengerOuterClass.MessageId message)
        {
            messages.remove(message);
        }

        //----------------------------------------------------------
        public void add (MessengerOuterClass.SubscriptionId subscription)
        {
            subscriptions.add(subscription);
        }

        //----------------------------------------------------------
        public void remove (MessengerOuterClass.SubscriptionId subscription)
        {
            subscriptions.remove(subscription);
        }
    }

}