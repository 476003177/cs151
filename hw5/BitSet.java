import java.util.Arrays;
import java.util.ConcurrentModificationException;
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
         modCount++;
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
            modCount++;
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

   private class BitSetIterator implements Iterator<Integer> {

      private int nextElementIndex;
      private int expectedModCount;
      private boolean lastCalledNext;

      public BitSetIterator() {
         nextElementIndex = 0;
         expectedModCount = modCount;
         lastCalledNext = false;
      }

      @Override
      public boolean hasNext() {
         if (elements == null || nextElementIndex < 0 || nextElementIndex >= elements.length * 32)
            return false;

         int indexInElements = nextElementIndex / 32;
         int bitIndexInPack = nextElementIndex % 32;

         if (elements[indexInElements] > 0) {
            for (int i = bitIndexInPack; i < 32; i++) {
               if (test(elements[indexInElements], i))
                  return true;
            }
         }

         for (int i = indexInElements + 1; i < elements.length; i++) {
            if (elements[i] > 0)
               return true;
         }
         return false;
      }

      @Override
      public Integer next() {
         if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         else if (!hasNext())
            throw new NoSuchElementException();
         else
            lastCalledNext = true;

         int indexInElements = nextElementIndex / 32;
         int bitIndexInPack = nextElementIndex % 32;

         for (int i = indexInElements; i < elements.length; i++) {
            if (elements[i] != 0) {
               // Search in the pack
               for (int j = bitIndexInPack; j < 32; j++) {
                  if (test(elements[i], j)) {
                     nextElementIndex = (i * 32) + j;
                     return start + nextElementIndex++;
                  }
               }
            }

            bitIndexInPack = 0; // After first iteration, it should be 0
         }
         // This should never happen
         return null;
      }

      @Override
      public void remove() {
         if (!lastCalledNext)
            throw new IllegalStateException("Remove must only be called right after next has been called.");
         else if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         else
            lastCalledNext = false;

         int elementIndexToRemove = nextElementIndex - 1;

         int indexInElements = elementIndexToRemove / 32;
         int bitIndexInPack = elementIndexToRemove % 32;

         try {
            elements[indexInElements] = clear(elements[indexInElements], bitIndexInPack);
            elementCount--;
            expectedModCount = ++modCount;
         } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ConcurrentModificationException();
         }
      }

   }

   public Iterator<Integer> iterator() {
      return new BitSetIterator();
   }

   int[] elements;
   int start;
   int elementCount;
   private int modCount;

}
