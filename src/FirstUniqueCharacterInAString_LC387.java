import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacterInAString_LC387 {
    public int firstUniqChar1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            Integer cur = map.getOrDefault(c, 0);
            cur++;
            map.put(c, cur);
        }
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(map.get(c) == 1){
                return i;
            }
        }

        return -1;
    }

    public int firstUniqChar2(String s) {
        int[] map = new int[26];
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            map[c - 'a']++;
        }

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(map[c - 'a'] == 1) return i;
        }

        return -1;
    }
}
