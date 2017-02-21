import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Tyler on 2/21/2017.
 */
public class ArraySet implements IntSet {

    int[] elements;
    int elementCount;
    int smallest = Integer.MAX_VALUE;
    int largest = Integer.MIN_VALUE;

    private HashSet<Integer> emptySpots;

    public ArraySet() {
        elements = new int[10];
        elementCount = 0;
        emptySpots = new HashSet<>();
    }

    @Override
    public boolean test(int n) {
        return findElement(n) != -1;
    }

    @Override
    public void set(int n) {
        ensureCapacity();

        int elIndex = (emptySpots.size() > 0) ? emptySpots.iterator().next() : elementCount++;

        elements[elIndex] = n;
        largest = Math.max(n, largest);
        smallest = Math.min(n, smallest);
    }

    @Override
    public void clear(int n) {
        int elIndex = findElement(n);
        if (elIndex != -1)
            emptySpots.add(elIndex);

        elementCount--;
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

    private void ensureCapacity() {
        if (elementCount >= elements.length)
            elements = Arrays.copyOf(elements, elements.length * 2);
    }

    private int findElement(int target) {
        for (int i = 0; i < elementCount; i++)
            if (!emptySpots.contains(i) && elements[i] == target)
                return i;

        return -1;
    }

}
