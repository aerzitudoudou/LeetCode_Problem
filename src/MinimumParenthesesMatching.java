import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
*
* Lintcode 317
* Description
Give a contain '(',') ', '*' string, allowing the '*' into '(' or')'or '', return the shortest parentheses to match the legal sequence. If there are more than one answer, return the lexicographical order of the smallest one. if no solution exists,return“No solution!”.

|s| <= 100000∣s∣≤100000
The answer can be an empty string

Have you met this question in a real interview?
*
*
*
* */
public class MinimumParenthesesMatching {
    //way1: 暴力 TLE
    public String minimumParenthesesMatching(String s) {
        //dfs + 判断是否是valid
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        dfs(index, sb, list, s);

        //find legal and compare to find the smallest string lexicographically
        return findLegal(list);

    }

    private void dfs(int index, StringBuilder sb, List<String> list, String s){

        //recursion rule
        while(index < s.length() && s.charAt(index) != '*'){
            sb.append(s.charAt(index));
            index++;
        }
        if(index == s.length()){
            list.add(sb.toString());
            return;
        }

        if(s.charAt(index) == '*'){
            int sbPreLen = sb.length();
            //case 1
            sb.append('(');
            dfs(index + 1, sb, list, s);
            sb.delete(sbPreLen, sb.length());

            //case 2
            sb.append(')');
            dfs(index + 1, sb, list, s);
            sb.delete(sbPreLen, sb.length());

            //case 3
            dfs(index + 1, sb, list, s);
            sb.delete(sbPreLen, sb.length());
        }

    }


    private String findLegal(List<String> list){
        String res = "No solution!";

        for(String str : list){
            if(str.equals("(())()(())")){
                System.out.println("found answer!");
            }
            if(str.equals("(())()()(())")){
                System.out.println("found bad answer!");
            }
            boolean flag = true;
            Deque<Character> stack = new LinkedList<>();
            for(int i = 0; i < str.length(); i++){
                char c = str.charAt(i);
                if(c == '('){
                    stack.offerFirst(str.charAt(i));
                }else if(c == ')' && stack.size() > 0){
                    stack.pollFirst();
                }else{
                    flag = false;
                    break;
                }

            }
            if(flag && stack.size() == 0 ){
                if(res == "No solution!" || str.compareTo(res) == -1){
                    res = str;
                }
            }
        }
        return res;
    }

    //way2: 答案方法 greedy
    //TODO: 注意对比与valid parenthesis string 的思路
    /*
    * two pass， greedy
    * 从左往右 把不得不加左括号的地方加上左括号（ '（'尽量加到最左边星星 e.g. *1*11, 需要加个0， 如果保证字典序最小的话0应该加到第一个*的位置）
    * 从右往左 把不得不加上右括号的地方加上右括号（‘）’ 尽量加到最右边星星以保证结果字典序最小， 因为 ')' 字典序 > '(' 字典序）
    *
    *
    * */
    public String minimumParenthesesMatching2(String s) {
        if(s == null){
            return s;
        }
        char[] ary = s.toCharArray();
        //queue:存* 从左至右出现的index
        Deque<Integer> queue = new LinkedList<>();
        //count: ‘（ ’比 ‘）’的数量多多少
        int count = 0;
        //从左往右扫, 左边>= 右边 就没事儿 一旦左边<右边 最左边的*换成（
        for(int i = 0; i < s.length(); i++){
            if(ary[i] == '*'){
                queue.offerFirst(i);
            }else if(ary[i] == '('){
                count++;
            }else{
                count--;
            }
            if(count < 0){
                if(queue.isEmpty()){
                    return null;
                }
                int index = queue.pollLast();
                ary[index] = '(';
                count++;
            }

        }
        queue = new LinkedList<>();
        //count: ')'比 ‘(’的数量多多少
        count = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            if(ary[i] == '*'){
                queue.offerFirst(i);
            }else if(ary[i] == ')'){
                count++;
            }else{
                count--;
            }
            if(count < 0){
                if(queue.isEmpty()){
                    return null;
                }
                int index = queue.pollLast();
                ary[index] = ')';
                count++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ary.length; i++){
            if(ary[i] != '*'){
                sb.append(ary[i]);
            }
        }
        return sb.toString();

    }




}
