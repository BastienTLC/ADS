package se.umu.cs.ads.a1.types;

import se.umu.cs.ads.a1.adts.AbstractStringType;
import se.umu.cs.ads.a1.util.Util;

public class Username extends AbstractStringType
{
  //----------------------------------------------------------
  public Username (String value)
  {
    super(value);

    if (!Util.validateUsername(value))
      throw new IllegalArgumentException(value);
  }
}
