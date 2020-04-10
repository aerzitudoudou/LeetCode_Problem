import java.util.ArrayList;
import java.util.List;

public class CommonNumberOfTwoSortedArys {
    public List<Integer> common(int[] A, int[] B) {
        List<Integer> res = new ArrayList<>();

        //method 1: 谁小移谁
        int i = 0, j = 0;
        while (i < A.length && j < B.length){
            if(A[i] == B[j]){
                res.add(A[i]);


            }else if(A[i] < B[j]){
                i++;
            }else{
                j++;
            }
        }
        return res;
    }
}
