import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestPalindrome_LC409 {
    //!!sol1, from https://leetcode.com/problems/longest-palindrome/solution/, hashmap, O(n), O(1) (52 letters in total which is constant)
    public int longestPalindrome(String s) {
        int res = 0;
        boolean isOdd = false;
        Map<Character, Integer> map = new HashMap<>();
        char[] array = s.toCharArray();
        for(char c : array){
            int cur = map.getOrDefault(c, 0);
            map.put(c, cur + 1);
        }

        for(Integer i : map.values()){
            //!!!find how may even count numbers each character can contribute: if count  = even, then all counts can goes to res; if odd, then count - 1 number goes to res
            res += i / 2 * 2;
            if(i % 2 == 1) isOdd = true;
        }

        return isOdd ? res + 1 : res;
    }


    //!!!sol2, from https://leetcode.com/problems/longest-palindrome/discuss/89604/Simple-HashSet-solution-Java, hashSet O(n), O(1)
    public int longestPalindrome1(String s) {
        int res = 0;
        Set<Character> set = new HashSet<>();
        char[] ary = s.toCharArray();
        for(char c : ary){
            if(set.contains(c)){
                set.remove(c);
            }else{
                set.add(c);
            }
        }

        return set.isEmpty() ? s.length() : s.length() - set.size() + 1;
    }
}
