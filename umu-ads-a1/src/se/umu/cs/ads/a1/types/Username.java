package se.umu.cs.ads.a1.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.umu.cs.ads.a1.adts.AbstractStringType;
import se.umu.cs.ads.a1.util.Util;

public class Username extends AbstractStringType {

  //----------------------------------------------------------
  @JsonCreator
  public Username(@JsonProperty("value") String value) {
    super(value);

    if (!Util.validateUsername(value)) {
      throw new IllegalArgumentException(value);
    }
  }
}
