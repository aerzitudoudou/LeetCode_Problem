import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacterInAString_LC387 {
    public int firstUniqChar(String s) {
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
}
