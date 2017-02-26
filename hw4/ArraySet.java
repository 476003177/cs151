import java.util.Arrays;

/**
 * Created by Tyler on 2/21/2017.
 */
public class ArraySet implements IntSet {

    int[] elements;
    int elementCount;
    int smallest = Integer.MAX_VALUE;
    int largest = Integer.MIN_VALUE;

    public ArraySet() {
        elements = new int[10];
        elementCount = 0;
    }

    @Override
    public boolean test(int n) {
        return findElement(n) != -1;
    }

    @Override
    public void set(int n) {
        // Don't store the same element twice
        if (test(n))
            return;

        ensureCapacity();
        elements[elementCount++] = n;

        smallest = Math.min(n, smallest);
        largest = Math.max(n, largest);
    }

    @Override
    public void clear(int n) {
        int elIndex = findElement(n);
        boolean isSmallestOrLargest = elements[elIndex] == smallest || elements[elIndex] == largest;

        if (elIndex != -1) {
            elements[elIndex] = elements[elementCount - 1];
            elementCount--;
        }

        if (isSmallestOrLargest)
            updateMinAndMax();
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

    private void ensureCapacity() {
        if (elementCount >= elements.length)
            elements = Arrays.copyOf(elements, elements.length * 2);
    }

    private int findElement(int target) {
        for (int i = 0; i < elementCount; i++)
            if (elements[i] == target)
                return i;

        return -1;
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < elementCount; i++)
            ret += elements[i] + ", ";

        return "[" + ret.substring(0, Math.max(0, ret.length() - 2)) + "]";
    }

}
