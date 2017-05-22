public class LabeledPoint extends Point
{
   private String label;

   public LabeledPoint(int x, int y, String label)
   {
      super(x, y);
      this.label = label;
   }

   public String toString()
   {
      return super.toString() + "[label=" + label + "]";
   }

   public boolean equals(Object otherObject)
   {

      if (this == otherObject)
         return true;

      if (otherObject == null)
         return false;

      if (getClass() != otherObject.getClass())
         return false;

      LabeledPoint other = (LabeledPoint) otherObject;
      return super.equals(other) && label.equals(other.label);
   }
}
