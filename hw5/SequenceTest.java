import static org.junit.Assert.*;
import org.junit.Test;

/**
 * For testing the IntSequence class
 */
public class SequenceTest {

    /**
     * Horstmann's test on hw5
     */
    @Test
    public void testAlternateHorstmann() {
        BitSet bitSet = new BitSet();
        ArraySet arraySet = new ArraySet();

        bitSet.set(1);
        bitSet.set(2);
        bitSet.set(4);
        bitSet.set(8);
        bitSet.set(11);

        arraySet.set(7);
        arraySet.set(5);
        arraySet.set(9);

        IntSequence bitSetSequence = IntSequence.fromIterator(bitSet.iterator());
        IntSequence arraySetSequence = IntSequence.fromIterator(arraySet.iterator());

        IntSequence alternateSequence = bitSetSequence.alternate(arraySetSequence);

        assertEquals(1, alternateSequence.next().intValue());
        assertEquals(7, alternateSequence.next().intValue());
        assertEquals(2, alternateSequence.next().intValue());
        assertEquals(5, alternateSequence.next().intValue());
        assertEquals(4, alternateSequence.next().intValue());
        assertEquals(9, alternateSequence.next().intValue());
        assertEquals(8, alternateSequence.next().intValue());
        assertEquals(11, alternateSequence.next().intValue());
        assertEquals(null, alternateSequence.next());
        assertEquals(null, alternateSequence.next());
        assertEquals(null, alternateSequence.next());
    }

    /**
     * Test first sequence empty
     */
    @Test
    public void testAlternateFirstSequenceEmpty() {
        BitSet bitSet = new BitSet();
        ArraySet arraySet = new ArraySet();

        arraySet.set(7);
        arraySet.set(5);
        arraySet.set(9);

        IntSequence bitSetSequence = IntSequence.fromIterator(bitSet.iterator());
        IntSequence arraySetSequence = IntSequence.fromIterator(arraySet.iterator());

        IntSequence alternateSequence = bitSetSequence.alternate(arraySetSequence);

        assertEquals(7, alternateSequence.next().intValue());
        assertEquals(5, alternateSequence.next().intValue());
        assertEquals(9, alternateSequence.next().intValue());
        assertEquals(null, alternateSequence.next());
        assertEquals(null, alternateSequence.next());
        assertEquals(null, alternateSequence.next());
    }

    /**
     * Test other sequence empty
     */
    @Test
    public void testAlternateOtherSequenceEmpty() {
        BitSet bitSet = new BitSet();
        ArraySet arraySet = new ArraySet();

        bitSet.set(1);
        bitSet.set(2);
        bitSet.set(4);
        bitSet.set(8);
        bitSet.set(11);

        IntSequence bitSetSequence = IntSequence.fromIterator(bitSet.iterator());
        IntSequence arraySetSequence = IntSequence.fromIterator(arraySet.iterator());

        IntSequence alternateSequence = bitSetSequence.alternate(arraySetSequence);

        assertEquals(1, alternateSequence.next().intValue());
        assertEquals(2, alternateSequence.next().intValue());
        assertEquals(4, alternateSequence.next().intValue());
        assertEquals(8, alternateSequence.next().intValue());
        assertEquals(11, alternateSequence.next().intValue());
        assertEquals(null, alternateSequence.next());
        assertEquals(null, alternateSequence.next());
        assertEquals(null, alternateSequence.next());
    }

    /**
     * Test only one element in first sequence
     */
    @Test
    public void testAlternateOneElementFirstSequence() {
        BitSet bitSet = new BitSet();
        ArraySet arraySet = new ArraySet();

        bitSet.set(1);

        IntSequence bitSetSequence = IntSequence.fromIterator(bitSet.iterator());
        IntSequence arraySetSequence = IntSequence.fromIterator(arraySet.iterator());

        IntSequence alternateSequence = bitSetSequence.alternate(arraySetSequence);

        assertEquals(1, alternateSequence.next().intValue());
        assertEquals(null, alternateSequence.next());
        assertEquals(null, alternateSequence.next());
        assertEquals(null, alternateSequence.next());
    }

    /**
     * Test only one element in other sequence
     */
    @Test
    public void testAlternateOneElementOtherSequence() {
        BitSet bitSet = new BitSet();
        ArraySet arraySet = new ArraySet();

        arraySet.set(7);

        IntSequence bitSetSequence = IntSequence.fromIterator(bitSet.iterator());
        IntSequence arraySetSequence = IntSequence.fromIterator(arraySet.iterator());

        IntSequence alternateSequence = bitSetSequence.alternate(arraySetSequence);

        assertEquals(7, alternateSequence.next().intValue());
        assertEquals(null, alternateSequence.next());
        assertEquals(null, alternateSequence.next());
        assertEquals(null, alternateSequence.next());
    }

    /**
     * Test other sequence longer
     */
    @Test
    public void testAlternateOtherSequenceLonger() {
        BitSet bitSet = new BitSet();
        ArraySet arraySet = new ArraySet();

        bitSet.set(2);
        bitSet.set(4);
        bitSet.set(8);
        bitSet.set(11);

        arraySet.set(7);
        arraySet.set(5);
        arraySet.set(9);
        arraySet.set(10);
        arraySet.set(1);
        arraySet.set(25);
        arraySet.set(17);

        IntSequence bitSetSequence = IntSequence.fromIterator(bitSet.iterator());
        IntSequence arraySetSequence = IntSequence.fromIterator(arraySet.iterator());

        IntSequence alternateSequence = bitSetSequence.alternate(arraySetSequence);

        assertEquals(2, alternateSequence.next().intValue());
        assertEquals(7, alternateSequence.next().intValue());
        assertEquals(4, alternateSequence.next().intValue());
        assertEquals(5, alternateSequence.next().intValue());
        assertEquals(8, alternateSequence.next().intValue());
        assertEquals(9, alternateSequence.next().intValue());
        assertEquals(11, alternateSequence.next().intValue());
        assertEquals(10, alternateSequence.next().intValue());
        assertEquals(1, alternateSequence.next().intValue());
        assertEquals(25, alternateSequence.next().intValue());
        assertEquals(17, alternateSequence.next().intValue());
        assertEquals(null, alternateSequence.next());
        assertEquals(null, alternateSequence.next());
        assertEquals(null, alternateSequence.next());
    }

}