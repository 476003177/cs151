public class Arrays
{
   public static void swapLargestAndSmallest(int[] a)
   {
     int smallestIndex = findSmallest(a);
     int largestIndex = findLargest(a);
     
     if (smallestIndex >= 0 && largestIndex >= 0)
      swap(a, smallestIndex, largestIndex);
   }
   
   public static int findLargest(int[] a) {
     int largestIndex = -1;
     for (int i = 0; i < a.length; i++) {
       if (largestIndex < 0 || a[i] > a[largestIndex]) {
         largestIndex = i;
       }
     }
     
     return largestIndex;
   }
   
   public static int findSmallest(int[] a) {
     int smallestIndex = -1;
     for (int i = 0; i < a.length; i++) {
       if (smallestIndex < 0 || a[i] < a[smallestIndex]) {
         smallestIndex = i;
       }
     }
     
     return smallestIndex;
   }
   
   public static void swap(int[] a, int firstEl, int secondEl) {
     int temp = a[firstEl];
     a[firstEl] = a[secondEl];
     a[secondEl] = temp;
   }
   
}