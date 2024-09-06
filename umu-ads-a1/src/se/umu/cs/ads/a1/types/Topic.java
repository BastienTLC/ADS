package se.umu.cs.ads.a1.types;

import se.umu.cs.ads.a1.adts.AbstractStringType;
import se.umu.cs.ads.a1.util.Util;

public class Topic extends AbstractStringType
{
  private final boolean wildcard;


  //----------------------------------------------------------
  public Topic (String value)
  {
    super(Util.stripWildcard(value));

    if (!Util.validateTopic(value))
      throw new IllegalArgumentException(value);

    this.wildcard = Util.endsWithWildcard(value);
  }

  //----------------------------------------------------------
  public boolean getWildcard ()
  {
    return wildcard;
  }

  //----------------------------------------------------------
  public boolean match (Topic topic)
  {
    return wildcard ? value.startsWith(topic.value) : value.equals(topic.value);
  }
}
