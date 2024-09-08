package org.example;
import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.example.protobuf.grpc.MessengerGrpc;
import org.example.protobuf.java.MessengerOuterClass;

import java.util.HashMap;
import java.util.HashSet;

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

    public void store (MessengerOuterClass.Message[] messages, StreamObserver<Empty> responseObserver)
    {
        synchronized (this)
        {
            for (MessengerOuterClass.Message message : messages)
            {
                MessengerOuterClass.Message messageWithTimestamp = message.toBuilder()
                        .setTimestamp(MessengerOuterClass.Timestamp.newBuilder()
                                .setValue(System.currentTimeMillis())
                                .build())
                        .build();
                messageMap.put(messageWithTimestamp.getId(), messageWithTimestamp);
                getTopicBackEnd(messageWithTimestamp.getTopic()).add(messageWithTimestamp.getId());
            }
            responseObserver.onNext(Empty.newBuilder().build());
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