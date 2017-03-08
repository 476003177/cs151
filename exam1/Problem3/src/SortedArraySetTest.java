import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;

/**
   Tests the ArraySet implementation.
 */
public class SortedArraySetTest
{

   @Test public void setAndClear() {
      ArraySet set = new ArraySet();
      set.set(3);
      set.set(1);
      set.set(2);
      set.set(2);
      System.out.println(Arrays.toString(set.elements));
      set.clear(2);
      set.clear(1);
      System.out.println(Arrays.toString(set.elements));
   }

   @Test public void checkExpectedContent() {
      ArraySet set = new ArraySet();
      set.set(3);
      set.set(1);
      set.set(2);
      set.set(2);
      set.clear(2);
      assertEquals(1, set.elements[0]);
      assertEquals(3, set.elements[1]);
   }
}
