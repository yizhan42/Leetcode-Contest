import java.util.ArrayList;
import java.util.TreeMap;
// You have an array of logs.  Each log is a space delimited string of words.

// For each log, the first word in each log is an alphanumeric identifier.  Then, either:

// Each word after the identifier will consist only of lowercase letters, or;
// Each word after the identifier will consist only of digits.
// We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

// Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

// Return the final order of the logs.

// Example 1:

// Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
// Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]

// Note:

// 0 <= logs.length <= 100
// 3 <= logs[i].length <= 100
// logs[i] is guaranteed to have an identifier, and a word after the identifier.

// 此题我卡在不会给identifier之后的部分排序。
// 所以看这两种方法的处理方式：
// 第一种重写排序方法：
// String[] words1 = log1.split(" ");
// String[] words2 = log2.split(" ");

// int firstSpaceIndex = log1.indexOf(' ');
// int secondSpaceIndex = log2.indexOf(' ');

// return log1.substring(firstSpaceIndex+1).compareTo(log2.substring(secondSpaceIndex+1));

// 第二种用TreeMap，巧妙地方：String key = s.substring(toks[0].length()).trim();

// version 1
// class Solution {
//     public String[] reorderLogFiles(String[] logs) {
//         Arrays.sort(logs, new LogComparator());

//         return logs;
//     }
// }

// class LogComparator implements Comparator<String>{
//     @Override 
//     public int compare(String log1, String log2){
//         String[] words1 = log1.split(" ");
//         String[] words2 = log2.split(" ");

//         int firstSpaceIndex = log1.indexOf(' ');
//         int secondSpaceIndex = log2.indexOf(' ');

//         boolean isLetter1 = isLetter(words1);
//         boolean isLetter2 = isLetter(words2);

//         if (isLetter1 == true && isLetter2 == true){
//             //Then we need to sort lexicographically, but we need to ignore the first word.
//             return log1.substring(firstSpaceIndex+1).compareTo(log2.substring(secondSpaceIndex+1));
//         }else if (isLetter1 != isLetter2){ //This means one is true and one is false, therefore, one is a digit the other is a letter
//             return isLetter1 ? -1 : 1; //If the first word is a letter log, then second one is digit log and vice-versa.
//         } //else if isLetter1 == false && isLetter2 == false, both are digits therefore maintain the ordering.

//         return 0;

//     }

//     boolean isLetter(String[] words){
//         //Get the first word of split string (not the id)
//         String firstWord = words[1]; //First word after id
//         //Get the first letter of the first word
//         char c = firstWord.charAt(0);
//         if (Character.isLetter(c)) return true;

//         return false;
//     }
// }

// Iterate over each log entry in the passed array. Split the entry. If we find that the second token of the entry has digits, then the entire tokens must all be digits -- so, we can add them to an ArrayList. Else, we add them to a TreeMap. When adding to the TreeMap, use the substring of the entry (other than the identifier) as the key and the entry as the value.

// version 2
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        ArrayList<String> al = new ArrayList<>();
        TreeMap<String, String> map = new TreeMap<>();

        for (String s : logs) {
            String[] toks = s.split(" ");
            if (!Character.isAlphabetic(toks[1].charAt(0)))
                al.add(s);
            else {
                String key = s.substring(toks[0].length()).trim();
                System.out.println(toks[0].length());
                System.out.println(key);
                System.out.println(toks[0].length());
                map.put(key, s);
            }
        }

        String[] ret = new String[logs.length];
        int idx = 0;
        for (String key : map.keySet())
            ret[idx++] = map.get(key);

        for (String s : al)
            ret[idx++] = s;
        return ret;
    }
}