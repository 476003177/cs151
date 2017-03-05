import org.hamcrest.collection.IsArray;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class IteratorTest {

    /**
     * ArraySet iterate through all elements test.
     */
    @Test
    public void arraySetIterateThroughAllElements() {
        final int EL_START = -100; // Inclusive
        final int EL_END = 1000; // Exclusive

        ArraySet arraySet = new ArraySet();

        for (int n = EL_START; n < EL_END; n++)
            arraySet.set(n);

        Iterator<Integer> iterator = arraySet.iterator();
        for (int n = EL_START; iterator.hasNext(); n++)
            assertEquals(n, iterator.next().intValue());
    }

    /**
     * ArraySet iterate through all elements test.
     */
    @Test
    public void arraySetIterateThroughAllElementsRandom() {
        final int NUM_ELS = 500;
        final int MAX_NUM = 1000;

        ArraySet arraySet = new ArraySet();
        TreeSet<Integer> control = new TreeSet<>();

        Random rnd = new Random();
        for (int n = 0; n < NUM_ELS; n++) {
            int numToAdd = rnd.nextInt(MAX_NUM * 2) - MAX_NUM;

            arraySet.set(numToAdd);
            control.add(numToAdd);
        }

        assertEquals(arraySet.size(), control.size());

        Iterator<Integer> iterator = arraySet.iterator();
        Iterator<Integer> controlIterator = control.iterator();
        while (iterator.hasNext()) {
            assertTrue(control.contains(iterator.next()));
        }
        while (controlIterator.hasNext()) {
            assertTrue(arraySet.test(controlIterator.next()));
        }

        for (int n = -MAX_NUM; n < MAX_NUM; n++)
            assertTrue(arraySet.test(n) == control.contains(n));

    }

    /**
     * ArraySet iterator test remove
     */
    @Test
    public void arraySetRemove() {
        final int EL_START = 0; // Inclusive
        final int EL_END = 10; // Exclusive

        ArraySet arraySet = new ArraySet();
        TreeSet<Integer> control = new TreeSet<>();

        for (int n = EL_START; n < EL_END; n++) {
            arraySet.set(n);
            control.add(n);
        }

        Iterator<Integer> iterator = arraySet.iterator();
        iterator.next(); // 0
        iterator.remove();
        iterator.next(); // 1
        iterator.remove();
        iterator.next(); // 2
        iterator.next(); // 3
        iterator.next(); // 4
        iterator.remove();

        Iterator<Integer> controlIterator = control.iterator();
        controlIterator.next(); // 0
        controlIterator.remove();
        controlIterator.next(); // 1
        controlIterator.remove();
        controlIterator.next(); // 2
        controlIterator.next(); // 3
        controlIterator.next(); // 4
        controlIterator.remove();


        while (iterator.hasNext())
            assertEquals(controlIterator.next(), iterator.next());

        for (int n = EL_START; n < EL_END; n++)
            assertEquals(control.contains(n), arraySet.test(n));
    }

    /**
     * ArraySet test remove with randomness
     */
    @Test
    public void arraySetRemoveRandom() {
        final int NUM_ELS = 100;
        final int MAX_NUM = 1000;
        final double CHANCE_TO_REMOVE = 0.25;

        ArraySet arraySet = new ArraySet();
        TreeSet<Integer> control = new TreeSet<>();

        Random rnd = new Random();
        for (int n = 0; n < NUM_ELS; n++) {
            int numToAdd = rnd.nextInt(MAX_NUM * 2) - MAX_NUM;

            arraySet.set(numToAdd);
            control.add(numToAdd);
        }

        assertEquals(arraySet.size(), control.size());

        Iterator<Integer> iterator = arraySet.iterator();
        while (iterator.hasNext()) {
            int next = iterator.next();
            assertTrue(control.contains(next));
            if (rnd.nextInt((int) Math.round(1/CHANCE_TO_REMOVE)) == 0) {
                iterator.remove();
                control.remove(next);
            }
        }

        assertEquals(control.size(), arraySet.size());

        for (int n = -MAX_NUM; n < MAX_NUM; n++)
            assertTrue(arraySet.test(n) == control.contains(n));
    }

    /**
     * ArraySet iterator test remove throwing exception when first called
     */
    @Test(expected = IllegalStateException.class)
    public void arraySetRemoveBeforeCallingNext() {
        ArraySet arraySet = new ArraySet();

        for (int n = 0; n < 10; n++)
            arraySet.set(n);

        Iterator<Integer> iterator = arraySet.iterator();
        iterator.remove();
    }

    /**
     * ArraySet iterator test remove throwing exception when remove is called twice in a row
     */
    @Test(expected = IllegalStateException.class)
    public void arraySetRemoveTwiceInARow() {
        ArraySet arraySet = new ArraySet();

        for (int n = 0; n < 10; n++)
            arraySet.set(n);

        Iterator<Integer> iterator = arraySet.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        iterator.remove();
    }

    /**
     * BitSet iterate through all elements test.
     */
    @Test
    public void bitSetIterateThroughAllElements() {
        final int EL_START = -100; // Inclusive
        final int EL_END = 1000; // Exclusive

        BitSet bitSet = new BitSet();


        for (int n = EL_START; n < EL_END; n++)
            bitSet.set(n);

        Iterator<Integer> iterator = bitSet.iterator();
        for (int n = EL_START; iterator.hasNext(); n++)
            assertEquals(n, iterator.next().intValue());
    }

    /**
     * BitSet iterate through all elements test.
     */
    @Test
    public void bitSetIterateThroughAllElementsRandom() {
        final int NUM_ELS = 100;
        final int MAX_NUM = 1000;

        BitSet bitSet = new BitSet();
        TreeSet<Integer> control = new TreeSet<>();

        Random rnd = new Random();
        for (int n = 0; n < NUM_ELS; n++) {
            int numToAdd = rnd.nextInt(MAX_NUM * 2) - MAX_NUM;

            bitSet.set(numToAdd);
            control.add(numToAdd);
        }

        assertEquals(bitSet.size(), control.size());

        Iterator<Integer> iterator = bitSet.iterator();
        Iterator<Integer> controlIterator = control.iterator();
        while (iterator.hasNext()) {
            assertEquals(controlIterator.next(), iterator.next());
        }

        for (int n = -MAX_NUM; n < MAX_NUM; n++)
            assertTrue(bitSet.test(n) == control.contains(n));

    }

    /**
     * BitSet iterator test remove
     */
    @Test
    public void bitSetRemove() {
        final int EL_START = 0; // Inclusive
        final int EL_END = 10; // Exclusive

        BitSet bitSet = new BitSet();
        TreeSet<Integer> control = new TreeSet<>();

        for (int n = EL_START; n < EL_END; n++) {
            bitSet.set(n);
            control.add(n);
        }

        Iterator<Integer> iterator = bitSet.iterator();
        iterator.next(); // 0
        iterator.remove();
        iterator.next(); // 1
        iterator.remove();
        iterator.next(); // 2
        iterator.next(); // 3
        iterator.next(); // 4
        iterator.remove();

        Iterator<Integer> controlIterator = control.iterator();
        controlIterator.next(); // 0
        controlIterator.remove();
        controlIterator.next(); // 1
        controlIterator.remove();
        controlIterator.next(); // 2
        controlIterator.next(); // 3
        controlIterator.next(); // 4
        controlIterator.remove();


        while (iterator.hasNext())
            assertEquals(controlIterator.next(), iterator.next());

        for (int n = EL_START; n < EL_END; n++)
            assertEquals(control.contains(n), bitSet.test(n));
    }

    /**
     * BitSet test remove with randomness
     */
    @Test
    public void bitSetRemoveRandom() {
        final int NUM_ELS = 100;
        final int MAX_NUM = 1000;
        final double CHANCE_TO_REMOVE = 0.25;

        BitSet bitSet = new BitSet();
        TreeSet<Integer> control = new TreeSet<>();

        Random rnd = new Random();
        for (int n = 0; n < NUM_ELS; n++) {
            int numToAdd = rnd.nextInt(MAX_NUM * 2) - MAX_NUM;

            bitSet.set(numToAdd);
            control.add(numToAdd);
        }

        assertEquals(bitSet.size(), control.size());

        Iterator<Integer> iterator = bitSet.iterator();
        Iterator<Integer> controlIterator = control.iterator();
        while (iterator.hasNext()) {
            assertEquals(controlIterator.next(), iterator.next());
            if (rnd.nextInt((int) Math.round(1/CHANCE_TO_REMOVE)) == 0) {
                iterator.remove();
                controlIterator.remove();
            }
        }

        assertEquals(control.size(), bitSet.size());

        for (int n = -MAX_NUM; n < MAX_NUM; n++)
            assertTrue(bitSet.test(n) == control.contains(n));
    }

    /**
     * BitSet iterator test remove throwing exception when first called
     */
    @Test(expected = IllegalStateException.class)
    public void bitSetRemoveBeforeCallingNext() {
        BitSet bitSet = new BitSet();

        for (int n = 0; n < 10; n++)
            bitSet.set(n);

        Iterator<Integer> iterator = bitSet.iterator();
        iterator.remove();
    }

    /**
     * BitSet iterator test remove throwing exception when remove is called twice in a row
     */
    @Test(expected = IllegalStateException.class)
    public void bitSetRemoveTwiceInARow() {
        BitSet bitSet = new BitSet();

        for (int n = 0; n < 10; n++)
            bitSet.set(n);

        Iterator<Integer> iterator = bitSet.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        iterator.remove();
    }

}