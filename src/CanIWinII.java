/*
*
* Laicode
*
* 657. Can I Win II
Medium
There is an array of positive integers, in which each integer represents a piece of Pizza’s size, you and your friend take turns to pick pizza from either end of the array.
* Your friend follows a simple strategy: He will always pick the larger one he could get during his turn.
* The winner is the one who gets the larger total sum of all pizza. Return the max amount of pizza you can get.

Assumption: If during your friend's turn, the leftmost pizza has the same size as the rightmost pizza, he will pick the rightmost one.

Example:

Input: [2,1,100,3]

Output: 102

Explanation: To win the game, you pick 2 first, then your friend will pick either 3, after that you could pick 100. In the end you could get 2 + 100 = 102, while your friend could only get 1 + 3 = 4.
*
*
*--dp 也是从对角线向右上角走
*
*
* index:             0     1      2      3
* nums:              1     10     2      7
*
*
* m[i][j] represents the max amount I can get from between [i, j]
* base case:
* 1. m[0][0] = 1, m[1][1] = 10, m[2][2] = 2 m[3][3] = 7
* 2. m[0][1] = 10, m[1][2] = 10, m[2][3] = 7  ==> m[i][i + 1] = max(nums[i], nums[i + 1])
* induction rule:
* case 1: I pick the leftmost i.e. pick nums[i]      剩下[i + 1, j]
* sum1 = m[i + 2][j]                 if nums[i + 1] > nums[j]
*      = m[i + 1][j - 1]             otherwise
* m[i][j] = nums[i] + sum1
*
*
* case 2: I pick the rightmost i.e. pick nums[j]     剩下[i, j - 1]
* sum2 = m[i][j - 2]                  if nums[j - 1] > nums[i]
*      = m[i + 1][j - 1]              otherwise
* m[i][j] = nums[j] + sum2
*
* m[i][j] = max(case1, case2)
*
*
*
*
*
* 填表：从左到右， 从下到上  有数值的为base case(所以这个dp是从对角线开始往右上走) * 代表可以根据induction rule 推出来的值, 求* 只需要知道它下面和右边的值，所以for for loop的顺序是左 --> 右 下--> 上
* 最终结果res = m[0][3]
*
*      j  0    1    2     3
* i
* 0       1    10   *     *
*
* 1            10   10    *
*
* 2                 2     7
*
* 3                       7
*
*
*
*
*
* */

public class CanIWinII {
    public int canWin(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[][] m = new int[nums.length][nums.length];
        for(int j = 0; j < nums.length; j++){
            for(int i = j; i >= 0; i--){
                if(i == j){
                    m[i][j] = nums[i];
                }else if(i + 1 == j){
                    m[i][j] = Math.max(nums[i], nums[i + 1]);
                }else{
                    int sum1 = nums[i + 1] > nums[j] ? nums[i] + m[i + 2][j] : nums[i] + m[i + 1][j - 1];
                    int sum2 = nums[i] > nums[j - 1] ? nums[j] + m[i + 1][j - 1] : nums[j] + m[i][j - 2];
                    m[i][j] = Math.max(sum1, sum2);
                }
            }
        }
        return m[0][nums.length - 1];
    }
}
