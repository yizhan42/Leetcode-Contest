/*
Solution
Approach 1: Stack of Letters
Intuition and Algorithm

Collect the letters of S separately into a stack, so that popping the stack reverses the letters. (Alternatively, 
we could have collected the letters into an array and reversed the array.)

Then, when writing the characters of S, any time we need a letter, we use the one we have prepared instead.

Complexity Analysis

Time Complexity: O(N), where NN is the length of S.

Space Complexity: O(N). 


*/

class Solution {
    public String reverseOnlyLetters(String S) {
        Stack<Character> letters = new Stack();
        for (char c : S.toCharArray())
            if (Character.isLetter(c))
                letters.push(c);

        StringBuilder ans = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (Character.isLetter(c))
                ans.append(letters.pop());
            else
                ans.append(c);
        }

        return ans.toString();
    }
}

/*
 * 
 * Approach 2: Reverse Pointer Intuition
 * 
 * Write the characters of S one by one. When we encounter a letter, we want to
 * write the next letter that occurs if we iterated through the string
 * backwards.
 * 
 * So we do just that: keep track of a pointer j that iterates through the
 * string backwards. When we need to write a letter, we use it.
 * 
 * Complexity Analysis
 * 
 * Time Complexity: O(N), where NN is the length of S.
 * 
 * Space Complexity: O(N).
 */

class Solution {
    public String reverseOnlyLetters(String S) {
        StringBuilder ans = new StringBuilder();
        int j = S.length() - 1;
        for (int i = 0; i < S.length(); ++i) {
            if (Character.isLetter(S.charAt(i))) {
                while (!Character.isLetter(S.charAt(j)))
                    j--;
                ans.append(S.charAt(j--));
            } else {
                ans.append(S.charAt(i));
            }
        }

        return ans.toString();
    }
}

// uwi
class Solution {
    public String reverseOnlyLetters(String S) {
        char[] s = S.toCharArray();
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            while (i < s.length && !ok(s[i]))
                i++;
            while (j >= 0 && !ok(s[j]))
                j--;
            if (i < j) {
                char d = s[i];
                s[i] = s[j];
                s[j] = d;
            }
        }
        return new String(s);
    }

    boolean ok(char c) {
        return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
    }
}
