import java.util.Arrays;

public class QuickSort {

    /**
     * @param arr
     *
     * Sort the array arr using quicksort with the 3-scan partition algorithm.
     * The quicksort algorithm is as follows:
     * 1. Select a pivot, partition array in place around the pivot.
     * 2. Recursively call quicksort on each subsection of the modified array.
     */
    public static int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    /**
     * @param arr
     * @param start
     * @param end
     *
     * Helper method for sort: runs quicksort algorithm on array from [start:end)
     */
    private static void quickSort(int[] arr, int start, int end) {
        // TODO: Implement quicksort
        if(start >= end) {
            return;
        }
        int[] partition = partition(arr, start, end);
        quickSort(arr, start, partition[0] - 1);
        quickSort(arr, partition[1] + 1, end);
    }

    /**
     * @param arr
     * @param start
     * @param end
     *
     * Partition the array in-place following the 3-scan partitioning scheme.
     * You may assume that first item is always selected as the pivot.
     * 
     * Returns a length-2 int array of indices:
     * [end index of "less than" section, start index of "greater than" section]
     *
     * Most of the code for quicksort is in this function
     */
    private static int[] partition(int[] arr, int start, int end) {
        // TODO: Implement partition
        int pivot = arr[start];  // Select the first element as pivot
        int lt = start;          // Index for less than pivot
        int gt = end;            // Index for greater than pivot
        int i = start + 1;       // Traverse the array

        while (i <= gt) {
            if (arr[i] < pivot) {
                swap(arr, lt++, i++);  // Move smaller elements to the left
            } else if (arr[i] > pivot) {
                swap(arr, i, gt--);    // Move larger elements to the right
            } else {
                i++;  // If equal to pivot, just move to the next element
            }
        }

        return new int[]{lt, gt};  // Return the boundaries for the pivot
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}   
