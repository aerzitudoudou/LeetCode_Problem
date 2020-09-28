package amazonOA;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
Given a string s and an int k, return all unique substrings of s of size k with k distinct characters.

        Example 1:

        Input: s = "abcabc", k = 3
        Output: ["abc", "bca", "cab"]
        Example 2:

        Input: s = "abacab", k = 3
        Output: ["bac", "cab"]
        Example 3:

        Input: s = "awaglknagawunagwkwagl", k = 4
        Output: ["wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag"]
        Explanation:
        Substrings in order are: "wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag", "wagl"
        "wagl" is repeated twice, but is included in the output once.
        Constraints:

        The input string consists of only lowercase English letters [a-z]
        0 ≤ k ≤ 26

 */
public class KSubstringWithKDifferentCharacters {
    //sliding window 套用模板：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/hua-dong-chuang-kou-tong-yong-si-xiang-jie-jue-zi-/
    //if output is the number of result strings, then T: O(n) S: O(n)
    //if ask for K - 1 characters, just replace K with K - 1 everywhere in the code
    //if output is the set, then if counted result as space complexity, then S = O(n) otherwise S = O(K) = O(1)
    public int KSubstring(String stringIn, int K) {
        //corner cases
        if (stringIn == null || stringIn.length() == 0 || K <= 0) {
            return 0;
        }
        HashMap<Character, Integer> charMap = new HashMap<>();
        Set<Character> dupSet = new HashSet<>();
        HashSet<String> resultSet = new HashSet<>();
        int len = stringIn.length();
        int left = 0, right = 0;
        while(right < len){
            char c = stringIn.charAt(right);
            right++;

            if(charMap.containsKey(c)){
                dupSet.add(c);
            }
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);

            //some updates
            while(left < len &&  (charMap.size() == K || !dupSet.isEmpty())){
                char d = stringIn.charAt(left);
                left++;
                if(charMap.size() == K){
                    resultSet.add(stringIn.substring(left - 1, right));
                }
                if(!dupSet.contains(d)){
                    charMap.remove(d);
                }else{
                    dupSet.remove(d);
                    charMap.put(d, charMap.get(d) - 1);
                }
            }


        }
        return resultSet.size();

    }

}
