package se.umu.cs.ads.a1.util;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import se.umu.cs.ads.a1.interfaces.Messenger;
import se.umu.cs.ads.a1.types.Content;
import se.umu.cs.ads.a1.types.Data;
import se.umu.cs.ads.a1.types.Message;
import se.umu.cs.ads.a1.types.MessageId;
import se.umu.cs.ads.a1.types.Topic;
import se.umu.cs.ads.a1.types.Username;

public class Util
{
  public static final String PREFIX_FLAG     = "-";
  private static final int SIZE_ID           = 36;
  private static final int MIN_TIMESTAMP     = 0;
  private static final int MIN_SIZE_USERNAME = 1;
  private static final int MAX_SIZE_USERNAME = 255;
  private static final int MIN_SIZE_TOPIC    = 1;
  private static final int MAX_SIZE_TOPIC    = 255;
  private static final int MIN_SIZE_CONTENT  = 0;
  private static final int MAX_SIZE_CONTENT  = 65535;
  private static final int MIN_SIZE_DATA     = 0;
  private static final int MAX_SIZE_DATA     = 4194304;
  private static final String PREFIX         = "/";
  private static final int LENGTH_PREFIX     = PREFIX.length();
  private static final String WILDCARD       = "*";
  private static final int LENGTH_WILDCARD   = WILDCARD.length();
  private static final String CHARS          = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  private static final Random RANDOM         = new Random();


  //----------------------------------------------------------
  //----------------------------------------------------------
  public static boolean validateId (String id)
  {
    return id.length() == SIZE_ID;
  }

  //----------------------------------------------------------
  public static boolean validateTimestamp (long timestamp)
  {
    return timestamp >= MIN_TIMESTAMP;
  }

  //----------------------------------------------------------
  public static boolean validateUsername (String username)
  {
    return validateSize(username,MIN_SIZE_USERNAME,MAX_SIZE_USERNAME);
  }

  //----------------------------------------------------------
  public static boolean validateTopic (String topic)
  {
    if (!topic.startsWith(PREFIX))
      return false;
    return validateSize(topic,MIN_SIZE_TOPIC,MAX_SIZE_TOPIC);
  }

  //----------------------------------------------------------
  public static boolean validateContent (String content)
  {
    return content.length() <= MAX_SIZE_CONTENT;
  }

  //----------------------------------------------------------
  public static boolean validateData (byte[] data)
  {
    return data.length <= MAX_SIZE_DATA;
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  private static boolean validateSize (String data, int min, int max)
  {
    return validateSize(data.length(),min,max);
  }

  //----------------------------------------------------------
  private static boolean validateSize (int size, int min, int max)
  {
    return (size >= min) && (size <= max);
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  public static boolean endsWithWildcard (String topic)
  {
    return topic.endsWith(WILDCARD);
  }

  //----------------------------------------------------------
  public static String stripWildcard (String topic)
  {
    boolean wildcard = endsWithWildcard(topic);
    return wildcard ? topic.substring(0,topic.length() - LENGTH_WILDCARD) : topic;
  }

  //----------------------------------------------------------
  public static String toWildcardString (String topic, boolean wildcard)
  {
    return wildcard ? topic + Util.WILDCARD : topic;
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  public static String constructRandomString (int minSize, int maxSize)
  {
    if (minSize < 0)
      throw new IllegalArgumentException(minSize + "," + maxSize);
    if (maxSize < minSize)
      throw new IllegalArgumentException(minSize + "," + maxSize);

    int size = minSize + RANDOM.nextInt((maxSize - minSize) + 1);
    StringBuilder builder = new StringBuilder();
    for (int i=0; i<size; i++)
      builder.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
    return builder.toString();
  }

  //----------------------------------------------------------
  public static Username constructRandomUsername ()
  {
    return new Username(constructRandomString(MIN_SIZE_USERNAME,MAX_SIZE_USERNAME));
  }

  //----------------------------------------------------------
  public static Username[] constructRandomUsernames (int nr)
  {
    Username[] usernames = new Username[nr];
    for (int i=0; i<usernames.length; i++)
      usernames[i] = constructRandomUsername();
    return usernames;
  }

  //----------------------------------------------------------
  public static Topic constructRandomTopic ()
  {
    return new Topic(PREFIX + constructRandomString(MIN_SIZE_TOPIC - LENGTH_PREFIX,MAX_SIZE_TOPIC - LENGTH_PREFIX));
  }

  //----------------------------------------------------------
  public static Topic[] constructRandomTopics (int nr)
  {
    Topic[] topics = new Topic[nr];
    for (int i=0; i<topics.length; i++)
      topics[i] = constructRandomTopic();
    return topics;
  }

  //----------------------------------------------------------
  public static Content constructRandomContent ()
  {
    return new Content(constructRandomString(MIN_SIZE_CONTENT,MAX_SIZE_CONTENT));
  }

  //----------------------------------------------------------
  public static Data constructRandomData ()
  {
    return new Data(constructRandomString(MIN_SIZE_DATA,MAX_SIZE_DATA).getBytes(StandardCharsets.UTF_8));
  }

  //----------------------------------------------------------
  public static Data constructRandomData (int size)
  {
    return new Data(constructRandomString(size,size).getBytes(StandardCharsets.UTF_8));
  }

  //----------------------------------------------------------
  public static Message constructRandomMessage (Username username, Topic topic, int size)
  {
    return Message.construct(username,topic,constructRandomContent(),constructRandomData(size));
  }

  public static Message[] constructRandomMessages (Username username, Topic topic, int size, int nr)
  {
    Message[] messages = new Message[nr];
    for (int i=0; i<messages.length; i++)
      messages[i] = constructRandomMessage(username,topic,size);
    return messages;
  }

  //----------------------------------------------------------
  //----------------------------------------------------------
  public static Username getRandom (Username[] usernames)
  {
    return usernames[RANDOM.nextInt(usernames.length)];
  }

  //----------------------------------------------------------
  public static Topic getRandom (Topic[] topics)
  {
    return topics[RANDOM.nextInt(topics.length)];
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  public static MessageId[] getMessageIds (Message[] messages)
  {
    MessageId[] ids = new MessageId[messages.length];
    for (int i=0; i<ids.length; i++)
      ids[i] = messages[i].getId();
    return ids;
  }

  //----------------------------------------------------------
  public static Username[] getUsernames (Message[] messages)
  {
    HashSet<Username> set = new HashSet<>();
    for (Message message : messages)
      set.add(message.getUsername());

    Username[] usernames = set.toArray(new Username[set.size()]);
    Arrays.sort(usernames);
    return usernames;
  }

  //----------------------------------------------------------
  public static Topic[] getTopics (Message[] messages)
  {
    HashSet<Topic> set = new HashSet<>();
    for (Message message : messages)
      set.add(message.getTopic());

    Topic[] topics = set.toArray(new Topic[set.size()]);
    Arrays.sort(topics);
    return topics;
  }

  //----------------------------------------------------------
  public static HashMap<Topic,Message[]> constructTopicMessageMap (Message[] messages)
  {
    HashMap<Topic,ArrayList<Message>> map = new HashMap<>();
    for (Message message : messages)
    {
      ArrayList<Message> list = map.get(message.getTopic());
      if (list == null)
      {
        list = new ArrayList<>();
        map.put(message.getTopic(),list);
      }
      list.add(message);
    }
    HashMap<Topic,Message[]> data = new HashMap<>();
    for (Topic topic : map.keySet())
    {
      ArrayList<Message> list = map.get(topic);
      Message[] topicMessages = list.toArray(new Message[list.size()]);
      data.put(topic,topicMessages);
    }
    return data;
  }

  //----------------------------------------------------------
  public static HashMap<Username,Message[]> constructUsernameMessageMap (Message[] messages)
  {
    HashMap<Username,ArrayList<Message>> map = new HashMap<>();
    for (Message message : messages)
    {
      ArrayList<Message> list = map.get(message.getUsername());
      if (list == null)
      {
        list = new ArrayList<>();
        map.put(message.getUsername(),list);
      }
      list.add(message);
    }
    HashMap<Username,Message[]> data = new HashMap<>();
    for (Username username : map.keySet())
    {
      ArrayList<Message> list = map.get(username);
      Message[] usernameMessages = list.toArray(new Message[list.size()]);
      data.put(username,usernameMessages);
    }
    return data;
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  public static boolean isFlag (String value)
  {
    return value.startsWith(PREFIX_FLAG);
  }

  //----------------------------------------------------------
  public static String getFlagValue (String value)
  {
    int index = value.indexOf(PREFIX_FLAG);
    if (index < 0)
      throw new IllegalArgumentException(value);

    return value.substring(PREFIX_FLAG.length());
  }

  //----------------------------------------------------------
  public static boolean containsFlag (String[] args, String flag)
  {
    for (String arg : args)
      if (arg.startsWith(flag))
        return true;
    return false;
  }

  //----------------------------------------------------------
  public static String[] filterFlags (String[] args)
  {
    ArrayList<String> list = new ArrayList<>();
    for (String arg : args)
      if (!isFlag(arg))
        list.add(arg);
    return list.toArray(new String[list.size()]);
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  public static Messenger loadMessenger (String fqn)
  {
    try
    {
      Class<?> _class = Class.forName(fqn);
      return (Messenger)_class.getDeclaredConstructor().newInstance();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw new IllegalStateException("unable to instantiate messenger class '" + fqn + "'");
    }
  }
}
