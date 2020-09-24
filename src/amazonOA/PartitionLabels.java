package amazonOA;

import java.util.ArrayList;
import java.util.List;
//leetcode 763 Amazon vedio
public class PartitionLabels {
    //T: O(n) S: O(1)
    //For each letter encountered, process the last occurrence of that letter, extending the current partition [start, end] appropriately.
    public List<Integer> partitionLabels(String S) {
        //save last index of each letter
        int[] array = new int[26];
        for(int i = 0; i < S.length(); i++){
            array[S.charAt(i) - 'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        //i: current index being checked within range of [start, end]
        //start: first index of current partition
        //end:if i < end, end is the possible end index of current partion[start, end]. if i == end then end is the confirmed end of the current partition
        int start = 0, end = 0;
        for(int i = 0; i < S.length(); i++){
            end = Math.max(end, array[S.charAt(i) - 'a']);
            if(i == end){
                res.add(end - start + 1);
                start = i + 1;
            }
        }
        return res;
    }

}
