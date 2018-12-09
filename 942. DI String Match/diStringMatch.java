// 942. DI String Match
// User Accepted: 1648
// User Tried: 1848
// Total Accepted: 1683
// Total Submissions: 2736
// Difficulty: Easy
// Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.

// Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:

// If S[i] == "I", then A[i] < A[i+1]
// If S[i] == "D", then A[i] > A[i+1]

// Example 1:

// Input: "IDID"
// Output: [0,4,1,3,2]
// Example 2:

// Input: "III"
// Output: [0,1,2,3]
// Example 3:

// Input: "DDI"
// Output: [3,2,0,1]

// Note:

// 1 <= S.length <= 10000
// S only contains characters "I" or "D".

// uwi
class Solution {
    public int[] diStringMatch(String S) {
        int n = S.length() + 1;
        int[] ret = new int[n];
        int v = n - 1;
        int pre = 0;
        for (int i = 0; i < n - 1; i++) {
            if (S.charAt(i) == 'D') {
                for (int j = i; j >= pre; j--) {
                    ret[j] = v;
                    v--;
                }
                pre = i + 1;
            }
        }
        for (int j = n - 1; j >= pre; j--) {
            ret[j] = v;
            v--;
        }
        return ret;
    }
}

// Solution
/*
 * Intuition
 * 
 * If we see say S[0] == 'I', we can always put 0 as the first element;
 * similarly, if we see S[0] == 'D', we can always put N as the first element.
 * 
 * Say we have a match for the rest of the string S[1], S[2], ... using N
 * distinct elements. Notice it doesn't matter what the elements are, only that
 * they are distinct and totally ordered. Then, putting 0 or N at the first
 * character will match, and the rest of the elements (1, 2, ..., N or 0, 1,
 * ..., N-1) can use the matching we have.
 * 
 * Algorithm
 * 
 * Keep track of the smallest and largest element we haven't placed. If we see
 * an 'I', place the small element; otherwise place the large element.
 * 
 */

class Solution {
    public int[] diStringMatch(String S) {
        int N = S.length();
        int lo = 0, hi = N;
        int[] ans = new int[N + 1];
        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == 'I')
                ans[i] = lo++;
            else
                ans[i] = hi--;
        }

        ans[N] = lo;
        return ans;
    }
}