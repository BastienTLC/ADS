package se.umu.cs.ads.a1.adts;

public class AbstractStringType
  implements Comparable<AbstractStringType>
{
  protected final String value;


  //----------------------------------------------------------
  public AbstractStringType (String value)
  {
    this.value = value;
  }

  //----------------------------------------------------------
  public String getValue ()
  {
    return value;
  }

  //----------------------------------------------------------
  @Override
  public boolean equals (Object o)
  {
    if (!(o instanceof AbstractStringType))
      return false;

    AbstractStringType rhs = (AbstractStringType)o;
    return value.equals(rhs.value);
  }

  //----------------------------------------------------------
  @Override
  public int hashCode ()
  {
    return value.hashCode();
  }

  //----------------------------------------------------------
  @Override
  public String toString ()
  {
    return value;
  }

  //----------------------------------------------------------
  @Override
  public int compareTo (AbstractStringType rhs)
  {
    return value.compareTo(rhs.value);
  }
}
