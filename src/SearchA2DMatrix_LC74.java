public class SearchA2DMatrix_LC74 {
    //sol1, my, run binary search on first col to find the largest row <= target
    //then find the largest col <= target
    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m - 1;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(matrix[mid][0] <= target){
                l = mid;
            }else r = mid - 1;
        }

        int rowIndex = r;
        l = 0;
        r = n - 1;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(matrix[rowIndex][mid] <= target){
                l = mid;
            }else r = mid - 1;
        }

        return matrix[rowIndex][r] == target;
    }

    //!!!sol2, from Lai, stretch the matrix into a line, m is row, n is col
    //line index x -> matrix coordinate: (x / col, x % col)
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n - 1;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            int rowIndex = mid / n, colIndex = mid % n;
            if(matrix[rowIndex][colIndex] <= target){
                l = mid;
            }else r = mid - 1;
        }

        return matrix[r / n][r % n] == target;
    }

}
