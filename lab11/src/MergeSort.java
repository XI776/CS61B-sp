import java.util.Arrays;

public class MergeSort {


    /**
     * @param arr
     *
     * Sort the array arr using merge sort.
     * The merge sort algorithm is as follows:
     * 1. Split the collection to be sorted in half.
     * 2. Recursively call merge sort on each half.
     * 3. Merge the sorted half-lists.
     *
     */
    public static int[] sort(int[] arr) {
        // TODO: Implement merge sort
        int n = arr.length;
        if(n == 1) {
            return arr;
        }

        // Split the array into two halves
        int mid = n / 2;
        int[] newArr1 = Arrays.copyOfRange(arr, 0, mid);  // Copy the first half
        int[] newArr2 = Arrays.copyOfRange(arr, mid, n);   // Copy the second half

        newArr1 = sort(newArr1);
        newArr2 = sort(newArr2);
        return merge(newArr1,newArr2);
    }
    public static void main(String[] args) {
        int[] arr = {5, 1, 23, 5, 6, 23, 134, 5, 0, 12};
        int[] arr1 = sort(arr);
        for(int i = 0; i < arr1.length; i++){
            System.out.print(arr1[i] + " ");
        }
    }
    /**
     * @param a
     * @param b
     *
     * Merge the sorted half-lists.
     *
     * Suggested helper method that will make it easier for you to implement merge sort.
     */
    private static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        // TODO: Implement merge
        int start1 = 0;
        int start2 = 0;
        for(int i = 0; i < a.length + b.length; i++) {
            if(start1 < a.length && start2 < b.length) {
                if(a[start1] <= b[start2]) {
                    c[i] = a[start1];
                    start1++;
                } else {
                    c[i] = b[start2];
                    start2++;
                }
            } else if(start1 < a.length) {
                c[i] = a[start1];
                start1++;
            } else if(start2 < b.length) {
                c[i] = b[start2];
                start2++;
            }
        }
        return c;
    }
}

