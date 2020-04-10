import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> allTriples(int[] array, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(array);
        if(array == null || array.length <= 2){
            return res;
        }


        for(int c = 0; c < array.length - 2; c++){
            if(c > 0 && array[c - 1] == array[c]){
                continue;
            }
            int i = c + 1, j = array.length - 1;
            while(i < j){
                if(array[i] + array[j] == target - array[c]){
                    res.add(Arrays.asList(array[c], array[i], array[j]));
                    break;

                }else if(array[i] + array[j] < target - array[c]){
                    while(i <= array.length - 2 && array[i] == array[i + 1]){
                        i++;
                    }
                    i++;
                }else{
                    while(j >= 2 && array[j] == array[j - 1]){
                        j--;
                    }
                    j--;
                }
            }
        }
        return res;


    }
}
