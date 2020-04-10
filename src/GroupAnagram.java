
import java.util.*;

public class GroupAnagram {

    /*way1:
    public List<List<String>> groupAnagrams(String[] strs) {
        //use a hashset to save the sorted words that's been seen
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String a : strs){
            char[] tmp = a.toCharArray();
            Arrays.sort(tmp);
            String string = String.valueOf(tmp);
            if(map.keySet().contains(string)){
                map.get(string).add(a);
            }else{
                List<String> list = new ArrayList();
                list.add(a);
                map.put(string, list);
            }
        }


        return new ArrayList(map.values());
    }
    */

    /*way2:
    * */
    public List<List<String>> groupAnagrams(String[] strs) {
        //HashMap<String, String> key: #0#1#2#4#5#6#0#0#0#0#0#0#0#0#0#0#0
        //each word is n
        //array length is m
        //S: O(mn)
        //T: O(mn)
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();


        for(String str : strs){ //m
            String tmp = convert(toCountArray(str)); //n
            if(!map.containsKey(tmp)){
                List<String> list = new ArrayList<>();
                map.put(tmp, list);
            }
            map.get(tmp).add(str);


        }

        return new ArrayList(map.values());
    }

    private String convert(int[] count){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < count.length; i++){
            sb.append('#');
            sb.append(count[i]);
        }
        return sb.toString();
    }

    private int[] toCountArray(String str){
        int[] count = new int[26];
        for(int i = 0; i < str.length(); i++){
            count[str.charAt(i) - 'a']++;
        }
        return count;
    }
}
