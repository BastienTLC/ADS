package se.umu.cs.ads.a1.types;

import java.util.UUID;
import se.umu.cs.ads.a1.adts.AbstractStringType;
import se.umu.cs.ads.a1.util.Util;

public class MessageId extends AbstractStringType
{
  //----------------------------------------------------------
  public MessageId (String value)
  {
    super(value);

    if (!Util.validateId(value))
      throw new IllegalArgumentException(value);
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  public static MessageId construct ()
  {
    String value = UUID.randomUUID().toString();
    return new MessageId(value);
  }
}
