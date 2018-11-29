/*
915. Partition Array into Disjoint Intervals
DescriptionHintsSubmissionsDiscussSolution
Given an array A, partition it into two (contiguous) subarrays left and right so that:

Every element in left is less than or equal to every element in right.
left and right are non-empty.
left has the smallest possible size.
Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.

 

Example 1:

Input: [5,0,3,8,6]
Output: 3
Explanation: left = [5,0,3], right = [8,6]
Example 2:

Input: [1,1,1,0,6,12]
Output: 4
Explanation: left = [1,1,1,0], right = [6,12]
 

Note:

2 <= A.length <= 30000
0 <= A[i] <= 10^6
It is guaranteed there is at least one way to partition A as described.
 
*/

// uwi
class Solution {
    public int partitionDisjoint(int[] a) {
        int n = a.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = Math.max(pre[i], a[i]);
        }
        int[] suf = new int[n + 1];
        suf[n] = 999999999;
        for (int i = n - 1; i >= 0; i--) {
            suf[i] = Math.min(suf[i + 1], a[i]);
        }
        for (int i = 1; i < n; i++) {
            if (pre[i] <= suf[i]) {
                return i;
            }
        }
        throw new RuntimeException();
    }
}

// Solution
/*
 * Approach 1: Next Array Intuition
 * 
 * Instead of checking whether all(L <= R for L in left for R in right), let's
 * check whether max(left) <= min(right).
 * 
 * Algorithm
 * 
 * Let's try to find max(left) for subarrays left = A[:1], left = A[:2], left =
 * A[:3], ... etc. Specifically, maxleft[i] will be the maximum of subarray
 * A[:i]. They are related to each other: max(A[:4]) = max(max(A[:3]), A[3]), so
 * maxleft[4] = max(maxleft[3], A[3]).
 * 
 * Similarly, min(right) for every possible right can be found in linear time.
 * 
 * After we have a way to query max(left) and min(right) quickly, the solution
 * is straightforward.
 * 
 * 
 */

// 算法思想：从左到右遍历一遍找最大值，从右往左再遍历一遍找最小值

class Solution {
    public int partitionDisjoint(int[] A) {
        int N = A.length;
        int[] maxleft = new int[N];
        int[] minright = new int[N];

        int m = A[0];
        for (int i = 0; i < N; ++i) {
            m = Math.max(m, A[i]);
            maxleft[i] = m;
        }

        m = A[N - 1];
        for (int i = N - 1; i >= 0; --i) {
            m = Math.min(m, A[i]);
            minright[i] = m;
        }

        for (int i = 1; i < N; ++i)
            if (maxleft[i - 1] <= minright[i])
                return i;

        throw null;
    }
}