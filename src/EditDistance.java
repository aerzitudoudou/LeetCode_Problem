/*
*
*
* Lintcode 119. Edit Distance
This is the problem you have done. Do you remember how to solve it?
Description
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Have you met this question in a real interview?
Example
Example 1:

Input:
"horse"
"ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input:
"intention"
"execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
*
*
*
*
* */


public class EditDistance {

    public int editDistance(String one, String two) {
     /*
      m[i][j] represents minimum nubmer of operations taken from one.substring(0, i) to two.substring(0,j)
      就是从one 的前i个字母到two 的前j 个字母所用的最小的operation
      m[0][0] 表示从empty string one 到empty string two 所用的最小的opertion

      operation:
                                        前i个数的最后一个index 是i - 1, 新来的被观察的是array[i - 1] 0...i - 1代表了array的前i个数，所以在m里是m[i][j]
      if(array[i - 1] == array[j - 1]) => m[i][j] = m[i - 1][j - 1]

      replace:
      m[i][j] = m[i - 1][j - 1] + 1

      insert:
      one = a + b   => one = a
      two = b          two = ''
      m[i][j] = m[i][j - 1] + 1


      delete；
      one = a    => one = ''
      two = b       two = b
      m[i][j] = m[i - 1][j] + 1

      m[i][j] = min{m[i - 1][j - 1] + 1,  m[i][j - 1] + 1, m[i - 1][j] + 1}


      String one: sigh
      String two: asith
        s i g h
      0 1 2 3 4
  a   1 1 2 3 4
  s   2 1 2 3 4
  i   3 2 1 2 3
  t   4 3 2 2 3
  h   5 4 3 3 2
     */


        int[][] m = new int[one.length() + 1][two.length() + 1];

        for(int i = 0; i <= one.length(); i++){
            for(int j = 0; j <= two.length(); j++){
                if(i == 0){
                    m[i][j] = j;
                }else if(j == 0){
                    m[i][j] = i;
                }else if(one.charAt(i - 1) == two.charAt(j - 1)){
                    m[i][j] = m[i - 1][j - 1];
                }else{
                    m[i][j] = Math.min(m[i - 1][j] + 1, m[i][j - 1] + 1);
                    m[i][j] = Math.min(m[i - 1][j - 1] + 1, m[i][j]);
                }
            }
        }
        return m[one.length()][two.length()];
    }
}
