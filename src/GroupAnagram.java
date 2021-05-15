import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    /*way 3*/
    //2021/04/01
    public List<List<String>> groupAnagrams3(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        if(strs == null || strs.length == 0){
            return res;
        }

        for(String s : strs){
            int[] count = new int[26];
            char[] charAry = s.toCharArray();
            for(char c : charAry){
                count[c - 'a']++;
            }
            String key = encode(count);
            List<String> list = ans.getOrDefault(key, new ArrayList<String>());
            list.add(s);
            ans.put(key, list);

        }
        res.addAll(ans.values());//List<String> has to have type to be able to use List.addAll

        return res;


    }

    private String encode(int[] count){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < count.length; i++){
            sb.append('#');
            sb.append(count[i]);
        }
        return sb.toString();
    }
}
