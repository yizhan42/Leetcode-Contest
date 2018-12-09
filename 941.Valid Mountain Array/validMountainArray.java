// 941. Valid Mountain Array
// User Accepted: 2235
// User Tried: 2377
// Total Accepted: 2282
// Total Submissions: 7042
// Difficulty: Easy
// Given an array A of integers, return true if and only if it is a valid mountain array.

// Recall that A is a mountain array if and only if:

// A.length >= 3
// There exists some i with 0 < i < A.length - 1 such that:
// A[0] < A[1] < ... A[i-1] < A[i]
// A[i] > A[i+1] > ... > A[B.length - 1]

// Example 1:

// Input: [2,1]
// Output: false
// Example 2:

// Input: [3,5,5]
// Output: false
// Example 3:

// Input: [0,3,2,1]
// Output: true

// Note:

// 0 <= A.length <= 10000
// 0 <= A[i] <= 10000 

// My answer
class Solution {
    public boolean validMountainArray(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
        if (A.length < 3) {
            return false;
        }
        int temp = -1;
        int ins = 1;
        // for (int i = 1; i < A.length;) {
        if (A[1] < A[0]) {
            return false;
            // i++;
            // if (i == A.length) {
            // return false;
            // }
        }
        // else {
        // ins = i;
        // System.out.println(ins);
        // break;
        // }
        // }
        for (int i = 1; i < A.length;) {
            if (A[i] > A[i - 1]) {
                i++;
                if (i == A.length) {
                    return false;
                }
            } else {
                temp = i;
                break;
                // System.out.println(temp);
            }

        }
        int j = temp;
        for (; j < A.length;) {

            if (A[j] < A[j - 1]) {
                j++;
            } else {
                return false;
            }
        }
        return j == A.length;
    }
}
/*
 * [2,1] [3,5,5] [0,3,2,1] [] [0,1,2,3,4,5,6,7,8,9] false [9,8,7,6,5,4,3,2,1,0]
 * false [3,2,1,2,3,2] true [2,1,2,3,5,7,9,10,12,14,15,16,18,14,13] false
 */

// uwi
class Solution {
    public boolean validMountainArray(int[] A) {
        if (A.length < 3)
            return false;
        int n = A.length;
        int pre = n - 1;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] >= A[i + 1]) {
                pre = i;
                break;
            }
        }
        if (pre == 0 || pre == n - 1)
            return false;
        for (int i = pre; i < n - 1; i++) {
            if (A[i] <= A[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

// Solution
/*
 * Intuition
 * 
 * If we walk along the mountain from left to right, we have to move strictly
 * up, then strictly down.
 * 
 * Algorithm
 * 
 * Let's walk up from left to right until we can't: that has to be the peak. We
 * should ensure the peak is not the first or last element. Then, we walk down.
 * If we reach the end, the array is valid, otherwise its not
 */

class Solution {
    public boolean validMountainArray(int[] A) {
        int N = A.length;
        int i = 0;

        // walk up
        while (i + 1 < N && A[i] < A[i + 1])
            i++;

        // peak can't be first or last
        if (i == 0 || i == N - 1)
            return false;

        // walk down
        while (i + 1 < N && A[i] > A[i + 1])
            i++;

        return i == N - 1;
    }
}