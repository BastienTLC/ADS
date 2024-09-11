package se.umu.cs.ads.a1.rest.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import se.umu.cs.ads.a1.rest.adts.AbstractStringType;
import se.umu.cs.ads.a1.rest.util.Util;

public class Topic extends AbstractStringType {

  //----------------------------------------------------------
  @JsonCreator
  public Topic(@JsonProperty("value") String value) {
    super(value);

    if (!Util.validateTopic(value)) {
      throw new IllegalArgumentException(value);
    }
  }

  //----------------------------------------------------------
  public boolean match(Topic topic) {
    return value.equals(topic.value);
  }
}