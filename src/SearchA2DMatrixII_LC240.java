public class SearchA2DMatrixII_LC240 {
    /*
   from upper right corner to lower left corner

   target < cur: move left
   target > cur: move down
   similar to lc 1428 m+ n way

   */
    //O(m + n), O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while(i < m && j >= 0){
            if(matrix[i][j] == target){
                return true;
            }else if(matrix[i][j] > target){
                j--;
            }else{
                i++;
            }
        }
        return false;
    }
}
