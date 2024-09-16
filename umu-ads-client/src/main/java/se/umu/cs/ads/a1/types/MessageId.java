package se.umu.cs.ads.a1.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.umu.cs.ads.a1.adts.AbstractStringType;
import se.umu.cs.ads.a1.util.Util;

import java.util.UUID;

public class MessageId extends AbstractStringType {

  //----------------------------------------------------------
  @JsonCreator
  public MessageId(@JsonProperty("value") String value) {
    super(value);

    if (!Util.validateId(value)) {
      throw new IllegalArgumentException(value);
    }
  }

  //----------------------------------------------------------
  public static MessageId construct() {
    String value = UUID.randomUUID().toString();
    return new MessageId(value);
  }
}
