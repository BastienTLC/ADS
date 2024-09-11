package se.umu.cs.ads.a1.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.umu.cs.ads.a1.adts.AbstractByteArrayType;
import se.umu.cs.ads.a1.util.Util;

import java.util.Arrays;
import java.util.Base64;

public class Data extends AbstractByteArrayType
{
  public static final Data EMPTY = new Data(new byte[0]);

  //----------------------------------------------------------
  @JsonCreator
  public Data(@JsonProperty("value") Object value)
  {
    super(value instanceof String ? Base64.getDecoder().decode((String)value) : (byte[])value);

      if (!Util.validateData(this.getValue())) {
      throw new IllegalArgumentException(Arrays.toString(this.getValue()));
    }
  }

  //----------------------------------------------------------
  @JsonProperty("value")
  public byte[] getValue() {
    return super.getValue();
  }
}