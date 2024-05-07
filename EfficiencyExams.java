
/**
 * There a list of efficiency exercises from exams of the open university 
 * to use maximaize the usage click ctrl f and write the name of function you need 
 *
 * @author (Or saban)
 * @version (a version number or a date)
 */
public class EfficiencyExams
{
    /**
     *  Efficiency Exam 2023Bmoed 79.
     *  
     *  This method searches for a missing number in a sorted array of integers.
     *  It assumes that the array contains a sequence of consecutive integers, except for one missing number.
     *  
     *  @param arr The input array of integers.
     *  @return The missing number if found, or Integer.MIN_VALUE if not found.
     */
    public static int findNumber(int[] arr) {
        int high = 0; // Initialize the index for the highest element
        int low = arr.length - 1; // Initialize the index for the lowest element
        int mid; // Declare the index for the middle element

        // Binary search algorithm to find the missing number
        while (high <= low) {
            mid = (high + low) / 2; // Calculate the index of the middle element

            // Check if there is space for more numbers on the left side
            if (mid < arr.length - 1 && arr[mid] - arr[mid + 1] > 1) {
                return arr[mid] - 1; // Return the missing number
            } else if (mid > 0 && arr[mid - 1] - arr[mid] > 1) { // Check if there is space for more numbers on the right side
                return arr[mid] + 1; // Return the missing number
            }

            // Adjust the search range based on the differences between elements
            if (arr[mid] - arr[low] == low - mid) {
                low = mid - 1; // Cut the left side of the array
            } else {
                high = mid + 1; // Cut the right side of the array
            }
        }

        return Integer.MIN_VALUE; // Return Integer.MIN_VALUE if the missing number is not found

        // Time complexity: O(log n) - because the algorithm halves the search range in each iteration (binary search)
        // Space complexity: O(1) - there are only fixed parameters (high, low, mid)
    }

    /**
     *  Efficiency Exam 2023Bmoed 83.
     *  
     *  This method finds the length of the smallest subarray whose sum is greater than or equal to a given number.
     *  
     *  @param arr The input array of integers.
     *  @param num The target sum to find.
     *  @return The length of the smallest subarray whose sum is greater than or equal to 'num'.
     */
    public static int findSmallestSubarrayLen(int[] arr, int num) {
        int i = 0; // Start index (left one).
        int j = 0; // End index (right one).
        int sum = 0; // Initialize the sum of the current subarray.
        int minSubArrayLength = Integer.MAX_VALUE; // Initialize the length of the smallest subarray.
        int indexSaveI = 0; // Save the start index of the smallest subarray.
        int indexSaveJ = 0; // Save the end index of the smallest subarray.

        // Sliding window algorithm
        for (j = 0; j < arr.length; j++) {
            // Expand the window until the sum becomes greater than or equal to 'num'
            if (sum <= num) {
                sum += arr[j];
            }
            // Shrink the window if the sum exceeds 'num'
            while (sum > num) {
                // Check if the current subarray is smaller than the smallest found so far
                if (minSubArrayLength > j - i + 1) {
                    // Update the smallest subarray length and its start and end indexes
                    minSubArrayLength = j - i + 1;
                    indexSaveI = i;
                    indexSaveJ = j;
                }
                // Try to make the window smaller by moving the start index
                sum -= arr[i];
                i++;
            }
        }

        // Print the result or indicate if no subarray exists
        if (minSubArrayLength == Integer.MAX_VALUE) {
            System.out.println("No subarray exists");
            return 0;
        } else {
            System.out.println("Smallest subarray found [" + indexSaveI + "-" + indexSaveJ + "]");
        }

        // Return the length of the smallest subarray whose sum is greater than or equal to 'num'
        return minSubArrayLength;

        // Time complexity: O(n) - because we traverse the array once in the for loop, and possibly once again in the while loop.
        // Space complexity: O(1) - only fixed number of parameters are used.
    }

    //------------------//---------//------------------------------////////
    /**
     *  Efficiency Exam 2023Bmoed 81.
     *  
     *  This method finds the subarray whose elements sum up to a given target value.
     *  
     *  @param arr The input array of integers.
     *  @param target The target sum to find.
     */
    public static void findSubarray(int[] arr, int target) {
        int currentSum = 0; // Initialize the current sum of the subarray.
        int i = 0; // Start index of the subarray.
        int j = 0; // End index of the subarray.
        int minIndexI = arr.length; // Initialize the minimum start index of the subarray.
        int minIndexJ = arr.length; // Initialize the minimum end index of the subarray.

        // Sliding window technique
        while (j < arr.length) {
            // Expand the window if the current sum is less than the target
            if (currentSum < target && j < arr.length) {
                currentSum += arr[j];
                j++;
            } else {
                // Shrink the window if the current sum exceeds the target
                currentSum -= arr[i];
                i++;
            }

            // Check if the current sum equals the target
            if (currentSum == target) {
                // Update the minimum indexes if the current subarray is smaller
                if (i < minIndexI) {
                    minIndexI = i;
                    minIndexJ = j - 1;
                }
                // If the start index is the same, update the end index if it's smaller
                if (i == minIndexI && j < minIndexJ) {
                    minIndexJ = j - 1;
                }
            }
        }

        // Print the result or indicate if no subarray exists
        if (minIndexI == arr.length) {
            System.out.println("No subarray found with sum " + target);
        } else {
            System.out.println("Subarray found between indexes " + minIndexI + " and " + minIndexJ + " with sum " + target);
        }

        // Sliding window method is used for efficient search.
        // Time complexity: O(n) - because we traverse the array once using the sliding window.
        // Space complexity: O(1) - as only fixed number of parameters are used.
    }

    ///////
    /**
     * moed 2023A moed 72
     * 
     * This method finds a peak element in a given array using binary search.
     * A peak element is defined as an element that is greater than or equal to its neighbors.
     * 
     * @param arr The input array of integers.
     * @return The peak element if found, otherwise -1.
     */
    public static int findPeak(int[] arr) {
        // Check edge cases: first and last elements
        if (arr[0] >= arr[1]) {
            return arr[0];
        } else if (arr[arr.length - 1] >= arr[arr.length - 2]) {
            return arr[arr.length - 1];
        }

        // Initialize pointers for binary search
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;

        // Binary search loop
        while (left <= right) {
            mid = (left + right) / 2;

            // Check if the current element is a peak
            if (mid > 0 && mid < arr.length - 1 && arr[mid] >= arr[mid + 1] && arr[mid] >= arr[mid - 1]) {
                return arr[mid]; // Found a peak
            } else if (arr[mid + 1] >= arr[mid]) {
                left = mid + 1; // Peak might be on the right side
            } else {
                right = mid - 1; // Peak might be on the left side
            }
        }

        return -1; // No peak found
    }

    /**
     * 2022B moed 96
     * 
     * This method performs a modified binary search to find a target number in an array
     * where some elements might be missing. It tolerates "almost" correct elements (0s).
     * 
     * @param a The input array of integers.
     * @param num The target number to search for.
     * @return The index of the target number if found, otherwise -1.
     */
    public static int kAlmostSearch(int[] a, int num) {
        // Initialize pointers for binary search
        int low = 0;
        int high = a.length - 1;
        int mid = 0;
        int left = 0;
        int right = 0;

        // Binary search loop
        while (low <= high) {
            mid = (low + high) / 2;

            // Handle case where the middle element is 0
            if (a[mid] == 0) {
                left = mid - 1;
                right = mid + 1;
                // Search for the nearest non-zero element
                while (low <= left || high >= right) {
                    if (left >= low && a[left] != 0) {
                        mid = left;
                        break;
                    }
                    if (right <= high && a[right] != 0) {
                        mid = right;
                        break;
                    } else {
                        right++;
                        left--;
                    }
                }
            }

            // Check if the target number is found
            if (a[mid] == num) {
                return mid;
            }
            // Check if the middle element is 0
            if (a[mid] == 0) {
                return -1; // Target number cannot be found if middle element is 0
            }
            // Adjust the search range based on the comparison with the target number
            if (a[mid] > num) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1; // Target number not found
    }

    /**
     * 2022B moed 96
     * 
     * This method finds the maximum product of a triplet in an integer array.
     * 
     * @param arr The input array of integers.
     * @return The maximum product of a triplet.
     * @timecomplexity O(n)
     * @spacecomplexity O(1)
     */
    public static int findTriplet(int[] arr) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        // Iterate through the array to find the top three maximum and two minimum elements
        for (int i = 0; i < arr.length; i++) {
            if (max1 < arr[i]) {
                max3 = max2;
                max2 = max1;
                max1 = arr[i];
            } else if (max2 < arr[i]) {
                max3 = max2;
                max2 = arr[i];
            } else if (max3 < arr[i]) {
                max3 = arr[i];
            }

            if (min1 > arr[i]) {
                min2 = min1;
                min1 = arr[i];
            } else if (min2 > arr[i]) {
                min2 = arr[i];
            }
        }

        // Calculate the maximum product of the triplet using the top three maximum and two minimum elements
        int calcMaxPositive = max1 * max2 * max3;
        int calcMaxNegative = max1 * min1 * min2;

        // Return the maximum of the two products
        return Math.max(calcMaxPositive, calcMaxNegative);
    }

    /**
     * moed 2022B moed 94
     * 
     * This method sorts an integer array based on the modulo operation with a given divisor.
     * 
     * @param a The input array of integers to be sorted.
     * @param k The divisor used for the modulo operation.
     * @timecomplexity O(n) where n is the length of the array.
     * @spacecomplexity O(1)
     */
    public static void sortmod(int[] a, int k) {
        int temp = 0;
        int countLocation = 0;

        // Iterate through each modulo from 0 to k - 1
        for (int i = 0; i < k; i++) {
            // Iterate through the array to find elements with the current modulo
            for (int j = 0; j < a.length; j++) {
                // Swap the current element with the next available position for its modulo
                if (a[j] % k == i) {
                    temp = a[countLocation];
                    a[countLocation] = a[j];
                    a[j] = temp;
                    countLocation++;
                }
            }
        }
        return;
    }
    ///////////
    /**
     * moed 2022 A moed 94
     * 
     * This method finds the length of the longest subarray where adjacent elements have opposite signs.
     * 
     * @param a The input array of integers.
     * @return The length of the longest subarray with alternating signs.
     * @timecomplexity O(n) where n represents the length of the array.
     * @spacecomplexity O(1)
     */
    public static int longestSubarray(int[] a) {
        int start = 0;
        int end = 0;
        int bestStart = 0;
        int bestEnd = 0;
        int i = 1;

        // Iterate through the array to find the longest subarray with alternating signs
        while (i < a.length) {
            if (i < a.length - 1 && a[i] * a[i + 1] < 0) {
                end = i + 1;
            } else if (bestEnd - bestStart < end - start) {
                bestEnd = end;
                bestStart = start;
                start = i;
                end = i + 1;
            } else {
                start = i + 1;
            }
            i++;
        }

        // Print the starting and ending indexes of the longest subarray
        System.out.println("Starting index = " + bestStart + " Ending index = " + bestEnd);

        // Return the length of the longest subarray with alternating signs
        return bestEnd - bestStart + 1;
    }

    ///////////////
    //////////////////////
    /**
     * moed 2022B moed 86
     * 
     * This method finds a duplicate element in an integer array.
     * 
     * @param a The input array of integers.
     * @return The index of the duplicate element, or -1 if no duplicate is found.
     * @timecomplexity O(n)
     * @spacecomplexity O(1)
     */
    public static int findDuplicate(int[] a) {
        // Iterate through the array to mark the indices of visited elements
        for (int i = 0; i < a.length; i++) {
            int index = (a[i] % a.length); // Calculate the index to place the current number
            a[index] += a.length; // Add a large number to mark the visited index
            // This way, if a place has two equal numbers, it will have a value of at least 2n
        }

        // Iterate through the array to find the index of the duplicate element
        for (int i = 0; i < a.length; i++) {
            if ((a[i] / a.length) >= 2) { // Check if the value at the index indicates a duplicate
                return i; // Return the index of the duplicate element
            }
        }
        return -1; // Return -1 if no duplicate is found
    }

    ////////////////////////////////////////////////////////////////////////////////
    /**
     * moed 2021B moed 92
     * 
     * This method finds the length of the longest subarray containing at most k zeros.
     * 
     * @param a The input array of integers.
     * @param k The maximum number of zeros allowed in the subarray.
     * @return The length of the longest subarray.
     */
    public static int longestSequence(int[] a, int k) {
        int i = 0; // Pointer for the start of the subarray
        int j = 0; // Pointer for the end of the subarray
        int maxLength = 0; // Variable to store the length of the longest subarray

        // Loop through the array using two pointers (i and j)
        while (j < a.length) {
            // If the element at index j is 0, decrement k
            if (a[j] == 0) {
                k--;
            }

            // If k becomes negative, adjust the subarray
            if (k < 0) {
                // Move the start pointer (i) to the right until k is non-negative
                if (a[i] == 0) {
                    k++;
                }
                i++;
            }

            j++; // Move the end pointer (j) to the right
        }

        // The length of the longest subarray is the difference between j and i
        return j - i;
    }

    /**
     * moed 2021B moed 60
     * 
     * This method finds the length of the smallest subarray whose sum is less than or equal to k.
     * 
     * @param a The input array of integers.
     * @param k The target sum.
     * @return The length of the smallest subarray.
     */
    public static int smallestSub(int[] a, int k) {
        int i = 0; // Pointer for the start of the subarray
        int j = 0; // Pointer for the end of the subarray
        int sum = 0; // Variable to store the current sum of the subarray
        int min = a.length + 1; // Variable to store the length of the smallest subarray, initialized to a large value

        // Iterate through the array using two pointers (i and j)
        while (i < a.length && j < a.length) {
            // If the current sum is less than or equal to k, expand the subarray to the right
            if (sum <= k) {
                sum += a[j];
                j++;
            } else { // If the current sum exceeds k, shrink the subarray from the left
                min = Math.min(min, j - i); // Update the length of the smallest subarray
                sum -= a[i];
                i++;
            }
        }

        // After exiting the loop, check if the subarray needs further shrinking
        while (sum > k && i < a.length) {
            min = Math.min(min, j - i); // Update the length of the smallest subarray
            sum -= a[i];
            i++;
        }

        return min; // Return the length of the smallest subarray
    }

    ////////////////////////////////////////////////////////////
    /**
     * moed 2021 A moed 67
     * time complexity - O(log(n))
     * space complexity - O(1)
     */
    public static int findMissingIndex(int [] a){
        int diff = a[1]-a[0];
        int low = 0;
        int high = a.length-1;
        int mid = 0;
        if(diff != a[2] - a[1]){
            return 1;  
        }
        int totalDistance = 0;
        while(low <= high){
            mid = (low + high)/2;
            totalDistance = (mid*diff) + a[0];
            if(mid < a.length-1 && a[mid+1] - a[mid] > diff){
                return mid;    
            }
            else if(mid > 0 && a[mid] - a[mid-1] > diff){
                return mid;
            }
            else if(totalDistance == a[mid]){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return a.length; 
    }
    ///////////////////////////////////
    /**
     * moed 2021A moed 85 corona
     * 
     * This method determines whether there exists a contiguous subarray in the input array 
     * whose average equals a given value x.
     *  Time complexity - O(n)
     *  Space complexity - O(1)
     * @param arr The input array of integers.
     * @param x The target average.
     * @return True if there exists such a subarray, false otherwise.
     */
    public static boolean findAverage(int[] arr, double x) {
        int divine = arr.length; // Variable to store the number of elements in the subarray
        int sum = 0; // Variable to store the sum of elements in the array
        double average = 0; // Variable to store the current average

        // Calculate the sum of all elements in the array
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        int start = 0; // Pointer for the start of the subarray
        int end = arr.length - 1; // Pointer for the end of the subarray

        // Iterate until the subarray size is non-zero and the array bounds are valid
        while (start <= end && divine > 0) {
            average = (double) sum / divine; // Calculate the average of the current subarray

            // If the average equals x, print the indices of the subarray and return true
            if (average == x) {
                System.out.println(start + " --- " + end);
                return true;
            } else if (average > x) { // If the average is greater than x, decrease the sum from the right
                sum -= arr[end];
                end--;
            } else { // If the average is less than x, decrease the sum from the left
                sum -= arr[start];
                start++;
            }
            divine--; // Decrement the number of elements in the subarray
        }

        System.out.println("There is no average of x in the array");
        return false; // Return false if no subarray with the required average is found
    }

    ///////////////////////////////////////////////////////
    /*This code in note because it using external classes that are not in use here*/
    /**
     * Finds the minimal positive integer in an array of ranges.
     *
     * This method takes an array of Range objects and searches for the minimal positive integer
     * within these ranges. The ranges are defined by objects of type Range, which are not implemented
     * here.
     *
     * Note: The code for this method is commented out as it relies on external classes (Range) that
     * are not included here.
     *
     * @param rangeA An array of Range objects defining the ranges to search within.
     * @return The minimal positive integer found within the ranges. Returns -1 if no positive integer
     *         is found.
     */
    /**
     * moed 2020B moed 98

    public static int minimalPositive(Range[] rangeA){
    int left = 0;
    int right = rangeA.length-1;
    int mid = 0;
    while(left <= right){
    mid = (left + right)/2;
    if(rangeA[mid].getBig <= 0){
    left = mid+1;
    }
    else{
    right = mid;
    }
    }
    left = rangeA[mid].getSmall();
    right = rangeA[mid].getBig();
    while(left <right){
    mid = (left + right)/2;
    if(mid < 0){
    left = mid+1;
    }
    else{
    right = mid;
    }
    }
    if(right<1){
    return -1;
    }
    return right;
     */
    ////////////////////////////////////////////
    ///////////////////////////////////////////
    /**
     * Finds the sub-array with the minimum sum of size k.
     *
     * This method takes an array of integers and an integer k, and finds the sub-array of size k
     * with the minimum sum. It utilizes a sliding window algorithm to efficiently compute the
     * minimum sum sub-array.
     *
     * @param arr The input array of integers.
     * @param k The size of the sub-array.
     */
    public static void minimumSubK(int[] arr, int k) {
        // Implementation details:
        // This method utilizes a sliding window algorithm to efficiently find the sub-array
        // with the minimum sum of size k within the given array.

        // Initialize variables
        int i = 0;
        int j = 0;
        int currSum = 0;
        int minSum = Integer.MAX_VALUE;
        int startIndex = 0;
        int endIndex = 0;

        // Calculate the sum of the first k elements
        for (j = 0; j < k; j++) {
            currSum += arr[j];
        }

        // Update the minimum sum and corresponding indices
        minSum = currSum;
        startIndex = i;
        endIndex = j;

        // Move the sliding window and update the minimum sum
        for (j = k; j < arr.length; j++) {
            currSum -= arr[i]; // Move the window forward (remove the current start)
            i++; // Move to the next index (the new start)
            currSum += arr[j]; // Update the sum if within bounds
            if (currSum < minSum) { // Update the minimum sum and corresponding indices
                minSum = currSum;
                startIndex = i;
                endIndex = j;
            }
        }

        // Print the result
        System.out.println("Minimum sum sub-array is (" + startIndex + "," + endIndex + ") " + minSum);
        return;
    }

    ////////////////////////////
    /**
     * Prints triplets from the given array whose product is equal to a specified number.
     *
     * This method takes an array of integers and a target number and prints all the triplets
     * whose product is equal to the target number. It utilizes a nested loop to iterate over
     * all possible combinations of triplets in the array.
     *
     * @param a The input array of integers.
     * @param num The target number for which triplets are searched.
     */
    public static void printTriplets(int[] a, int num) {
        // Implementation details:
        // This method iterates over all possible combinations of triplets in the array and prints
        // the triplets whose product is equal to the specified target number.

        // Initialize variables
        int j, k;
        int triplets;

        // Iterate over each element as the first element of the triplet
        for (int i = 0; i < a.length; i++) {
            j = i + 1; // Initialize the second index of the triplet
            k = a.length - 1; // Initialize the third index of the triplet

            // Iterate over possible combinations of the second and third elements of the triplet
            while (j < k) {
                triplets = a[i] * a[j] * a[k]; // Calculate the product of the triplet
                if (triplets == num) { // If the product matches the target number, print the triplet
                    System.out.println(a[i] + "\t" + a[j] + "\t" + a[k]);
                    j++; // Move to the next index for the second element
                    k--; // Move to the previous index for the third element
                } else if (triplets < num) { // If the product is less than the target number, move to the next index for the second element
                    j++;
                } else { // If the product is greater than the target number, move to the previous index for the third element
                    k--;
                }
            }
        }
        return;
    }

    /**
     * Counts the total number of strictly increasing subsequences in the array.
     *
     * This method takes an array of integers and counts the total number of strictly increasing
     * subsequences. A subsequence is considered strictly increasing if each element is greater
     * than the previous one.
     *
     * @param a The input array of integers.
     * @return The total number of strictly increasing subsequences in the array.
     */
    public static int strictlyIncreasing(int[] a) {
        // Implementation details:
        // This method counts the total number of strictly increasing subsequences in the input array.

        // Initialize variables to track the total number of sequences and the current sequence length
        int totalSeq = 0;
        int sequence = 1;

        // Iterate through the array to count strictly increasing subsequences
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] < a[i + 1]) {
                totalSeq += sequence; // Increase the total count by the current sequence length
                sequence++; // Increment the current sequence length
            } else {
                sequence = 1; // Reset the sequence length if the next element is not greater
            }
        }

        // Return the total count of strictly increasing subsequences
        return totalSeq;
    }

    /////////////////////////////////
    /**
     * Finds if there exists a pair of elements in the array whose sum equals a specified value.
     *
     * This method takes an array of integers and a target sum and checks if there exists a pair
     * of elements in the array whose sum is equal to the specified target sum. It utilizes a
     * modified version of the Moznayim algorithm to efficiently find the pair.
     *
     * @param a The input array of integers.
     * @param sum The target sum to be matched.
     * @return true if a pair of elements exists whose sum equals the target sum, otherwise false.
     */
    public static boolean findSum(int[] a, int sum) {
        // Implementation details:
        // This method utilizes a modified version of the Moznayim algorithm to efficiently find
        // if there exists a pair of elements in the array whose sum is equal to the specified target sum.

        // Initialize pointers for the Moznayim algorithm
        int low = 0;
        int high = a.length - 1;

        // Find the pivot index where the array is divided into two sorted sub-arrays
        for (int i = 0; i < high; i++) {
            if (a[i] > a[i + 1]) {
                low = i + 1;
                high = i;
                break;
            }
        }

        // Use the Moznayim algorithm to find the pair whose sum equals the target sum
        while (low != high) {
            if (a[low] + a[high] == sum) {
                return true; // Pair found
            }
            if (a[low] + a[high] > sum) {
                high--;
                if (high == 0) {
                    high = a.length - 1;
                }
            }
            if (a[low] + a[high] < sum) {
                low++;
                if (low == a.length) {
                    low = 0;
                }
            }
        }

        // Pair not found
        return false;
    }

    /////////////////////////////////////
    /**
     * Finds the smallest integer that cannot be represented as a sum of elements from the array.
     *
     * This method takes an array of integers and finds the smallest positive integer that cannot be
     * represented as a sum of elements from the array. It iterates through the array, updating the
     * sum of elements as it progresses. If the current element is greater than the current sum, it
     * returns the sum, indicating the smallest integer that cannot be represented.
     *
     * @param arr The input array of integers.
     * @return The smallest positive integer that cannot be represented as a sum of elements from the array.
     */
    public static int findSmallest(int[] arr) {
        // Implementation details:
        // This method iterates through the array, updating the sum of elements as it progresses.
        // If the current element is greater than the current sum, it returns the sum, indicating
        // the smallest integer that cannot be represented as a sum of elements from the array.

        int sum = 1; // Initialize the smallest integer that cannot be represented

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > sum) {
                return sum; // Found the smallest integer that cannot be represented
            } else {
                sum += arr[i]; // Update the sum
            }
        }

        // All integers up to the sum of elements can be represented
        return sum;
    }

    /////////////////
    /**
     * Prints the pair of elements from two arrays that has the sum closest to a specified value.
     *
     * This method takes two arrays of integers and a target value and prints the pair of elements,
     * one from each array, whose sum is closest to the specified target value. The method utilizes
     * a two-pointer approach to efficiently search for the closest pair.
     *
     * @param a The first array of integers.
     * @param b The second array of integers.
     * @param x The target value for which the sum of elements should be closest.
     */
    public static void printClosest(int[] a, int[] b, int x) {
        // Implementation details:
        // This method utilizes a two-pointer approach to efficiently search for the pair of elements
        // from the two arrays whose sum is closest to the specified target value.

        int closest = Integer.MAX_VALUE; // Initialize the closest sum to a large value
        int i = 0; // Pointer for the first array
        int j = b.length - 1; // Pointer for the second array
        int saveI = 0; // Variable to save the element from the first array
        int saveJ = 0; // Variable to save the element from the second array

        while (i < a.length && j >= 0) {
            int sum = a[i] + b[j]; // Calculate the sum of the current pair of elements

            // Update the closest sum and the saved elements if the current sum is closer to the target
            if (Math.abs(sum - x) < closest) {
                closest = Math.abs(sum - x);
                saveI = a[i];
                saveJ = b[j];
            }

            // Move the pointers to adjust the sum
            if (sum > x) {
                j--; // Decrease the sum by moving to a smaller element in array b
            } else {
                i++; // Increase the sum by moving to a larger element in array a
            }
        }

        // Print the pair of elements with the closest sum
        System.out.println(saveI + " and " + saveJ);
        return;
    }

    /**
     * Counts the number of passing cars traveling in opposite directions.
     *
     * This method takes an array of integers representing cars traveling in a single direction,
     * where 0 represents a car traveling east and 1 represents a car traveling west. It calculates
     * the total number of pairs of cars that pass each other, where the cars traveling east (0)
     * pass those traveling west (1). The method utilizes a linear scan of the array to efficiently
     * count the passing cars.
     *
     * @param a The array of integers representing cars traveling in a single direction.
     * @return The total number of pairs of passing cars.
     */
    public static int passingCars(int[] a) {
        // Implementation details:
        // This method counts the number of passing cars traveling in opposite directions.
        // It utilizes a linear scan of the array to efficiently calculate the total number of pairs.

        int zeroCount = 0; // Counter for cars traveling east (0)
        int countPass = 0; // Counter for passing cars

        // Iterate through the array to count passing cars
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                zeroCount++; // Increment the count of cars traveling east (0)
            } else if (a[i] == 1) {
                countPass += zeroCount; // Increment the count of passing cars by the number of cars traveling east (0)
            }
        }

        // Return the total number of passing cars
        return countPass;
    }

    ///////////////////////////////
    /**
     * Determines if there exist two adjacent elements in the array whose sum equals a specified value.
     *
     * This method takes an array of integers and a target sum and checks if there exist two adjacent
     * elements in the array whose sum is equal to the specified target sum. It utilizes a binary search
     * algorithm to efficiently search for the target sum in the array.
     *
     * @param a The input array of integers.
     * @param x The target sum to be matched.
     * @return true if there exist two adjacent elements whose sum equals the target sum, otherwise false.
     */
    public static boolean findX(int[] a, int x) {
        // Implementation details:
        // This method determines if there exist two adjacent elements in the array whose sum is equal to the
        // specified target sum. It utilizes a binary search algorithm to efficiently search for the target sum
        // in the array.

        // Initialize pointers for binary search
        int low = 0;
        int high = a.length - 1;

        // Check if the array has only one element
        if (a.length == 1) {
            return false;
        }

        int mid = 0;

        // Perform binary search to find the target sum
        while (low <= high) {
            mid = (low + high) / 2;

            // Check if the sum of the current element and its adjacent neighbors equals the target sum
            if ((mid >= 0 && a[mid] + a[mid - 1] == x) || (mid < a.length && a[mid] + a[mid + 1] == x)) {
                return true; // Pair found
            } else if (mid >= 0 && a[mid] + a[mid - 1] < x) {
                low = mid + 1; // Adjust the low pointer
            } else {
                high = mid - 1; // Adjust the high pointer
            }
        }

        // Pair not found
        return false;
    }

    ///////////////////////////////////////

    /////////////////////////////////////
}

