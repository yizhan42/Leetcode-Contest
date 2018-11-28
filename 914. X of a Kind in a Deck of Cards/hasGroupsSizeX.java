/*
914. X of a Kind in a Deck of Cards
User Accepted: 1883
User Tried: 2274
Total Accepted: 1926
Total Submissions: 6579
Difficulty: Easy
In a deck of cards, each card has an integer written on it.

Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:

Each group has exactly X cards.
All the cards in each group have the same integer.
 

Example 1:

Input: [1,2,3,4,4,3,2,1]
Output: true
Explanation: Possible partition [1,1],[2,2],[3,3],[4,4]
Example 2:

Input: [1,1,1,2,2,2,3,3]
Output: false
Explanation: No possible partition.
Example 3:

Input: [1]
Output: false
Explanation: No possible partition.
Example 4:

Input: [1,1]
Output: true
Explanation: Possible partition [1,1]
Example 5:

Input: [1,1,2,2,2,2]
Output: true
Explanation: Possible partition [1,1],[2,2],[2,2]

Note:

1 <= deck.length <= 10000
0 <= deck[i] < 10000
*/

// my answer
class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck.length == 1) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < deck.length; i++) {
            if (map.containsKey(deck[i])) {
                map.put(deck[i], map.get(deck[i]) + 1);
            } else {
                map.put(deck[i], 1);
            }
        }
        int min = Integer.MAX_VALUE;

        for (int i = 1; i < deck.length; i++) {
            int temp = gcd(map.get(deck[i]), map.get(deck[i - 1]));
            if (temp < min) {
                min = temp;
            }
        }
        System.out.println(min);

        for (int i = 0; i < deck.length; i++) {
            if (min == 1 || map.get(deck[i]) % min != 0) {
                return false;
            }
        }

        return true;
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}

/*
 * [1,2,3,4,4,3,2,1] [1,1,1,2,2,2,3,3] [1] [1,1] [1,1,2,2,2,2]
 * [1,1,1,1,2,2,2,2,2,2]
 */

// uwi
class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int[] f = new int[10001];
        for (int d : deck) {
            f[d]++;
        }
        int g = 0;
        for (int v : f) {
            g = gcd(g, v);
        }
        return g != 1;
    }

    public int gcd(int a, int b) {
        while (b > 0) {
            int c = a;
            a = b;
            b = c % b;
        }
        return a;
    }

}

// Solution
// Approach 1: Brute Force
// Intuition

// We can try every possible X.

// Algorithm

// Since we divide the deck of N cards into say, K piles of X cards each, we
// must have N % X == 0.

// Then, say the deck has C_i copies of cards with number i.
// Each group with number i has X copies, so we must have C_i % X == 0. These
// are necessary and sufficient conditions.

class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int N = deck.length;
        int[] count = new int[10000];
        for (int c : deck)
            count[c]++;

        List<Integer> values = new ArrayList();
        for (int i = 0; i < 10000; ++i)
            if (count[i] > 0)
                values.add(count[i]);

        search: for (int X = 2; X <= N; ++X)
            if (N % X == 0) {
                for (int v : values)
                    if (v % X != 0)
                        continue search;
                return true;
            }

        return false;
    }
}

/*
 * Complexity Analysis
 * 
 * Time Complexity: O(N^2 \log \log N)O(N 2 loglogN), where NN is the number of
 * cards. It is outside the scope of this article to prove that the number of
 * divisors of NN is bounded by O(N \log \log N)O(NloglogN).
 * 
 * Space Complexity: O(N)O(N).
 * 
 * 
 * Approach 2: Greatest Common Divisor Intuition and Algorithm
 * 
 * Again, say there are C_i cards of number i. These must be broken down into
 * piles of X cards each, ie. C_i % X == 0 for all i.
 * 
 * Thus, X must divide the greatest common divisor of C_i. If this greatest
 * common divisor g is greater than 1, then X = g will satisfy. Otherwise, it
 * won't.
 * 
 * 
 */

class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int[] count = new int[10000];
        for (int c : deck)
            count[c]++;

        int g = -1;
        for (int i = 0; i < 10000; ++i)
            if (count[i] > 0) {
                if (g == -1)
                    g = count[i];
                else
                    g = gcd(g, count[i]);
            }

        return g >= 2;
    }

    public int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }
}
