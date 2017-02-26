import org.junit.Test;

import java.util.HashSet;
import java.util.Random;

import static org.junit.Assert.*;

public class SetTest {

    @Test
    public void setAndClear() {
        IntSet set = new ArraySet();
        set.set(100);
        set.set(150);
        set.clear(100);
        assertEquals(150, set.min());
        assertEquals(150, set.max());
        assertEquals(1, set.size());
        assertTrue(set.test(150));
        assertFalse(set.test(100));
    }

    @Test
    public void elementOrderRemove() {
        ArraySet set = new ArraySet();
        set.set(40);
        set.set(110);
        set.set(90);
        set.set(70);
        set.set(100);
        set.set(80);
        set.clear(110);
        System.out.println(set);
        assertEquals(40, set.elements[0]);
        assertEquals(80, set.elements[1]);
        assertEquals(90, set.elements[2]);
        assertEquals(100, set.largest);
    }

    @Test
    public void testOneElement() {
        IntSet set = new BitSet();
        set.set(100);
        assertEquals(100, set.min());
        assertEquals(100, set.max());
        assertEquals(1, set.size());
        assertTrue(set.test(100));
        assertFalse(set.test(99));
    }

    @Test
    public void testBits() {
        BitSet set = new BitSet();
        set.set(100);
        set.set(102);
        set.clear(100);
        assertEquals(4, set.elements[0]);
        assertEquals(100, set.start);
    }

    @Test
    public void bitSetTestExpandingBackwards() {
        BitSet set = new BitSet();
        set.set(100);
        set.set(-1);
        set.set(-1000);
        set.set(-1020);
        set.set(-1021);
        assertTrue(set.test(100));
        assertTrue(set.test(-1));
        assertTrue(set.test(-1000));
        assertTrue(set.test(-1020));
        assertTrue(set.test(-1021));
        System.out.println(set.elements.length);
        System.out.println(set.size());
    }

    @Test
    public void arraySetTest1() throws Exception {
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

        arraySet.set(12);

        System.out.println(arraySet);
    }

    @Test
    public void arraySetTestRandom() throws Exception {
        HashSet<Integer> control = new HashSet();
        IntSet arraySet = new ArraySet();

        Random rnd = new Random();
        for (int n = 0; n < 100; n++) {
            for (int m = 0; m < rnd.nextInt(20); m++) {
                int input = rnd.nextInt(100) - 50;
                control.add(input);
                arraySet.set(input);
            }

            assertEquals(arraySet.size(), control.size());

            for (int m = 0; m < rnd.nextInt(1000); m++) {
                int input = rnd.nextInt(100) - 50;
                control.remove(input);
                arraySet.clear(input);
            }

            for (int m : control) {
                assertTrue(arraySet.test(m));
            }

            System.out.println("Control: " + control.toString());
            System.out.println("ArraySet: " + arraySet);
            System.out.println("Max: " + arraySet.max());
            System.out.println("Min: " + arraySet.min());
            System.out.println();

            assertEquals(arraySet.size(), control.size());
        }
    }

    @Test
    public void bitSetTestRandom() throws Exception {
        HashSet<Integer> control = new HashSet();
        IntSet bitSet = new BitSet();

        Random rnd = new Random();
        for (int n = 0; n < 100; n++) {
            for (int m = 0; m < rnd.nextInt(20); m++) {
                int input = rnd.nextInt(100) - 50;
                control.add(input);
                bitSet.set(input);
            }

            assertEquals(bitSet.size(), control.size());

            for (int m = 0; m < rnd.nextInt(10000000); m++) {
                int input = rnd.nextInt(10000) - 50;
                control.remove(input);
                bitSet.clear(input);
            }

            for (int m : control) {
                assertTrue(bitSet.test(m));
            }

            System.out.println("Control: " + control.toString());
            System.out.println("BitSet: " + bitSet);
            System.out.println("Max: " + bitSet.max());
            System.out.println("Min: " + bitSet.min());
            System.out.println();

            assertEquals(bitSet.size(), control.size());

        }
    }
}