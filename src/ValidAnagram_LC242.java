import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram_LC242 {
    //Sol1, t: O(n), s: O(1) map size is maximum = 26 so O(1) space complexity
    public boolean isAnagram(String s, String t) {

        if(s.length() != t.length()) return false;

        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }

        for(int i = 0; i < t.length(); i++){
            char cur = t.charAt(i);
            if(!map.containsKey(cur) || map.get(cur) == 0) return false;
            map.put(cur, map.get(cur) - 1);

        }

        return true;
    }

    //t: O(nlogn), S: O(1) //todo: why is heap sort O1
    public boolean isAnagram1(String s, String t) {
        if(s.length() != t.length()) return false;
        char[] charAry1 = s.toCharArray();
        char[] charAry2 = t.toCharArray();
        Arrays.sort(charAry1);
        Arrays.sort(charAry2);
        return Arrays.equals(charAry1, charAry2);




    }


}
