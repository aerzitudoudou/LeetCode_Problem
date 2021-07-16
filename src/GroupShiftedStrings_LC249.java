import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupShiftedStrings_LC249 {
    /*
      a d f
map: <0#3#5, index>

*/
    //sol1, my , O(m * n) , O(m * n)
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strings){
            String key = hash(str);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            res.add(entry.getValue());
        }
        return res;
    }

    //hash: bcd -> abc
    // private String hash(String str){
    //     int diff = str.charAt(0) - 'a';
    //     StringBuilder sb = new StringBuilder();
    //     for(char c : str.toCharArray()){
    //         int curDiff = c - 'a';
    //         char cur = curDiff < diff ? (char)(c - diff + 26) :(char)(c - diff);
    //         sb.append(cur);
    //     }
    //     return sb.toString();
    // }

    //another way of generating hash: 0#3#5
    private String hash(String str){
        int diff = str.charAt(0) - 'a';
        StringBuilder sb = new StringBuilder();
        for(char c : str.toCharArray()){
            int curDiff = c - 'a';
            int cur = curDiff < diff ? c - diff + 26 : c - diff;
            sb.append(cur);
            sb.append('#');
        }
        return sb.toString();
    }
}
