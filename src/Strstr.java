import java.util.HashMap;
import java.util.Map;
//laicode 85

public class Strstr {
    //large length: m
    //small length: n
    //T: worst case: O(m * n) average: O(m)
    //S: O(n)
    public int strstr(String large, String small) {
        if(small.length() == 0){
            return 0;
        }

        if(large.length() < small.length()){
            return -1;
        }

        int matchNum = 0;
        //init smap
        Map<Character, Integer> sMap = new HashMap<>();
        for(int i = 0; i < small.length(); i++){
            sMap.put(small.charAt(i), sMap.getOrDefault(small.charAt(i), 0) + 1);

        }
        //init bMap
        Map<Character, Integer> bMap = new HashMap<>();

        for(int i = 0; i < small.length(); i++){
            if(sMap.containsKey(large.charAt(i))){
                char c = large.charAt(i);
                int old = bMap.getOrDefault(c, 0);

                bMap.put(c, old + 1);
                //update #of matched count
                if(old == sMap.get(c)){
                    matchNum--;
                }else if(old + 1 == sMap.get(c)){
                    matchNum++;
                }

            }
        }
        if(matchNum == sMap.size() && isSub(0, large, small)){
            return 0;
        }



        int s = 0, f = small.length() - 1;
        while(f < large.length() - 1){
            s++;
            f++;
            //update sMap, bMap, matchNum
            if(sMap.containsKey(large.charAt(f))){
                char c = large.charAt(f);
                int old = bMap.getOrDefault(c, 0);

                bMap.put(c, old + 1);
                //update #of matched count
                if(old == sMap.get(c)){
                    matchNum--;
                }else if(old + 1 == sMap.get(c)){
                    matchNum++;
                }

            }



            if(sMap.containsKey(large.charAt(s - 1))){
                char c = large.charAt(s - 1);
                int old = bMap.getOrDefault(c, 0);

                bMap.put(c, old - 1);
                //update #of matched count
                if(old == sMap.get(c)){
                    matchNum--;
                }else if(old - 1 == sMap.get(c)){
                    matchNum++;
                }
            }

            if(matchNum == sMap.size() && isSub(s, large, small)) {
                return s;
            }

        }
        return -1;




    }

    private boolean isSub(int s, String large, String small){
        for(int i = 0; i < small.length(); i++){
            if(large.charAt(s + i) != small.charAt(i)){
                return false;
            }
        }
        return true;
    }
}
