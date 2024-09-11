package se.umu.cs.ads.a1.rest.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.umu.cs.ads.a1.rest.adts.AbstractStringType;
import se.umu.cs.ads.a1.rest.util.Util;

public class Content extends AbstractStringType {

  public static final Content EMPTY = new Content("");

  //----------------------------------------------------------
  @JsonCreator
  public Content(@JsonProperty("value") String value) {
    super(value);

    if (!Util.validateContent(value)) {
      throw new IllegalArgumentException(value);
    }
  }
}
