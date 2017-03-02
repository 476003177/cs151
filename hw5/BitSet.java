import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BitSet implements IntSet
{
   public boolean test(int n)
   {
      if (elements == null || n < start || n >= start + 32 * elements.length)
         return false;
      int p = (n - start) / 32;
      int i = (n - start) % 32;
      return test(elements[p], i);
   }

   public void set(int n)
   {
      if (elements == null) 
      {
         elements = new int[10];
         start = n;
      }
      else if (n < start)
      {
         int intsNeeded = (start + 32 * elements.length - n) / 32 + 1;
         int[] newElements = new int[Math.max(intsNeeded, 2 * elements.length)];
         System.arraycopy(elements, 0, 
               newElements, newElements.length - elements.length, 
               elements.length);
         start -= 32 * (newElements.length - elements.length);
         elements = newElements;
      }
      else if (n >= start + 32 * elements.length)
      {
         int intsNeeded = (n - start) / 32 + 1; 
         elements = Arrays.copyOf(elements, 
               Math.max(intsNeeded, 2 * elements.length));         
      }
      int p = (n - start) / 32;
      int i = (n - start) % 32;
      if (!test(elements[p], i)) 
      {
         elementCount++;
         elements[p] = set(elements[p], i);      
      }
   }

   public void clear(int n)
   {
      if (elements != null && n >= start || n < start + 32 * elements.length)
      {
         int p = (n - start) / 32;
         int i = (n - start) % 32;
         if (test(elements[p], i)) 
         {
            elementCount--;
            elements[p] = clear(elements[p], i);
         }
      }
   }

   public int min()
   {
      if (elements != null)
         for (int p = 0; p < elements.length; p++)
            for (int i = 0; i < 32; i++)
               if (test(elements[p], i)) return 32 * p + i + start;      
      return Integer.MAX_VALUE;
   }

   public int max()
   {
      if (elements!= null)
         for (int p = elements.length - 1; p >= 0; p--)
            for (int i = 31; i >= 0; i--)
               if (test(elements[p], i)) return 32 * p + i + start;
      return Integer.MIN_VALUE;
   }

   public int size()
   {
      return elementCount;
   }

   private static boolean test(int n, int i)
   {
      assert 0 <= i && i < 32;
      return (n & (1 << i)) != 0;
   }

   private static int set(int n, int i)
   {
      assert 0 <= i && i < 32;
      return n | (1 << i);
   }

   private static int clear(int n, int i)
   {
      assert 0 <= i && i < 32;
      return n & ~(1 << i);
   }

   int[] elements;
   int start;
   int elementCount;

   private class BitSetIterator implements Iterator<Integer> {

      public BitSetIterator() {
         elementIndex = 0;
      }

      @Override
      public boolean hasNext() {
         return elementIndex < elements.length * 32;
      }

      @Override
      public Integer next() {
         if (!hasNext())
            throw new NoSuchElementException();

         int indexInElements = elementIndex / 32;
         int bitIndexInPack = elementIndex % 32;

         elementIndex++;

         return start + (indexInElements * elements.length) + bitIndexInPack;
      }

      @Override
      public void remove() {
         int indexToRemove = elementIndex - 1;

         int indexInElements = indexToRemove / 32;
         int bitIndexInPack = indexToRemove % 32;

         if (indexInElements >= 0 && indexInElements < elements.length)
            clear(indexInElements, bitIndexInPack);
      }

      private int elementIndex;

   }

   public Iterator<Integer> iterator() {
      return new BitSetIterator();
   }

}
