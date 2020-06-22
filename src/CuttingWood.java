/*
*
*
* Laicode
*137. Cutting Wood I
Hard
There is a wooden stick with length L >= 1, we need to cut it into pieces, where the cutting positions are defined in an int array A. The positions are guaranteed to be in ascending order in the range of [1, L - 1]. The cost of each cut is the length of the stick segment being cut. Determine the minimum total cost to cut the stick into the defined pieces.

Examples

L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)
*
*--这题也太难了！！！！！！！！，需要二刷
*
*
*
* index :       0       1       2           3           4
* cuts:                 2       4           7
* helper        0       2       4           7           10
*
* L:            0   1   2   3   4   5   6   7   8   9   10
*
*
* m[j][i] 代表从jth index 到 ith index 实现cut 好需要的minimum total cost
*
* 左大段，右大段
*
* size = 1(base case, 不用切就是好的, cost = 0)
* m[0][1] = m[1][2] = m[2][3] = m[3][4] = 0
*
* size = 2 中间只能被切一刀, 切这一刀带来的cost 是helper[i] - helper[j]
* m[0][2] = helper[2] - helper[0] + m[0][1] + m[1][2] = 4
* m[1][3] = helper[3] - helper[1] + m[1][2] + m[2][3] = 5
* m[2][4] = helper[4] - helper[2] + m[2][3] + m[3][4] = 6
*
* size = 3 中间可以被切两刀， 切这一刀带来的cost 是helper[i] - helper[j], 在[j + 1, i - 1]的index范围内,遍历这一刀的位置，并且计算刀落在不同位置时 m[j][k] + m[k][i]的最小值
* m[0][3] = helper[3] - helper[0] + Min{m[0][1] + m[1][3], m[0][2] + m[2][3]} = 7 + 4 = 11
* m[1][4] = helper[4] - helper[1] + Min{m[1][2] + m[2][4], m[1][3] + m[3][4]} = 8 + 5 = 13
*
*
* size = 4 中间可以被切3刀 m[0][4] 即为最终结果
* m[0][4] = helper[4] - helper[0] + Min{m[0][1] + m[1][4], m[0][2] + m[2][4], m[0][3] + m[3][4]} = 10 + min{13, 4 + 6, 11} = 10 + 10 = 20
*
*
*
*
*
*
* 填表：对角线从左上到右上
* 实现的时候，因为每个数只依赖左边和下面 eg. 11 依赖的是横向的0， 4 和纵向的0， 5，从下到上，从左往右实现
*
*        i
*        0   1   2   3   4
*
* j
* 0          0   4   11  20
*
* 1              0   5   13
*
* 2                  0   6
*
* 3                      0
*
* 4
*
*
* */

public class CuttingWood {
    public int minCost(int[] cuts, int length) {
        //只有cut 了才产生cost
        if(cuts == null || length == 0){
            return 0;
        }
        //构造包括起始点的辅助数组
        int[] helper = new int[cuts.length + 2];
        helper[0] = 0;
        helper[helper.length - 1] = length;
        for(int i = 1; i < helper.length - 1; i++){
            helper[i] = cuts[i - 1];
        }

        //填表， 由左到右，由下到上    [j, i]
        int[][] m = new int[helper.length][helper.length];
        //i 是column，从左到右
        for(int i = 1; i <= helper.length - 1; i++){
            //j是row, 从下到上
            for(int j = i - 1; j >= 0; j--){
                if(j + 1 == i){
          /*
          base case: [0,1] [1,2]....[helper[length - 2], helper[length - 1]]
          */
                    m[j][i] = 0;
                }else{
          /*
          对于每一个 j < k < i
          左大段:[j, k] 右大段：[k, i]
          取所有k 组成的{左大段 + 右大段}的最小值 minSum
          m[j][i] = helper[i] - helper[j] + minSum
          */
                    int min = Integer.MAX_VALUE;
                    for(int k = j + 1; k <= i - 1; k++){
                        min = Math.min(min, m[j][k] + m[k][i]);
                    }
                    m[j][i] = helper[i] - helper[j] + min;
                }
            }
        }
        return m[0][helper.length - 1];


    }


}
