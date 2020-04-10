import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KeepDistanceForIdenticalElements {
    /*
     way 1: dfs
     all permutation, then verify the result
    */
//    public int[] keepDistance(int k) {
//        int[] ary = new int[2 * k];
//        //construct the input array
//        int counter = 1;
//        for(int i = 0; i < k * 2; i+=2){
//            ary[i] = ary[i + 1] = counter;
//            counter++;
//        }
//        int[][] res = new int[1][];
//        helper(ary, res, 0);
//        return res[0];
//    }

//    private void helper(int[] ary, int[][] res, int index){
//        if(index == ary.length && checkValid(ary)){
//            res[0] = Arrays.copyOf(ary, ary.length);
//            return;
//        }
//        for(int i = index; i < ary.length; i++){
//            swap(ary, i, index);
//            helper(ary, res, index + 1);
//            swap(ary, i, index);
//        }
//    }

    private Boolean checkValid(int[] array){
        Map<Integer, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < array.length; i++){
            if(hashMap.containsKey(array[i])){
                if(i - hashMap.get(array[i]) != array[i] + 1){
                    return false;
                }
            }else{
                hashMap.put(array[i], i);
            }
        }

        return true;
    }

    private void swap(int[] ary, int i, int j){
        int tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }

    /*
    way 2: use boolean[] to indicate if a number has been used
     */public int[] keepDistance(int k) {
        int[] ary = new int[2 * k];
        //construct the input array
        int counter = 1;
        for(int i = 0; i < k * 2; i+=2){
            ary[i] = ary[i + 1] = counter;
            counter++;
        }
        boolean[] use = new boolean[k + 1];
        return helper(ary, use, 0) ? ary : null;


    }

    private Boolean helper(int[] ary, boolean[] use, int index){
        if(index == ary.length){
            return true;
        }
        for(int i = index; i < ary.length; i++){
            int cur = ary[i];
            if(!use[cur] || (index > cur && cur == ary[index - cur - 1])){
                swap(ary, i, index);
                use[cur] = !use[cur];
                if(helper(ary, use, index + 1)){
                    return true;
                }
                swap(ary, i, index);
                use[cur] = !use[cur];
            }

        }
        return false;


    }


}
