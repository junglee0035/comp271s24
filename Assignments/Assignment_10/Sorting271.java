/**
 * The Sorting271 class provides methods for merging two sorted arrays
 * and sorting an array using the merge sort algorithm.
 */
public class Sorting271 {

    /**
     * Merges two sorted integer arrays into a single sorted array.
     *
     * @param left  the first sorted array
     * @param right the second sorted array
     * @return a single sorted array containing all elements of left and right
     */
    public int[] merge(int[] left, int[] right) {
        int leftLength = left.length;
        int rightLength = right.length;
        int[] mergedArray = new int[leftLength + rightLength];

        // Pointers for left array, right array, and merged array
        int leftIndex = 0, rightIndex = 0, mergedIndex = 0;

        // Traverse both arrays and merge them in sorted order
        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (left[leftIndex] <= right[rightIndex]) {
                mergedArray[mergedIndex] = left[leftIndex];
                leftIndex++;
            } else {
                mergedArray[mergedIndex] = right[rightIndex];
                rightIndex++;
            }
            mergedIndex++;
        }

        // Copy remaining elements from left array, if any
        while (leftIndex < leftLength) {
            mergedArray[mergedIndex] = left[leftIndex];
            leftIndex++;
            mergedIndex++;
        }

        // Copy remaining elements from right array, if any
        while (rightIndex < rightLength) {
            mergedArray[mergedIndex] = right[rightIndex];
            rightIndex++;
            mergedIndex++;
        }

        return mergedArray; 
    } //method merge

    /**
     * Sorts an array using the iterative merge sort algorithm.
     *
     * @param array the array to be sorted
     * @return the sorted array
     */
    public int[] sort(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0]; // Return empty array if input is null or empty
        }

        int arrayLength = array.length;
        // Array to hold intermediate merges
        int[] auxiliaryArray = new int[arrayLength];

        // Initialize width for merging segments
        for (int width = 1; width < arrayLength; width = 2 * width) {
            for (int i = 0; i < arrayLength; i += 2 * width) {
                int leftStart = i;
                int leftEnd = Math.min(i + width, arrayLength);
                int rightEnd = Math.min(i + 2 * width, arrayLength);

                int[] left = new int[leftEnd - leftStart];
                int[] right = new int[rightEnd - leftEnd];

                // Copy segments to be merged into left and right arrays
                System.arraycopy(array, leftStart, left, 0, left.length);
                System.arraycopy(array, leftEnd, right, 0, right.length);

                // Merge the left and right arrays
                int[] merged = merge(left, right);

                // Copy the merged array back into the original array
                System.arraycopy(merged, 0, array, leftStart, merged.length);
            }
        }

        return array;
    } // method sort

    /**
     * The main method to test the merge and sort methods.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Sorting271 sorter = new Sorting271();

        // Test arrays
        int[] a = {1, 2, 8, 9};
        int[] b = {0, 5, 6, 7};
        int[] arrayToSort = {10, 8, 5, 3, 9, 2, 7, 1};

        // Test merge method
        int[] mergedArray = sorter.merge(a, b);
        System.out.println("Merged array: " + java.util.Arrays.toString(mergedArray));

        // Test sort method
        int[] sortedArray = sorter.sort(arrayToSort);
        System.out.println("Sorted array: " + java.util.Arrays.toString(sortedArray));
    } // method main

} //class Sorting271
