public class Point
{
   private int x;
   private int y;

   public Point(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   public String toString()
   {
      return getClass().getName() + "[x=" + x + ",y=" + y + "]";
   }

   public int hashCode()
   {
      return x + y;
   }

   public boolean equals(Object otherObject)
   {
      if (this == otherObject)
         return true;

      if (otherObject == null)
         return false

      if (getClass() != otherObject.getClass())
         return false;

      Point other = (Point) otherObject;
      return x == other.x && y == other.y;
   }
}