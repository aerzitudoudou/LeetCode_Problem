public class SprialMatrixII_LC59 {

    //sol1: my, recursive
    public int[][] generateMatrix1(int n) {
        int[][] res = new int[n][n];
        generate(res, 0, n, 1);
        return res;
    }

    private void generate(int[][] res, int offset, int size, int num){
        if(size == 0) return;
        if(size == 1){
            res[offset][offset] = num;
            return;
        }

        for(int i = 0; i < size; i++){
            res[offset][offset + i] = num++;
        }
        for(int i = 1; i < size - 1; i++){
            res[offset + i][size + offset - 1] = num++;
        }
        for(int i = size - 1; i >= 0; i--){
            res[size + offset - 1][offset + i] = num++;
        }
        for(int i = size - 2; i >= 1; i--){
            res[offset + i][offset] = num++;
        }

        generate(res, ++offset, size - 2, num);
    }


    //sol2: my, iterative
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int size = n, offset = 0, num = 1;
        while(size > 1){
            for(int i = 0; i < size; i++){
                res[offset][offset + i] = num++;
            }
            for(int i = 1; i < size - 1; i++){
                res[offset + i][size + offset - 1] = num++;
            }
            for(int i = size - 1; i >= 0; i--){
                res[size + offset - 1][offset + i] = num++;
            }
            for(int i = size - 2; i >= 1; i--){
                res[offset + i][offset] = num++;
            }
            size-=2;
            offset++;
        }
        if(size == 1){
            res[offset][offset] = num;
        }
        return res;
    }
}
