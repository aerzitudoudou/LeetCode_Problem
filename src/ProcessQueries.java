import java.util.ArrayList;
import java.util.List;

public class ProcessQueries {
    //TODO: check if it is the best way to do
    public int[] processQueries(int[] queries, int m) {
        int[] tmp = new int[m];
        for(int i = 0; i < m; i++){
            tmp[i] = i + 1;
        }
        int[] res = new int[queries.length];
        int count = 0;
        for(int i = 0; i < queries.length; i++){
            int index = findIndex(tmp, queries[i]);
            res[i] = index;
            tmp = move(tmp, queries[i]);

        }
        return res;

    }

    private int[] move(int[] array, int target){
        List<Integer> list = new ArrayList<>();
        list.add(target);
        for(int i = 0; i < array.length; i++){
            if(array[i] != target){
                list.add(array[i]);
            }else{
                i++;
            }

        }
        int[] res = new int[list.size()];
        int i = 0;
        for(Integer a : list){
            res[i] = a;
            i++;
        }
        return res;

    }

    private int findIndex(int[] array, int target){
        for(int i = 0; i < array.length; i++){
            if(array[i] == target){
                return i;
            }

        }
        return -1;
    }
}
