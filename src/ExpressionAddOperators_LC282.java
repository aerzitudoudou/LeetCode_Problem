import java.util.ArrayList;
import java.util.List;

class ExpressionAddOperators_LC282 {

    /*
    preStr
    curPos: 当前层
    lastVal: [0, curPos - 1] 最后一个多项式的值
    preVal: [0, curPos - 1] 的值


    curVal = preVal - lastVal + lastVal * curVal

            curpos
    2+3*4 * 5  67
    2+3+4 * 5  67

    T: n * 4^n (can be a tree with 4 branches: + - * and "" which is the digit connect case. total number of nodes int the recursion tree； 4^n. writing to the result is n per element.)
    S: n * 4^n (include result) N(sb, doesn't include result)

    1234 the recursion tree of my way: hard to calculate nodes. n^n in order to find lower bound see below tree 2
    tree 1:
              1
          |     / \
          2   23  234
         /\    |
        3 34   4
        |
        4


    another way of thinking how many nodes are in the tree
                            1
         +/                    -|   *|   ""\
        1+2                    1-2  1*2    12
       /     |    |     \
      1+2+3 1+2-3 1+2*3 1+23


      4 branches on each level, n numbers of levels


      another way to think time complexity: how many possible result? 1 _2_ 3_ 4_ 5 have n numbers of "_", where each "_" position can be: +,-,*, ""(connect together). therefore 4^n possible results.

    */
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(num, target, 0, 0, 0, sb, res);
        return res;
    }

    private void dfs(String num, int target, int curPos, long preVal, long lastVal, StringBuilder sb, List<String> res){
        int l = num.length();

        if(curPos == l){
            if(preVal == target){
                res.add(sb.toString());
            }
            return;
        }
        //012   3  i
        //123   45 67
        //1+2*3*45

        //num = 1234567 target=6 curpos =3 preVal =  7 lastVal = 6 sb: 1+2*3   res
        for(int i = curPos + 1; i <= l; i++){

            String curStr = num.substring(curPos, i);
            int curLen = curStr.length();
            if(curLen > 1 && curStr.charAt(0) == '0') break;
            long curVal = Long.valueOf(curStr);

            if(curPos == 0){
                sb.append(curStr);
                dfs(num, target, i, curVal, curVal, sb, res);
                sb.delete(sb.length() - curLen, sb.length());
            }else{
                //"+"
                sb.append("+");
                sb.append(curStr);
                dfs(num, target, i, preVal + curVal, curVal, sb, res);
                sb.delete(sb.length() - (curLen + 1), sb.length());

                //"-"

                sb.append("-");
                sb.append(curStr);
                dfs(num, target, i, preVal - curVal, -curVal, sb, res);
                sb.delete(sb.length() - (curLen + 1), sb.length());

                //"*"

                sb.append("*");
                sb.append(curStr);
                dfs(num, target, i, preVal - lastVal + lastVal * curVal, lastVal * curVal, sb, res);
                sb.delete(sb.length() - (curLen + 1), sb.length());
            }
        }
    }
}
