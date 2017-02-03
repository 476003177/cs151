import java.util.ArrayList;
import java.util.List;

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
        for (int i = 0; i < a.size(); i++) {
            if (largestIndex < 0 || a.get(i) > a.get(largestIndex)) {
                largestIndex = i;
            }
        }

        return largestIndex;
    }

    public static int findSecondSmallest(ArrayList<Integer> a) {
        int smallestIndex = -1;
        for (int i = 0; i < a.size(); i++) {
            if (smallestIndex < 0 || a.get(i) < a.get(smallestIndex)) {
                smallestIndex = i;
            }
        }

        System.out.println(smallestIndex);
        int secondSmallestIndex = -1;
        for (int i = 0; i < a.size(); i++) {
            if ((a.get(i) > a.get(smallestIndex)) && (secondSmallestIndex < 0 || a.get(i) < a.get(secondSmallestIndex))) {
                System.out.println(i);
                secondSmallestIndex = i;
            }
        }

        return secondSmallestIndex;
    }

    public static void swap(ArrayList<Integer> a, int firstEl, int secondEl) {
        int temp = a.get(firstEl);
        a.set(firstEl, a.get(secondEl));
        a.set(secondEl, temp);
    }

    public static void main(String[] args) {
        ArrayList<Integer> t1 = new ArrayList<>();
        t1.add(3);
        t1.add(1);
        t1.add(2);

        System.out.println(findSecondSmallest(t1));

    }

}