// Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.

// Return true if and only if we can do this in a way such that the resulting number is a power of 2.

 

// Example 1:

// Input: 1
// Output: true
// Example 2:

// Input: 10
// Output: false
// Example 3:

// Input: 16
// Output: true
// Example 4:

// Input: 24
// Output: false
// Example 5:

// Input: 46
// Output: true
 

// Note:
// 1 <= N <= 10^9



// Approach 1: Permutations
// Intuition

// For each permutation of the digits of N, let's check if that permutation is a power of 2.

// Algorithm

// This approach has two steps: how will we generate the permutations of the digits, and how will we check that the permutation represents a power of 2?

// To generate permutations of the digits, we place any digit into the first position (start = 0), then any of the remaining digits into the second position (start = 1), and so on. In Python, we can use the builtin function itertools.permutations.

// To check whether a permutation represents a power of 2, we check that there is no leading zero, and divide out all factors of 2. If the result is 1 (that is, it contained no other factors besides 2), then it was a power of 2. In Python, we can use the check bin(N).count('1') == 1.

public class reorderedPowerOf2{
    public static void main(String[] args){
        Solution solution = new Solution();
        solution.reorderedPowerOf2(128);
    }
}
class Solution {
    public boolean reorderedPowerOf2(int N) {
        // Build eg. N = 128 -> A = [1, 2, 8]
        String S = Integer.toString(N);
        int[] A = new int[S.length()];
        for (int i = 0; i < S.length(); ++i)
            A[i] = S.charAt(i) - '0';
        System.out.println("Done");
        return permutations(A, 0);
    }

    // Return true if A represents a valid power of 2
    public boolean isPowerOfTwo(int[] A) {
        if (A[0] == 0) return false;  // no leading zero

        // Build eg. A = [1, 2, 8] -> N = 128
        int N = 0;
        for (int x: A)
            N = 10 * N + x;
// version 1:
//         // Remove the largest power of 2
//         while (N > 0 && ((N & 1) == 0))
//             N >>= 1;

//         // Check that there are no other factors besides 2
//         return N == 1;
// version 2:
        if (N > 0 && ((N & (N - 1)) == 0)) {
            return true;
        }
        return false;    
    }

    /**
     * Returns true if some permutation of (A[start], A[start+1], ...)
     * can result in A representing a power of 2.
     */
    public boolean permutations(int[] A, int start) {
        if (start == A.length)
            return isPowerOfTwo(A);

        // Choose some index i from [start, A.length - 1]
        // to be placed into position A[start].
        for (int i = start; i < A.length; ++i) {
            // Place A[start] with value A[i].
            swap(A, start, i);

            // For each such placement of A[start], if a permutation
            // of (A[start+1], A[start+2], ...) can result in A
            // representing a power of 2, return true.
            if (permutations(A, start + 1))
                return true;

            // Restore the array to the state it was in before
            // A[start] was placed with value A[i].
            swap(A, start, i);
        }

        return false;
    }

    public void swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    
}

// Approach 2: Counting
// Intuition and Algorithm

// We can check whether two numbers have the same digits by comparing the count of their digits. For example, 338 and 833 have the same digits because they both have exactly two 3's and one 8.

// Since NN could only be a power of 2 with 9 digits or less (namely, 2^0, 2^1, ..., 2^29), we can just check whether NN has the same digits as any of these possibilities.

// class Solution {
//     public boolean reorderedPowerOf2(int N) {
//         int[] A = count(N);
//         for (int i = 0; i < 31; ++i)
//             if (Arrays.equals(A, count(1 << i)))
//                 return true;
//         return false;
//     }

//     // Returns the count of digits of N
//     // Eg. N = 112223334, returns [0,2,3,3,1,0,0,0,0,0]
//     public int[] count(int N) {
//         int[] ans = new int[10];
//         while (N > 0) {
//             ans[N % 10]++;
//             N /= 10;
//         }
//         return ans;
//     }
// }