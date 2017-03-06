import java.lang.reflect.Array;
import java.util.*;

/**
 * A set of integers implemented with an array
 */
public class ArraySet implements IntSet {

    int smallest = Integer.MAX_VALUE;
    int largest = Integer.MIN_VALUE;
    int[] elements;
    int elementCount;
    private int modCount;

    @Override
    public boolean test(int n) {
        if (n < smallest || n > largest) return false;
        for (int i = 0; i < elementCount; i++)
            if (elements[i] == n) return true;
        return false;
    }

    @Override
    public void clear(int n) {
        for (int i = 0; i < elementCount; i++)
            if (elements[i] == n) {
                elements[i] = elements[elementCount - 1];
                elementCount--;
                modCount++;
                if (n == smallest) {
                    smallest = Integer.MAX_VALUE;
                    for (int k = 0; k < elementCount; k++)
                        smallest = Math.min(elements[k], smallest);
                }
                if (n == largest) {
                    largest = Integer.MIN_VALUE;
                    for (int k = 0; k < elementCount; k++)
                        largest = Math.max(elements[k], largest);
                }
            }
    }

    @Override
    public void set(int n) {
        if (!test(n)) {
            if (elements == null) {
                elements = new int[10];
            } else if (elements.length == elementCount) {
                elements = Arrays.copyOf(elements, 2 * elements.length);
            }
            elements[elementCount] = n;
            smallest = Math.min(smallest, n);
            largest = Math.max(largest, n);
            elementCount++;
            modCount++;
        }
    }

    @Override
    public int min() {
        return smallest;
    }

    @Override
    public int max() {
        return largest;
    }

    @Override
    public int size() {
        return elementCount;
    }


    /**
     * Finds the new minimum and maximum with a simple brute force search
     */
    private void updateMinAndMax() {
        if (elementCount <= 0)
            return;

        int min = elements[0];
        int max = elements[0];
        for (int i = 0; i < elementCount; i++) {
            if (elements[i] < min)
                min = elements[i];

            if (elements[i] > max)
                max = elements[i];
        }

        smallest = min;
        largest = max;
    }

    /**
     * An iterator
     *
     * @return a new iterator
     */
    public Iterator<Integer> iterator() {
        return new ArraySetIterator();
    }

    /**
     * Iterator for ArraySet
     */
    private class ArraySetIterator implements Iterator<Integer> {

        private int nextIndex;
        private int expectedModCount;
        private boolean lastCalledNext;
        List<Integer> removedIndexes;

        /**
         * Initializes instance variables
         */
        public ArraySetIterator() {
            nextIndex = 0;
            expectedModCount = modCount;
            lastCalledNext = false;
            removedIndexes = new ArrayList<>();
        }

        @Override
        public boolean hasNext() {
            return nextIndex < elementCount + removedIndexes.size();
        }

        @Override
        public Integer next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            else if (!hasNext())
                throw new NoSuchElementException();
            else
                lastCalledNext = true;

            try {
                if (nextIndex >= elementCount)
                    return removedIndexes.remove(0);
            } catch (NullPointerException ex) {
                throw new ConcurrentModificationException();
            }

            try {
                return elements[nextIndex++];
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void remove() {
            if (!lastCalledNext)
                throw new IllegalStateException("Remove must only be called right after next has been called.");
            else if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            else
                lastCalledNext = false;

            try {
                int elementRemoved = (elements[nextIndex - 1] = elements[elementCount - 1]);
                removedIndexes.add(nextIndex - 1);
                expectedModCount = ++modCount;
                elementCount--;

                if (elementRemoved == smallest || elementRemoved == largest)
                    updateMinAndMax();
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

    }

}
