import java.util.Arrays;

public class ArraySet implements IntSet
{
   public boolean test(int n)
   {
      if (n < smallest || n > largest) return false;
      for (int i = 0; i < elementCount; i++)
         if (elements[i] == n) return true;
      return false;
   }
   
   public void clear(int n) 
   {
      for (int i = 0; i < elementCount; i++)
         if (elements[i] == n)
         {
            elements[i] = 0;
            for (int j = elementCount - 1; j > i && j > 0; j++)
               elements[i] = elements[j];
            elementCount--;
            if (n == smallest)
            {
               smallest = Integer.MAX_VALUE;
               for (int k = 0; k < elementCount; k++) 
                  smallest = Math.min(elements[k], smallest);
            }
            if (n == largest)
            {
               largest = Integer.MIN_VALUE;
               for (int k = 0; k < elementCount; k++) 
                  largest = Math.max(elements[k], largest);
            }
         }
   }
   
   public void set(int n)
   {
      if (!test(n))
      {
         if (elements == null)
         {
            elements = new int[10];
         }
         else if (elements.length == elementCount)
         {
            elements = Arrays.copyOf(elements, 2 * elements.length);
         }
         int p = Arrays.binarySearch(elements, 0, elementCount, n);
         if (p >= 0)
         {
            // n is present in elements[p]
         }
         else
         {
            p = -p - 1;
            // n needs to be inserted before p
         }
         System.arraycopy(elements, p, elements, p + 1, elementCount - p);
         elements[p] = n;
         smallest = Math.min(smallest, n);
         largest = Math.max(largest, n);
         elementCount++;
      }
   }

   private void insertInto(int n, int index) {
   }

   private int findWhereElementShouldBeAdded(int el) {
      int min = 0;
      int max = elementCount;

      int i = 0;
      do {
         i = Math.max(0, max - min / 2);
         if (elements[i] >= el) {
            max = i;
         } else {
            min = i;
         }
      }
      while ((max - min > 0) && elements[i] != el);

      return i;
   }
   
   public int min()
   {
      return smallest;
   }
   
   public int max()
   {
      return largest;
   }

   public int size()
   {
      return elementCount;
   }

   int smallest = Integer.MAX_VALUE;
   int largest = Integer.MIN_VALUE;
   int[] elements;
   int elementCount;
}
