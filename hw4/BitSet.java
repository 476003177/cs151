import java.util.Arrays;

public class BitSet implements IntSet {

    int[] elements;
    int elementCount;

    int start;
    boolean startIsSet;

    public BitSet() {
        elements = new int[10];
        elementCount = 0;

        startIsSet = false;
    }

    @Override
    public boolean test(int n)
    {
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
        n -= start;
        int indexInElements = n / 32;
        int bitIndexInPack = n % 32;

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
            int newElementsLength = Math.max(elements.length * 2, indexOfNewElement);
            elements = Arrays.copyOf(elements, newElementsLength);
        }
    }

    private void moveStartBack(int indexOfNewElement) {
        if (indexOfNewElement >= 0)
            return;

        indexOfNewElement = indexOfNewElement == 24 ? 25 : indexOfNewElement;

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

        return "[" + ret.substring(0, Math.max(0, ret.length())) + "]";
    }

    // Don't change any of these (but add javadoc)

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


}