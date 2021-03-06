/*
*
*
* Laicode
*
*
*176. Longest Common Substring
Medium
Find the longest common substring of two given strings.

Assumptions

The two given strings are not null
Examples

S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd”
*
*
* -具体坐标怎么下都行，可以跟着dp[] 走，也可以跟着原数组走:
* 跟着dp走，dp: i, j   原数组（i - 1, j - 1）
* 跟着原数组走， dp: i + 1, j + 1 原数组：（i, j）
*
* -恒定不变的是dp 数组 size = 原数组size + 1 仅此而已。
*
*
*
*
* 其他矩阵问题有offsite的: Spiral Order Traverse I
*
*
*
* */


import java.util.Arrays;

public class LongestCommonSubstring {
    //way 1: T: O(m * n) m and n are length of source and target, respectively  S:O(m * n)
    public String longestCommon(String source, String target) {
        String res = "";

        if(source == null || source.length() == 0 || target == null || target.length() == 0){
            return res;
        }
        String[][] m = new String[source.length() + 1][target.length() + 1];
        //i,j 跟着数组走
        for(int i = 0; i <= source.length(); i++){
            for(int j = 0; j <= target.length(); j++){
                if(i == 0 || j == 0){
                    m[i][j] = "";
                }else if(source.charAt(i - 1) == target.charAt(j - 1)){
                    m[i][j] = m[i - 1][j - 1] + source.charAt(i - 1);
                    res = m[i][j].length() > res.length() ? m[i][j] : res;
                }else{
                    m[i][j] = "";
                }
            }
        }
        return res;

    }

    //way2: 滚动数组减少空间复杂度 T: O(m * n) S: O(min(m,n))
    public String longestCommon2(String source, String target) {
        String res = "";

        if(source == null || source.length() == 0 || target == null || target.length() == 0){
            return res;
        }
        //相向而行就可以用滚动数组了
        int smallerLen = source.length() < target.length() ? source.length() : target.length();
        String smaller = source.length() < target.length() ? source : target;
        int largerLen = source.length() < target.length() ? target.length() : source.length();
        String larger = source.length() < target.length() ? target : source;
        String[] m = new String[smallerLen + 1];
        Arrays.fill(m, "");
        //i,j跟着字符串走
        for(int i = 0; i < largerLen; i++){
            for(int j = smallerLen - 1; j >= 0; j--){

                if(smaller.charAt(i) == larger.charAt(j)){
                    m[j + 1] = m[j] + smaller.charAt(i);
                    res = m[j + 1].length() > res.length() ? m[j + 1] : res;
                }else{
                    m[j + 1] = "";
                }
            }
        }
        return res;

    }




    //way3: 滚动数组减少空间复杂度 T: O(m * n) S: O(min(m,n)) 另一种实现方法： i跟着字符串走, j 跟着dp[] 走
    public String longestCommon3(String source, String target) {
        String res = "";

        if(source == null || source.length() == 0 || target == null || target.length() == 0){
            return res;
        }
        //相向而行就可以用滚动数组了
        int smallerLen = source.length() < target.length() ? source.length() : target.length();
        String smaller = source.length() < target.length() ? source : target;
        int largerLen = source.length() < target.length() ? target.length() : source.length();
        String larger = source.length() < target.length() ? target : source;
        String[] m = new String[smallerLen + 1];
        Arrays.fill(m, "");
        //i跟着字符串走,j跟着m[]走
        for(int i = 0; i < largerLen; i++){
            for(int j = smallerLen; j >= 1; j--){

                if(smaller.charAt(j - 1) == larger.charAt(i)){
                    m[j] = m[j - 1] + smaller.charAt(j - 1);
                    res = m[j].length() > res.length() ? m[j] : res;
                }else{
                    m[j] = "";
                }
            }
        }
        return res;

    }


    //本程序是按照offset from -5 to 5 的顺序, 平行对角线从右到左， 每一条对角线从左上，到右下，遍历过整个的matrix
    //T: O(n ^ 2) S: O(1)
    public String longestCommon4(String source, String target) {
        String res = "";
        if(source == null || source.length() == 0 || target == null || target.length() == 0){
            return res;
        }
        int n1 = source.length(), n2 = target.length();
        for(int offset = -n1 + 1; offset < n2; offset++){
            String cur = "";
            for(int i = Math.max(offset, 0); i - offset < n1 && i < n2; i++){
                if(source.charAt(i - offset) == target.charAt(i)){
                    cur = cur + source.charAt(i - offset);
                    res = cur.length() > res.length() ? cur : res;
                }else{
                    cur = "";
                }
            }
        }
        return res;
    }



    //本程序是按照offset from 5 to -5 的顺序, 平行对角线从左到右， 每一条对角线从左上，到右下，遍历过整个的matrix

    public String longestCommon5(String source, String target) {
        String res = "";
        if(source == null || source.length() == 0 || target == null || target.length() == 0){
            return res;
        }
        int n1 = source.length(), n2 = target.length();
        for(int offset = n2 - 1; offset >= -(n1 + 1); offset--){
            String cur = "";
            for(int i = Math.max(offset, 0); i - offset < n1 && i < n2; i++){
                if(source.charAt(i - offset) == target.charAt(i)){
                    cur = cur + source.charAt(i - offset);
                    res = cur.length() > res.length() ? cur : res;
                }else{
                    cur = "";
                }
            }
        }
        return res;
    }



    //way4: 因为只用到了m[i - 1][j - 1], 可以一个对角线，一个对角线的扫，在该对角线上只需要记录前一个的值 最后求最大值
    /*
    * source: aaaaaa   target: bbaaba
    *
    *
    * offset(x axis)             0     -1    -2    -3    -4    -5
    * index(x axis)              0     1     2     3     4      5
    * source                     a     a     a     a     a      a
    *
    *index(y)  offset(y) target
    *
    * 0         0         b      *     *     *     *     *       *
    *                               \     \     \     \      \
    * 1         1         b      *     *     *     *     *       *
    *                               \     \     \     \      \
    * 2         2         a      a     a     a     a     a       a
    *                               \     \      \     \     \
    * 3         3         a      a     aa    aa    aa    aa      aa
    *                               \     \     \     \      \
    * 4         4         b      *     *     *     *     *       *
    *                                \     \     \     \     \
    * 5         5         a      a     a     a     a     a       a
    *
    *
    * */



    //平行对角线从右到左扫， 每一条对角线从右上，到左下，遍历过整个的matrix
    /*
offset    0  -1  -2  -3  -4

          1   2   3   4   5
    1     6   7   8   9   10
    2     11  12  13  14  15
    3     16  17  18  19  20
    *
    *
    *
    *
    *
    * */
    int[][] matrix = {{1,2,3,4,5}, {6,7,8,9,10}, {11,12,13,14,15}, {16,17,18,19,20}};

    public void sweepDirectionUpDownSweepRightLeft(){

        int n1 = matrix.length; //5
        int n2 = matrix[0].length; //3
        //TODO: 怎么扫？
    }





}
