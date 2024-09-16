package se.umu.cs.ads.a1.adts;

public class AbstractLongType
  implements Comparable<AbstractLongType>
{
  private final long value;


  //----------------------------------------------------------
  public AbstractLongType (long value)
  {
    this.value = value;
  }

  //----------------------------------------------------------
  public long getValue ()
  {
    return value;
  }

  //----------------------------------------------------------
  @Override
  public boolean equals (Object o)
  {
    if (!(o instanceof AbstractLongType))
      return false;

    AbstractLongType rhs = (AbstractLongType)o;
    return value == rhs.value;
  }

  //----------------------------------------------------------
  @Override
  public int hashCode ()
  {
    return Long.hashCode(value);
  }

  //----------------------------------------------------------
  @Override
  public String toString ()
  {
    return Long.toString(value);
  }

  //----------------------------------------------------------
  @Override
  public int compareTo (AbstractLongType rhs)
  {
    return Long.compare(value,rhs.value);
  }
}
