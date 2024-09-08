package se.umu.cs.ads.a1.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import se.umu.cs.ads.a1.interfaces.Messenger;
import se.umu.cs.ads.a1.types.Message;
import se.umu.cs.ads.a1.types.MessageId;
import se.umu.cs.ads.a1.types.Subscription;
import se.umu.cs.ads.a1.types.SubscriptionId;
import se.umu.cs.ads.a1.types.Timestamp;
import se.umu.cs.ads.a1.types.Topic;
import se.umu.cs.ads.a1.types.Username;

public class InMemoryMessengerBackEnd
  implements Messenger
{
  private final HashMap<MessageId,Message> messageMap;
  private final HashMap<SubscriptionId,Subscription> subscriptionMap;
  private final HashMap<Topic,TopicBackEnd> topicMap;


  //----------------------------------------------------------
  public InMemoryMessengerBackEnd ()
  {
    this.messageMap = new HashMap<>();
    this.subscriptionMap = new HashMap<>();
    this.topicMap = new HashMap<>();
  }

  //----------------------------------------------------------
  private TopicBackEnd getTopicBackEnd (Topic topic)
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
	public void store (Message message)
	{
	  synchronized (this)
	  {
          message = message.copyWith(Timestamp.now());
  	  messageMap.put(message.getId(),message);
  	  getTopicBackEnd(message.getTopic()).add(message.getId());
	  }
	}

  //----------------------------------------------------------
	public void store (Message[] messages)
  {
    synchronized (this)
    {
  	  for (Message message : messages)
//  	    store(message);
  	  {
        message = message.copyWith(Timestamp.now());
        messageMap.put(message.getId(),message);
        getTopicBackEnd(message.getTopic()).add(message.getId());
  	  }
    }
  }

  //----------------------------------------------------------
	public Message retrieve (MessageId message)
	{
    synchronized (this)
    {
  	  Message data = messageMap.get(message);
  	  if (data == null)
  	    throw new IllegalArgumentException("unrecognized message: '" + message + "'");

  	  return data;
    }
	}

  //----------------------------------------------------------
	public Message[] retrieve (MessageId[] messages)
	{
    synchronized (this)
    {
  	  Message[] data = new Message[messages.length];
  	  for (int i=0; i<data.length; i++)
//  	    data[i] = retrieve(messages[i]);
  	  {
        Message d = messageMap.get(messages[i]);
        if (d == null)
          throw new IllegalArgumentException("unrecognized message: '" + messages[i] + "'");

        data[i] = d;
  	  }
  	  return data;
    }
	}

  //----------------------------------------------------------
	public void delete (MessageId message)
	{
        synchronized (this)
        {
          Message m = messageMap.remove(message);
          if (m != null)
            getTopicBackEnd(m.getTopic()).remove(message);
        }
	}

  //----------------------------------------------------------
	public void delete (MessageId[] messages)
	{
    synchronized (this)
    {
      for (MessageId message : messages)
//        delete(message);
      {
        Message m = messageMap.remove(message);
        if (m != null)
          getTopicBackEnd(m.getTopic()).remove(message);
      }
    }
	}


  //----------------------------------------------------------
  //----------------------------------------------------------
  // subscription interface
  //----------------------------------------------------------
  private void store (Subscription subscription)
  {
    synchronized (this)
    {
      subscriptionMap.put(subscription.getId(),subscription);
      getTopicBackEnd(subscription.getTopic()).add(subscription.getId());
    }
  }

  //----------------------------------------------------------
  public void delete (SubscriptionId subscription)
  {
    synchronized (this)
    {
      Subscription s = subscriptionMap.remove(subscription);
      if (s != null)
        getTopicBackEnd(s.getTopic()).remove(subscription);
    }
  }

  //----------------------------------------------------------
	public Topic[] subscribe (Username username, Topic topic)
	{
    synchronized (this)
    {
      ArrayList<Topic> list = new ArrayList<>();
      for (Topic t : topicMap.keySet())
        if (topic.match(t))
        {
          Subscription subscription = Subscription.construct(username,t);
          store(subscription);
          list.add(subscription.getTopic());
        }

      Topic[] data = list.toArray(new Topic[list.size()]);
      Arrays.sort(data);
      return data;
    }
	}

  //----------------------------------------------------------
	public Topic[] unsubscribe (Username username, Topic topic)
	{
    synchronized (this)
    {
      ArrayList<Topic> list = new ArrayList<>();
      for (Subscription subscription : subscriptionMap.values())
        if (subscription.getUsername().equals(username))
          if (topic.match(subscription.getTopic()))
          {
            delete(subscription.getId());
            list.add(subscription.getTopic());
          }

      Topic[] data = list.toArray(new Topic[list.size()]);
      Arrays.sort(data);
      return data;
    }
	}


  //----------------------------------------------------------
  //----------------------------------------------------------
  // query interface

  //----------------------------------------------------------
	public Username[] listUsers ()
	{
    synchronized (this)
    {
      HashSet<Username> set = new HashSet<>();
      for (Message message : messageMap.values())
        set.add(message.getUsername());
      for (Subscription subscription : subscriptionMap.values())
        set.add(subscription.getUsername());

      Username[] data = set.toArray(new Username[set.size()]);
      Arrays.sort(data);
      return data;
    }
	}

  //----------------------------------------------------------
  public Topic[] listTopics ()
  {
    synchronized (this)
    {
      Topic[] data = topicMap.keySet().toArray(new Topic[topicMap.size()]);
      Arrays.sort(data);
      return data;
    }
  }

  //----------------------------------------------------------
  public Topic[] listTopics (Username username)
  {
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

  //----------------------------------------------------------
  public Username[] listSubscribers (Topic topic)
  {
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

  //----------------------------------------------------------
  public MessageId[] listMessages (Username username)
  {
    synchronized (this)
    {
      ArrayList<MessageId> list = new ArrayList<>();
      for (Message message : messageMap.values())
        if (message.getUsername().equals(username))
          list.add(message.getId());
      return list.toArray(new MessageId[list.size()]);
    }
  }

  //----------------------------------------------------------
	public MessageId[] listMessages (Topic topic)
	{
    synchronized (this)
    {
      TopicBackEnd backend = getTopicBackEnd(topic);
      return backend.messages.toArray(new MessageId[backend.messages.size()]);
    }
	}


	//----------------------------------------------------------
  //----------------------------------------------------------
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
