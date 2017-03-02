import java.util.Arrays;

/**
 * A set of integers implemented with a bit set
 */
public class BitSet implements IntSet {

    int[] elements;
    int elementCount;

    int start;
    boolean startIsSet;

    /**
     * Initializes instance variables
     */
    public BitSet() {
        elements = new int[10];
        elementCount = 0;

        startIsSet = false;
    }

    @Override
    public boolean test(int n)
    {
        if (!startIsSet)
            return false;

        int bitDistanceFromStart = n - start;
        int indexInElements = bitDistanceFromStart / 32;
        int bitIndexInPack = bitDistanceFromStart % 32;

        // Test if out of range of the object
        if (bitDistanceFromStart < 0 || bitDistanceFromStart >= elements.length * 32)
            return false;

        return test(elements[indexInElements], bitIndexInPack);
    }

    @Override
    public void set(int n)
    {
        if (!startIsSet) {
            start = n;
            startIsSet = true;
        }

        int bitDistanceFromStart = n - start;
        int indexInElements = (int) Math.floor(bitDistanceFromStart / 32.0);
        ensureCapacity(indexInElements);

        // Have to recalculate bitDistanceFromStart and indexInElements in case start changed
        bitDistanceFromStart = n - start;
        indexInElements = bitDistanceFromStart / 32;

        int bitIndexInPack = bitDistanceFromStart % 32;

        int newElementValue = set(elements[indexInElements], bitIndexInPack);
        if (elements[indexInElements] != newElementValue) {
            elements[indexInElements] = set(elements[indexInElements], bitIndexInPack);
            elementCount++;
        }
    }

    @Override
    public void clear(int n)
    {
        if (!startIsSet)
            return;

        int bitDistanceFromStart = n - start;
        int indexInElements = (int) Math.floor(bitDistanceFromStart / 32.0);
        int bitIndexInPack = bitDistanceFromStart % 32;

        if (indexInElements < 0 || indexInElements >= elements.length)
            return;

        int newElementValue = clear(elements[indexInElements], bitIndexInPack);
        if (elements[indexInElements] != newElementValue) {
            elements[indexInElements] = clear(elements[indexInElements], bitIndexInPack);
            elementCount--;
        }
    }

    @Override
    public int min()
    {
        if (!startIsSet)
            return 0;

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < 32; j++) {
                if (test(elements[i], j))
                    return start + 32 * i + j;
            }
        }

        return Integer.MAX_VALUE;
    }

    @Override
    public int max()
    {
        if (!startIsSet)
            return 0;

        for (int i = elements.length - 1; i >= 0; i--) {
            for (int j = 31; j >= 0; j--) {
                if (test(elements[i], j))
                    return start + 32 * i + j;
            }
        }

        return Integer.MAX_VALUE;
    }

    /**
     * Ensures that the object will have enough space to store a new element
     * @param indexOfNewElement The would-be index if the new element was inserted
     */
    private void ensureCapacity(int indexOfNewElement) {
        if (indexOfNewElement < 0) {
            moveStartBack(indexOfNewElement);
        }

        if (indexOfNewElement >= elements.length) {
            int newElementsLength = Math.max(elements.length * 2, indexOfNewElement + 1);
            elements = Arrays.copyOf(elements, newElementsLength);
        }
    }

    /**
     * Shifts the set back to accommodate for new element
     * @param indexOfNewElement the index of the new element of which to accommodate for
     */
    private void moveStartBack(int indexOfNewElement) {
        if (indexOfNewElement >= 0)
            return;

        int lengthToAdd = Math.max(elements.length, Math.abs(indexOfNewElement));
        int[] newElements = new int[elements.length + lengthToAdd];

        for (int i = 0; i < elements.length; i++) {
            newElements[i + lengthToAdd] = elements[i];
        }

        start = Math.min(start - (32 * elements.length), start + (32 * indexOfNewElement));
        elements = newElements;
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < 32; j++) {
                ret += test(elements[i], j) ? (start + 32 * i + j) + ", " : "";
            }
        }

        return "[" + ret.substring(0, Math.max(0, ret.length() - 2)) + "]";
    }

    // Don't change any of these (but add javadoc)

    @Override
    public int size()
    {
        return elementCount;
    }

    /**
     * Checks if the bit at i is in n
     * @param n the integer to check bits of
     * @param i the index of the bit in n
     * @return true if bit at i is in n
     */
    private static boolean test(int n, int i)
    {
        assert 0 <= i && i < 32;
        return (n & (1 << i)) != 0;
    }

    /**
     * Sets the bit at i in n to 1
     * @param n the integer to set the bit of
     * @param i the index of the bit in n
     * @return the new n after bit at i was set to 1
     */
    private static int set(int n, int i)
    {
        assert 0 <= i && i < 32;
        return n | (1 << i);
    }

    /**
     * Sets the bit at i in n to 0
     * @param n the integer to set the bit of
     * @param i the index of the bit in n
     * @return the new n after bit at i was set to 0
     */
    private static int clear(int n, int i)
    {
        assert 0 <= i && i < 32;
        return n & ~(1 << i);
    }


}