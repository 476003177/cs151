import org.junit.Test;

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

    }
}