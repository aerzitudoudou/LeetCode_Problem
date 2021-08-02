import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix_LC54 {
    //sol1, iterative, from lai
    public List<Integer> spiralOrder(int[][] matrix) {
        int up = 0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        List<Integer> res = new ArrayList<>();
        while(left < right && up < down){
            for(int i = left; i <= right; i++){
                res.add(matrix[up][i]);
            }
            for(int i = up + 1; i <= down - 1; i++){
                res.add(matrix[i][right]);
            }
            for(int i = right; i >= left; i--){
                res.add(matrix[down][i]);
            }
            for(int i = down - 1; i >= up + 1; i--){
                res.add(matrix[i][left]);
            }
            left++;
            right--;
            up++;
            down--;
        }

        if(left > right || up > down){
            return res;
        }

        if(left == right){
            for(int i = up; i <= down; i++){
                res.add(matrix[i][left]);
            }
        }else{
            for(int i = left; i <= right; i++){
                res.add(matrix[up][i]);
            }
        }

        return res;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int up = 0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        generate(left, right, up, down, res, matrix);
        return res;


    }
    //sol2: my, recursive, O(n), O(m)
    //m: number of spirals in this matrix
    private void generate(int left, int right, int up, int down, List<Integer> res, int[][] matrix){
        if(left > right || up > down) return;
        if(left == right) {
            for(int i = up; i <= down; i++){
                res.add(matrix[i][left]);
            }
            return;
        }
        if(up == down){
            for(int i = left; i <= right; i++){
                res.add(matrix[up][i]);
            }
            return;
        }

        for(int i = left; i <= right; i++){
            res.add(matrix[up][i]);
        }
        for(int i = up + 1; i <= down - 1; i++){
            res.add(matrix[i][right]);
        }
        for(int i = right; i >= left; i--){
            res.add(matrix[down][i]);
        }
        for(int i = down - 1; i >= up + 1; i--){
            res.add(matrix[i][left]);
        }
        generate(++left, --right, ++up, --down, res, matrix);
    }




}
