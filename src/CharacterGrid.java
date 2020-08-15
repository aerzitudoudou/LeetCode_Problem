/*
*
* Description
You are given two words, A and B.Please return a matrix. The word A must be output horizontally,and the word B vertically, so that the two words cross (i.e. share exactly one letter). The shared
letter must be the first letter in A that is also contained in B. More specifically the first occurrence of that letter in each word. All other characters in the grid must be periods (the character”. without quotes).For example, given the words A =“ABBA" and B= "CCBB". you need to return 4 lines as shown below:

[“.C..”,
 “.C..”,
 ”ABBA“,
 ”.B..“]
1 \leq A.length,B.length \leq 301≤A.length,B.length≤30
It's guaranteed that A and B share a letter at least
*
*
*
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterGrid {
    public List<String> characterGrid(String A, String B) {
        List<String> res = new ArrayList<>();
        if(A == null || B == null || A.length() == 0 || B.length() == 0){
            return res;
        }
        int len1 = A.length();
        int len2 = B.length();
        int index1 = -1;
        int index2 = -1;
        Map<Character, Integer> map = new HashMap<>();
        //find first letter occurance
        for(int i = 0; i < len2; i++){
            Integer cur = map.putIfAbsent(B.charAt(i), i);
        }
        for(int i = 0; i < len1; i++){
            if(map.containsKey(A.charAt(i))){
                index1 = i;
                index2 = map.get(A.charAt(i));
                break;
            }
        }
        if(index1 == -1 || index2 == -1){
            return res;
        }
        for(int i = 0; i < len2; i++){
            if(i != index2){
                res.add(genRow(len1, index1, i, B));
            }else{
                res.add(A);
            }

        }
        return res;
    }


    private String genRow(int len, int index, int i, String B){
        StringBuilder sb = new StringBuilder();
        for(int a = 0; a < len; a++){
            if(a != index){
                sb.append(".");
            }else{
                sb.append(B.charAt(i));
            }
        }
        return sb.toString();
    }
}
