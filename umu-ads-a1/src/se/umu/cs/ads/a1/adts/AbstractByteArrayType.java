package se.umu.cs.ads.a1.adts;

import java.util.Arrays;

public class AbstractByteArrayType
  implements Comparable<AbstractByteArrayType>
{
  private final byte[] value;


  //----------------------------------------------------------
  public AbstractByteArrayType (byte[] value)
  {
    this.value = value.clone();
  }

  //----------------------------------------------------------
  public byte[] getValue ()
  {
    return value.clone();
  }

  //----------------------------------------------------------
  @Override
  public boolean equals (Object o)
  {
    if (!(o instanceof AbstractByteArrayType))
      return false;

    AbstractByteArrayType rhs = (AbstractByteArrayType)o;
    return Arrays.equals(value,rhs.value);
  }

  //----------------------------------------------------------
  @Override
  public int hashCode ()
  {
    return Arrays.hashCode(value);
  }

  //----------------------------------------------------------
  @Override
  public String toString ()
  {
    return Arrays.toString(value);
  }

  //----------------------------------------------------------
  @Override
  public int compareTo (AbstractByteArrayType rhs)
  {
    return Arrays.compare(value,rhs.value);
  }
}
