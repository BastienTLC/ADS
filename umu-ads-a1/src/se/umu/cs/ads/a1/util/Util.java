package se.umu.cs.ads.a1.util;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import se.umu.cs.ads.a1.types.Content;
import se.umu.cs.ads.a1.types.Data;
import se.umu.cs.ads.a1.types.Message;
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
  //----------------------------------------------------------
  public static Message[] constructRandomMessages (Username username, Topic topic, int size, int count)
  {
    Message[] messages = new Message[count];
    for (int i=0; i<count; i++)
      messages[i] = constructRandomMessage(username,topic,size);
    return messages;
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
}
