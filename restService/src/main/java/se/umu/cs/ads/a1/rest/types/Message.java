package se.umu.cs.ads.a1.rest.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Message
  implements Comparable<Message>
{
  private final MessageId id;
  private final Timestamp timestamp;
  private final Username username;
  private final Topic topic;
  private final Content content;
  private final Data data;


  //----------------------------------------------------------
  @JsonCreator
  public Message(
          @JsonProperty("id") MessageId id,
          @JsonProperty("timestamp") Timestamp timestamp,
          @JsonProperty("username") Username username,
          @JsonProperty("topic") Topic topic,
          @JsonProperty("content") Content content,
          @JsonProperty("data") Data data) {
    this.id = id;
    this.timestamp = timestamp;
    this.username = username;
    this.topic = topic;
    this.content = content;
    this.data = data;
  }

  //----------------------------------------------------------
  public Message copyWith (Timestamp timestamp)
  {
    return new Message(id,timestamp,username,topic,content,data);
  }

  //----------------------------------------------------------
  public MessageId getId ()
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
  public Content getContent ()
  {
    return content;
  }

  //----------------------------------------------------------
  public Data getData ()
  {
    return data;
  }

  //----------------------------------------------------------
  @Override
  public boolean equals (Object o)
  {
    if (!(o instanceof Message))
      return false;

    Message rhs = (Message)o;
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
  public int compareTo (Message rhs)
  {
    int t = timestamp.compareTo(rhs.timestamp);
    if (t != 0)
      return t;
    return id.compareTo(rhs.id);
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  public static Message construct (Username username, Topic topic, Content content, Data data)
  {
    MessageId id = MessageId.construct();
    Timestamp timestamp = Timestamp.now();
    return new Message(id,timestamp,username,topic,content,data);
  }

  //----------------------------------------------------------
  public static Message[] construct (Username username, Topic topic, Content content, Data data, int nr)
  {
    if (nr < 1)
      throw new IllegalArgumentException(Integer.toString(nr));

    Message[] messages = new Message[nr];
    for (int i=0; i<messages.length; i++)
      messages[i] = construct(username,topic,content,data);
    return messages;
  }
}
