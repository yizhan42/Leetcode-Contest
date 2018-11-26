
/*
893. Groups of Special-Equivalent Strings

Difficulty: Easy
You are given an array A of strings.

Two strings S and T are special-equivalent if after any number of moves, S == T.

A move consists of choosing two indices i and j with i % 2 == j % 2, and swapping S[i] with S[j].

Now, a group of special-equivalent strings from A is a non-empty subset of A such that any string not in A is not special-equivalent with any string in A.

Return the number of groups of special-equivalent strings from A.

 

Example 1:

Input: ["a","b","c","a","c","c"]
Output: 3
Explanation: 3 groups ["a","a"], ["b"], ["c","c","c"]
Example 2:

Input: ["aa","bb","ab","ba"]
Output: 4
Explanation: 4 groups ["aa"], ["bb"], ["ab"], ["ba"]
Example 3:

Input: ["abc","acb","bac","bca","cab","cba"]
Output: 3
Explanation: 3 groups ["abc","cba"], ["acb","bca"], ["bac","cab"]
Example 4:

Input: ["abcd","cdab","adcb","cbad"]
Output: 1
Explanation: 1 group ["abcd","cdab","adcb","cbad"]
 

Note:

1 <= A.length <= 1000
1 <= A[i].length <= 20
All A[i] have the same length.
All A[i] consist of only lowercase letters.
*/

// uwi
//1、奇数位偶数位分离，并各自排序；2、排序后拼接；3、若第1和第3位交换前后生成的新拼接后的字符串一样，则为1组
// abc,cba是一组，因为abc经过排序且拼接后是 acb, cba经过排序且拼接后是 acb，所以是同一组
class Solution {
    public int numSpecialEquivGroups(String[] A) {
        int n = A.length;
	        for(int i = 0;i < n;i++){
	        	char[] b = new char[(A[i].length()+1)/2];
	        	char[] c = new char[A[i].length()/2];
	        	for(int k = 0;k < A[i].length();k++){
	        		if(k % 2 == 0){
	        			b[k/2] = A[i].charAt(k);
	        		}else{
	        			c[k/2] = A[i].charAt(k);
	        		}
	        	}
	        	Arrays.sort(b);
	        	Arrays.sort(c);
	        	A[i] = new String(b) + new String(c);
	        }
	        Set<String> set = new HashSet<>();
	        for(int i = 0;i < n;i++)set.add(A[i]);
	        return set.size();
        
    }
}


/*
Approach 1: Counting
Intuition and Algorithm

Let's try to characterize a special-equivalent string SS, by finding a function \mathcal{C}C so that 𝑆≡𝑇⟺(𝑆)=(𝑇).

Through swapping, we can permute the even indexed letters, and the odd indexed letters. What characterizes these permutations is the count of the letters: all such permutations have the same count, and different counts have different permutations.

Thus, the function \mathcal{C}(S) =C(S)= (the count of the even indexed letters in S, followed by the count of the odd indexed letters in S) successfully characterizes the equivalence relation.

Afterwards, we count the number of unique \mathcal{C}(S)C(S) for S \in AS∈A.


Complexity Analysis

Time Complexity: O(\sum\limits_{i} (A_i)\text{.length})O(
​i
​∑
​​ (A
​i
​​ ).length)

Space Complexity: O(N)O(N), where NN is the length of A. 

*/

class Solution {
    public int numSpecialEquivGroups(String[] A) {
        Set<String> seen = new HashSet();
        for (String S: A) {
            // 26个英文字母，再把奇数位和偶数位分离成两部分，共52位
            int[] count = new int[52];
            for (int i = 0; i < S.length(); ++i)
                count[S.charAt(i) - 'a' + 26 * (i % 2)]++;
            seen.add(Arrays.toString(count));
        } 
        return seen.size();
    }
}