public class MatrixRestoration {
    public int[][] matrixRestoration(int n, int m, int[][] after) {
        int[][] res = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                res[i][j] = after[i][j];
                if(j > 0){
                    res[i][j] -= after[i][j - 1];
                }
                if(i > 0){
                    res[i][j] -= after[i - 1][j];
                }
                if(i > 0 && j > 0){
                    res[i][j] += after[i - 1][j - 1];
                }

            }
        }
        return res;

    }
}
