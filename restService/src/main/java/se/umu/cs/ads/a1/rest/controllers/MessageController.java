package se.umu.cs.ads.a1.rest.controllers;

import org.springframework.web.bind.annotation.*;
import se.umu.cs.ads.a1.rest.interfaces.Messenger;
import se.umu.cs.ads.a1.rest.types.*;

import java.util.HashMap;
import java.util.*;

@RestController
@RequestMapping("/api/messenger")
public class MessageController implements Messenger {

    private final HashMap<MessageId, Message> messageMap;
    private final HashMap<SubscriptionId, Subscription> subscriptionMap;
    private final HashMap<Topic, TopicBackEnd> topicMap;

    public MessageController() {
        this.messageMap = new HashMap<>();
        this.subscriptionMap = new HashMap<>();
        this.topicMap = new HashMap<>();
    }

    private TopicBackEnd getTopicBackEnd(Topic topic) {
        synchronized (this) {
            TopicBackEnd backend = topicMap.get(topic);
            if (backend == null) {
                backend = new TopicBackEnd();
                topicMap.put(topic, backend);
            }
            return backend;
        }
    }

    @Override
    @PostMapping
    public void store(@RequestBody Message message) {
        synchronized (this) {
            message = message.copyWith(Timestamp.now());
            messageMap.put(message.getId(), message);
            getTopicBackEnd(message.getTopic()).add(message.getId());
            System.out.println("Message stored: " + message);
        }
    }

    @Override
    @PostMapping("/batch")
    public void store(@RequestBody Message[] messages) {
        synchronized (this) {
            for (Message message : messages) {
                message = message.copyWith(Timestamp.now());
                messageMap.put(message.getId(), message);
                getTopicBackEnd(message.getTopic()).add(message.getId());
            }
            System.out.println("Messages stored.");
        }
    }


    @GetMapping("/{id}")
        public Message retrieve(@PathVariable("id") MessageId messageId) {
        synchronized (this) {
            Message data = messageMap.get(messageId);

            if (data == null) {
                throw new IllegalArgumentException("unrecognized message: '" + messageId + "'");
            }
            return data;
        }
    }

    @Override
    @PostMapping("/batch/retrieve")
    public Message[] retrieve(@RequestBody MessageId[] messageIds) {
        synchronized (this) {
            Message[] data = new Message[messageIds.length];
            for (int i = 0; i < messageIds.length; i++) {
                Message d = messageMap.get(messageIds[i]);
                if (d == null) {
                    throw new IllegalArgumentException("Message not found: '" + messageIds[i] + "'");
                }
                data[i] = d;
            }
            return data;
        }
    }

    @Override
    @DeleteMapping("/delete")
    public void delete(@RequestBody MessageId message) {
        synchronized (this)
        {
            Message m = messageMap.remove(message);
            if (m != null)
                getTopicBackEnd(m.getTopic()).remove(message);
        }
    }

    @DeleteMapping("/batch/delete")
    public void delete(@RequestBody MessageId[] messages) {
        synchronized (this)
        {
            for (MessageId message : messages)
//              delete(message);
            {
                Message m = messageMap.remove(message);
                if (m != null)
                    getTopicBackEnd(m.getTopic()).remove(message);
            }
        }
    }


    @Override
    @PostMapping("/subscribe")
    public Topic[] subscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        synchronized (this) {
            Username username = subscriptionRequest.getUsername();
            Topic topic = subscriptionRequest.getTopic();
            ArrayList<Topic> list = new ArrayList<>();
            for (Topic t : topicMap.keySet()) {
                if (topic.match(t)) {
                    Subscription subscription = Subscription.construct(username, t);
                    store(subscription);
                    list.add(subscription.getTopic());
                }
            }
            Topic[] data = list.toArray(new Topic[list.size()]);
            Arrays.sort(data);
            return data;
        }
    }

    @Override
    @PostMapping("/unsubscribe")
    public Topic[] unsubscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        synchronized (this) {
            Username username = subscriptionRequest.getUsername();
            Topic topic = subscriptionRequest.getTopic();
            List<Topic> list = new ArrayList<>();
            for (Subscription subscription : subscriptionMap.values()) {
                if (subscription.getUsername().equals(username) && topic.match(subscription.getTopic())) {
                    delete(subscription.getId());
                    list.add(subscription.getTopic());
                }
            }
            Topic[] data = list.toArray(new Topic[0]);
            Arrays.sort(data);
            return data;
        }
    }



    @Override
    @GetMapping("/users")
    public Username[] listUsers() {
        synchronized (this) {
            Set<Username> set = new HashSet<>();
            for (Message message : messageMap.values()) {
                set.add(message.getUsername());
            }
            for (Subscription subscription : subscriptionMap.values()) {
                set.add(subscription.getUsername());
            }
            Username[] data = set.toArray(new Username[0]);
            Arrays.sort(data);
            return data;
        }
    }


    @Override
    @GetMapping("/topics")
    public Topic[] listTopics() {
        synchronized (this) {
            Topic[] data = topicMap.keySet().toArray(new Topic[0]);
            Arrays.sort(data);
            return data;
        }
    }


    @PostMapping("/topics/{username}")
    public Topic[] listTopics(@RequestBody Username username) {
        synchronized (this)
        {
            ArrayList<Topic> list = new ArrayList<>();
            for (Subscription subscription : subscriptionMap.values())
                if (subscription.getUsername().equals(username))
                    list.add(subscription.getTopic());

            Topic[] data = list.toArray(new Topic[list.size()]);
            Arrays.sort(data);
            return data;
        }
    }


    @GetMapping("/subscribers/{topic}")
    public Username[] listSubscribers(@RequestBody Topic topic) {
        synchronized (this)
        {
            HashSet<Username> set = new HashSet<>();
            TopicBackEnd backend = getTopicBackEnd(topic);
            for (SubscriptionId subscription : backend.subscriptions)
                set.add(subscriptionMap.get(subscription).getUsername());

            Username[] data = set.toArray(new Username[set.size()]);
            Arrays.sort(data);
            return data;
        }
    }

    @PostMapping("/message/user/{username}")
    public MessageId[] listMessages(@RequestBody Username username) {
        synchronized (this) {
            HashSet<MessageId> set = new HashSet<>();
            for (Message message : messageMap.values()) {
                if (message.getUsername().equals(username)) {
                    set.add(message.getId());
                }
            }
            MessageId[] data = set.toArray(new MessageId[set.size()]);
            Arrays.sort(data);
            return data;
        }
    }

    @PostMapping("/message/topic/")
    public MessageId[] listMessages(@RequestBody Topic topic) {
        synchronized (this) {
                TopicBackEnd backend = getTopicBackEnd(topic);
            MessageId[] data = backend.messages.toArray(new MessageId[backend.messages.size()]);
            Arrays.sort(data);
            return data;
        }
    }

    private void store(Subscription subscription) {
        synchronized (this) {
            subscriptionMap.put(subscription.getId(), subscription);
            getTopicBackEnd(subscription.getTopic()).add(subscription.getId());
        }
    }

    public void delete(SubscriptionId subscriptionId) {
        synchronized (this) {
            Subscription subscription = subscriptionMap.remove(subscriptionId);
            if (subscription != null) {
                getTopicBackEnd(subscription.getTopic()).remove(subscriptionId);
            }
        }
    }

    private static class TopicBackEnd
    {
        private final HashSet<MessageId> messages;
        private final HashSet<SubscriptionId> subscriptions;


        //----------------------------------------------------------
        public TopicBackEnd ()
        {
            this.messages = new HashSet<>();
            this.subscriptions = new HashSet<>();
        }

        //----------------------------------------------------------
        public void add (MessageId message)
        {
            messages.add(message);
        }

        //----------------------------------------------------------
        public void remove (MessageId message)
        {
            messages.remove(message);
        }

        //----------------------------------------------------------
        public void add (SubscriptionId subscription)
        {
            subscriptions.add(subscription);
        }

        //----------------------------------------------------------
        public void remove (SubscriptionId subscription)
        {
            subscriptions.remove(subscription);
        }
    }
}
