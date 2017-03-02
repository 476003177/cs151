import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
   Tests the BitSet implementation.
 */
public class BitSetTest
{
   /**
      Tests setting a value.
    */
   @Test public void testOneElement()
   {      
      IntSet set = new BitSet();
      set.set(100);
      assertEquals(100, set.min());
      assertEquals(100, set.max());
      assertEquals(1, set.size());
      assertTrue(set.test(100));
      assertFalse(set.test(99));
   }

   /**
      Tests that the bits are set in the elements array
      (i.e. that this is actually a bit array).
   */
   @Test public void testBits()
   {
      BitSet set = new BitSet();
      set.set(100);
      set.set(102);
      set.clear(100);
      assertEquals(4, set.elements[0]);
      assertEquals(100, set.start);
   }
   
   /**
      Tests setting and clearing a value.
    */
   @Test public void setAndClear()
   {
      BitSet set = new BitSet();
      set.set(100);
      set.set(150);
      set.clear(100);
      assertEquals(150, set.min());
      assertEquals(150, set.max());
      assertEquals(1, set.size());
      assertTrue(set.test(150));
      assertFalse(set.test(100));      
   }

   /**
      Tests a set with 500 entries going up to a million.
    */
   @Test public void growSet()
   {
      IntSet set = new ArraySet();
      for (int i = 1; i <= 1000; i++)
      {
         set.set(i * i);
      }
      for (int i = 1; i <= 1000; i += 2)
      {
         set.clear(i * i);
      }
      for (int i = 1; i <= 1000; i++)
      {
         if (i % 2 == 0) assertTrue(set.test(i * i));
         else assertFalse(set.test(i * i));
      }
      assertEquals(4, set.min());
      assertEquals(1000000, set.max());
      assertEquals(500, set.size());
   }

   /**
      Tests a set with values between -999 and 1000.
    */
   @Test public void posAndNeg()
   {
      BitSet set = new BitSet();
      int sign = -1;
      for (int i = 1; i <= 1000; i++)
      {
         set.set(sign * i);
         sign *= -1;
      }
      for (int i = 1; i <= 1000; i++)
      {
         if (i % 2 == 0) assertTrue(set.test(i));
         else assertTrue(set.test(-i));
      }
      assertEquals(-999, set.min());
      assertEquals(1000, set.max());
      assertEquals(1000, set.size());      
   }

   /**
      Tests the growth of the bit array, as described in
      the problem specification.
    */
   @Test public void arrayTest()
   {
      BitSet set = new BitSet();
      set.set(100); 
      assertEquals(10, set.elements.length);
      assertEquals(100, set.start);
      set.set(500);
      assertEquals(20, set.elements.length);
      assertEquals(100, set.start);
      set.set(4000);
      assertEquals(122, set.elements.length);
      assertEquals(100, set.start);
   }
   
   /**
      Tests the growth of the bit array, as described in
      the problem specification, when elements to the left
      of the current minimum are added.
    */
   @Test public void arrayTestNeg()
   {
      BitSet set = new BitSet();
      set.set(100);
      set.set(-1);
      assertEquals(20, set.elements.length);
      assertEquals(-220, set.start);
      set.set(-1000);
      assertEquals(45, set.elements.length);
      assertEquals(-1020, set.start);
   }
}
