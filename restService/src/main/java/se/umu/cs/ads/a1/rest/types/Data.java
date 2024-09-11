package se.umu.cs.ads.a1.rest.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.umu.cs.ads.a1.rest.adts.AbstractByteArrayType;
import se.umu.cs.ads.a1.rest.util.Util;

import java.util.Arrays;

public class Data extends AbstractByteArrayType
{
  public static final Data EMPTY = new Data(new byte[0]);

  //----------------------------------------------------------
  @JsonCreator
  public Data(@JsonProperty("value") byte[] value)
  {
    super(value);

    if (!Util.validateData(value))
      throw new IllegalArgumentException(Arrays.toString(value));
  }

}