import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;

import static org.junit.Assert.*;

public class ArraySetTest {

    @Test
    public void test1() throws Exception {
        ArraySet arraySet = new ArraySet();

        arraySet.set(0);
        arraySet.set(-10);
        arraySet.set(50);

        System.out.println(arraySet);

        assertTrue(arraySet.test(0));
        assertTrue(arraySet.test(-10));
        assertFalse(arraySet.test(25));
        assertTrue(arraySet.test(50));
        assertEquals(arraySet.size(), 3);

        arraySet.clear(0);

        System.out.println(arraySet);

        assertFalse(arraySet.test(0));
        assertEquals(arraySet.size(), 2);
    }

    @Test
    public void testRandom() throws Exception {
        HashSet<Integer> control = new HashSet();
        ArraySet arraySet = new ArraySet();

        Random rnd = new Random();
        for (int n = 0; n < 100; n++) {
            for (int m = 0; m < rnd.nextInt(100); m++) {
                int input = rnd.nextInt((Integer.MAX_VALUE * 2 - 1) - Integer.MAX_VALUE);
                control.add(input);
                arraySet.set(input);
            }

            for (int m = 0; m < rnd.nextInt(100); m++) {
                int input = rnd.nextInt((Integer.MAX_VALUE * 2 - 1) - Integer.MAX_VALUE);
                control.remove(input);
                arraySet.clear(input);
            }

            for (int m : control) {
                assertTrue(arraySet.test(m));
            }

            assertEquals(arraySet.size(), control.size());
        }
    }
}