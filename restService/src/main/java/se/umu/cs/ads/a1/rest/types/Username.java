package se.umu.cs.ads.a1.rest.types;

import se.umu.cs.ads.a1.rest.adts.AbstractStringType;
import se.umu.cs.ads.a1.rest.util.Util;

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
