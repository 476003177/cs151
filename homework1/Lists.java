import java.util.ArrayList;

public class Lists
{

    public static void swapLargestAndSecondSmallest(ArrayList<Integer> a)
    {
        int secondSmallestIndex = findSecondSmallest(a);
        int largestIndex = findLargest(a);

        if (secondSmallestIndex >= 0 && largestIndex >= 0)
            swap(a, secondSmallestIndex, largestIndex);
    }

    public static int findLargest(ArrayList<Integer> a) {
        int largestIndex = -1;
        for (int i = 0; i < a.length; i++) {
            if (largestIndex < 0 || a[i] > a.get(largestIndex)) {
                largestIndex = i;
            }
        }

        return largestIndex;
    }

    public static int findSecondSmallest(ArrayList<Integer> a) {
        int smallestIndex = -1;
        for (int i = 0; i < a.length; i++) {
            if (smallestIndex < 0 || a[i] < a.get(smallestIndex)) {
                smallestIndex = i;
            }
        }

        int secondSmallestIndex = -1;
        for (int i = 0; i < a.length; i++) {
            if ((secondSmallestIndex != smallestIndex) && (secondSmallestIndex < 0 || a[i] < a.get(secondSmallestIndex))) {
                secondSmallestIndex = i;
            }
        }

        return secondSmallestIndex;
    }

    public static void swap(ArrayList<Integer> a, int firstEl, int secondEl) {
        int temp = a.get(firstEl);
        a.set(firstEl, secondEl);
        a.set(secondEl, temp);
    }
    public static void main(String[] args) {

    }

}