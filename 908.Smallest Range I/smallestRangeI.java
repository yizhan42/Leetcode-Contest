import java.util.Arrays;
/*
908. Smallest Range I
User Accepted: 2257
User Tried: 2389
Total Accepted: 2329
Total Submissions: 3668
Difficulty: Easy
Given an array A of integers, for each integer A[i] we may choose any x with -K <= x <= K, and add x to A[i].

After this process, we have some array B.

Return the smallest possible difference between the maximum value of B and the minimum value of B.

 

Example 1:

Input: A = [1], K = 0
Output: 0
Explanation: B = [1]
Example 2:

Input: A = [0,10], K = 2
Output: 6
Explanation: B = [2,8]
Example 3:

Input: A = [1,3,6], K = 3
Output: 0
Explanation: B = [3,3,3] or B = [4,4,4]
 

Note:

1 <= A.length <= 10000
0 <= A[i] <= 10000
0 <= K <= 10000
*/

// My answer
class Solution {
    public int smallestRangeI(int[] A, int K) {
        int[] arr = new int[2 * K + 1];
        for (int i = arr.length / 2; i > 0; i--) {
            arr[i] = -i;
        }
        arr[arr.length / 2] = 0;
        for (int i = arr.length / 2 + 1; i < arr.length; i++) {
            arr[i] = arr.length % K;
        }

        Arrays.sort(A);
        int min = A[0] + K;
        int max = A[A.length - 1] - K;
        if ((max - min) < 0) {
            return 0;
        }
        return max - min;
    }
}

/*
 * Solution
 * 
 * Approach 1: Mathematical Intuition and Algorithm
 * 
 * Let A be the original array, and B be the array after all our modifications.
 * Towards trying to minimize max(B) - min(B), let's try to minimize max(B) and
 * maximize min(B) separately.
 * 
 * The smallest possible value of max(B) is max(A) - K, as the value max(A)
 * cannot go lower. Similarly, the largest possible value of min(B) is min(A) +
 * K. So the quantity max(B) - min(B) is at least ans = (max(A) - K) - (min(A) +
 * K).
 * 
 * We can attain this value (if ans >= 0), by the following modifications:
 * 
 * If A[i] ≤ min(A)+K, then B[i]=min(A)+K Else, if A[i] ≥ max(A)−K, then
 * B[i]=max(A)−K Else, B[i] = A[i]B[i]=A[i]. If ans < 0, the best answer we
 * could have is ans = 0, also using the same modification.
 * 
 */
class Solution {
    public int smallestRangeI(int[] A, int K) {
        int min = A[0], max = A[0];
        for (int x : A) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        return Math.max(0, max - min - 2 * K);
    }
}