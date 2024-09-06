package se.umu.cs.ads.a1.rest.types;

import se.umu.cs.ads.a1.rest.adts.AbstractLongType;
import se.umu.cs.ads.a1.rest.util.Util;

public class Timestamp extends AbstractLongType
{
  //----------------------------------------------------------
  public Timestamp (long value)
  {
    super(value);

    if (!Util.validateTimestamp(value))
      throw new IllegalArgumentException(Long.toString(value));
  }


  //----------------------------------------------------------
  //----------------------------------------------------------
  public static Timestamp now ()
  {
    long value = System.currentTimeMillis();
    return new Timestamp(value);
  }
}
