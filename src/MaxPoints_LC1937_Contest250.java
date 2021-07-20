public class MaxPoints_LC1937_Contest250 {
    //sol1: my, TLE
    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        long[][] res = new long[m][n];
        for(int i = 0; i < n; i++){
            res[0][i] = points[0][i];

        }
        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++){
                long max = Integer.MIN_VALUE;
                for(int k = 0; k < n; k++){
                    max = Math.max(max, points[i][j] + res[i - 1][k] - Math.abs(j-k));
                }
                res[i][j] = max;
            }
        }
        return findMax(res[m - 1]);

    }

    private long findMax(long[] row){
        long count = 0;
        for(int i = 0 ; i < row.length; i++){
            count = Math.max(row[i], count);
        }
        return count;
    }

    //todo: sol2, dp
}
