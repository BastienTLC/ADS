package se.umu.cs.ads.a1.rest.types;

import se.umu.cs.ads.a1.rest.adts.AbstractStringType;
import se.umu.cs.ads.a1.rest.util.Util;

import java.util.UUID;

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
