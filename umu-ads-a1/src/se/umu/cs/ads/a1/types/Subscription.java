package se.umu.cs.ads.a1.types;

public class Subscription
  implements Comparable<Subscription>
{
  private final SubscriptionId id;
  private final Timestamp timestamp;
  private final Username username;
  private final Topic topic;


  //----------------------------------------------------------
  public Subscription (SubscriptionId id, Timestamp timestamp, Username username, Topic topic)
  {
    this.id = id;
    this.timestamp = timestamp;
    this.username = username;
    this.topic = topic;
  }

  //----------------------------------------------------------
  public SubscriptionId getId ()
  {
    return id;
  }

  //----------------------------------------------------------
  public Timestamp getTimestamp ()
  {
    return timestamp;
  }

  //----------------------------------------------------------
  public Username getUsername ()
  {
    return username;
  }

  //----------------------------------------------------------
  public Topic getTopic ()
  {
    return topic;
  }

  //----------------------------------------------------------
  @Override
  public boolean equals (Object o)
  {
    if (!(o instanceof Subscription))
      return false;

    Subscription rhs = (Subscription)o;
    return id.equals(rhs.id);
  }

  //----------------------------------------------------------
  @Override
  public int hashCode ()
  {
    return id.hashCode();
  }

  //----------------------------------------------------------
  @Override
  public String toString ()
  {
    return id.toString();
  }

  //----------------------------------------------------------
  @Override
  public int compareTo (Subscription rhs)
  {
    int t = timestamp.compareTo(rhs.timestamp);
    if (t != 0)
      return t;
    return id.compareTo(rhs.id);
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  public static Subscription construct (Username username, Topic topic)
  {
    SubscriptionId id = SubscriptionId.construct();
    Timestamp timestamp = Timestamp.now();
    return new Subscription(id,timestamp,username,topic);
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  public boolean match (Topic topic)
  {
    return this.topic.match(topic);
  }

  //----------------------------------------------------------
  public boolean match (Username username)
  {
    return this.username.equals(username);
  }
}
