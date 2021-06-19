import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuildingWithAnOceanView_LC1762 {
    /*
    right to left, update max. if cur > max, save index to res

   */


    //O(n) O(n)
    public int[] findBuildings(int[] heights) {
        List<Integer> list = new ArrayList<>();
        int max = 0;
        for(int i = heights.length - 1; i >= 0; i--){
            if(heights[i] > max){
                max = heights[i];
                list.add(i);
            }
        }
        Collections.reverse(list);
        return list.stream().mapToInt(i->i).toArray();


    }
}
