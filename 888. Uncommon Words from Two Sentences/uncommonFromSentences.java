/*
Difficulty: EASY
We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)

A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Return a list of all uncommon words. 

You may return the list in any order.

 

Example 1:

Input: A = "this apple is sweet", B = "this apple is sour"
Output: ["sweet","sour"]
Example 2:

Input: A = "apple apple", B = "banana"
Output: ["banana"]
 

Note:

0 <= A.length <= 200
0 <= B.length <= 200
A and B both contain only spaces and lowercase letters.

*/ 

// my answer
// A和B 编程字符串数组后放到一个 HashMap中，key是String， value是该String的个数。
// 把个数为 1 的所有String放到 一个 ArrayList中，最后把 ArrayList 转为 字符串数组：String[] results = ans.toArray(new String[ans.size()]);
class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        String[] Alist = A.split(" ");
        String[] Blist = B.split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (String str : Alist) {
            if ( !map.containsKey(str)) {
                map.put(str,1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }
        for (String str : Blist) {
            if ( !map.containsKey(str)) {
                map.put(str,1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }
        ArrayList<String> ans = new ArrayList<>();
        for (String str: Alist) {
            if (map.get(str) == 1) {
                ans.add(str);
            }
        }
        for (String str: Blist) {
            if (map.get(str) == 1) {
                ans.add(str);
            }
        }
        String[] results = ans.toArray(new String[ans.size()]);
        if (A == null || B == null) {
            return new String[0];//这句对不对
        }
        return results;
    }
}


