import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllAnagrams {
    public List<Integer> allAnagrams(String sh, String lo) {
    /*ab
      abcbac

      [s, f] 闭区间是被check的区域
      map<a, 1><b, 1>
      因为check区域固定 只动f

    */
        List<Integer> list = new ArrayList<>();
        //corner case:
        if(lo.length() < sh.length()){
            return list;
        }
        Map<Character, Integer> counter = count(sh);
        //when match == sh.length it is a anagram
        int match = 0;
        //i represents the current item added to the sliding window
        for(int i = 0; i < lo.length(); i++){
            char tmp = lo.charAt(i);

            if(i < sh.length()){
                //first sh.length number of items, only add operation, no remove
                if(counter.containsKey(tmp)){
                    //orignal times
                    int freq = counter.get(tmp);
                    counter.put(tmp, freq - 1);
                    if(freq == 1){
                        match++;
                    }
                }
            }else{
                //既要吃，又要吐
                //吃： 如果couter有，在原基础上 -1
                if(counter.containsKey(tmp)){
                    //orignal times
                    int freq = counter.get(tmp);
                    counter.put(tmp, freq - 1);
                    if(freq == 1){
                        match++;
                    }
                }
                char remove = lo.charAt(i - sh.length());
                if(counter.containsKey(remove)){
                    int freq = counter.get(remove);
                    //去掉一个match 的， 待match评率 + 1
                    counter.put(remove, freq + 1);
                    if(freq == 0){
                        match--;
                    }
                }
            }
            if(match == counter.size()){
                list.add(i - sh.length() + 1);
            }
        }
        return list;
    }



    private Map<Character, Integer> count(String str){
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < str.length(); i++){
            char tmp = str.charAt(i);
            if(!map.containsKey(tmp)){
                map.put(tmp, 1);
            }else{
                map.put(tmp, map.get(tmp) + 1);
            }
        }
        return map;
    }
}
