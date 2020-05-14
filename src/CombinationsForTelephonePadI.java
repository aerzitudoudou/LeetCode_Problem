import java.util.ArrayList;
import java.util.List;
/**
 *laicode
 * 272. Combinations For Telephone Pad I
 * Medium
 * Given a telephone keypad, and an int number, print all words which are possible by pressing these numbers, the output strings should be sorted.
 *
 * {0 : "", 1 : "", 2 : "abc", 3 : "def", 4 : "ghi", 5 : "jkl", 6 : "mno", 7 : "pqrs", 8 : "tuv", 9 : "wxyz"}
 *
 * Assumptions:
 *
 * The given number >= 0
 * Examples:
 *
 * if input number is 231, possible words which can be formed are:
 *
 * [ad, ae, af, bd, be, bf, cd, ce, cf]
 *
 *
 *
 *
 *
 */
//N = number of digits in the input number
//T: (4(worst branch number) ^ N) * N
//S: O(N)
public class CombinationsForTelephonePadI {
    //way1 : my way 不太优雅的做法
    public String[] combinations(int number) {
        StringBuilder num = new StringBuilder();
        while(number > 0){
            num.insert(0, number % 10);
            number /= 10;
        }
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(list, 0, num.toString(), sb);
        String[] res = {};
        return list.toArray(res);

    }
    //                 最后结果           当前层     231           cur:当前结果
    private void dfs(List<String> list, int index, String str, StringBuilder sb){
        if(index == str.length()){
            list.add(sb.toString());
            return;
        }
        if(str.charAt(index) == '0' || str.charAt(index) == '1'){
            dfs(list, index + 1, str, sb);
        }


        if(str.charAt(index) == '2'){
            helper(sb, list, index, str, 'a', 'c');
        }
        if(str.charAt(index) == '3'){
            helper(sb, list, index, str, 'd', 'f');
        }
        if(str.charAt(index) == '4'){
            helper(sb, list, index, str, 'g', 'i');

        }
        if(str.charAt(index) == '5'){
            helper(sb, list, index, str, 'j', 'l');
        }
        if(str.charAt(index) == '6'){
            helper(sb, list, index, str, 'm', 'o');
        }
        if(str.charAt(index) == '7'){
            helper(sb, list, index, str, 'p', 's');
        }
        if(str.charAt(index) == '8'){
            helper(sb, list, index, str, 't', 'v');
        }
        if(str.charAt(index) == '9'){
            helper(sb, list, index, str, 'w', 'z');
        }

    }

    private void helper(StringBuilder sb, List<String> list, int index, String str, char start, char end){
        for(char c = start; c <= end; c++){
            sb.append(c);
            dfs(list, index + 1, str, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    //way 2:  把index 和字母的对应关系存在数组里
    public String[] combinations2(int number) {
        //number 到String 不需要除模， 一个String.valueOf 或者Integer.toString() 就解决了
        String numStr = String.valueOf(number);
        List<String> res = new ArrayList();
        StringBuilder sb = new StringBuilder();
        //建立map 的关系
        String[] numToChar = {"", "", "abc", "def", "ghi",  "jkl", "mno",  "pqrs", "tuv", "wxyz"};
        dfs2(res, 0, numStr, sb, numToChar);
        //注意list to array: list.toArray(空同类型数组)
        String[] ary = {};
        return res.toArray(ary);

    }

    private void dfs2(List<String> res, int index, String numStr, StringBuilder sb, String[] numToChar){
        if(index == numStr.length()){
            res.add(sb.toString());
            return;
        }

        //char to int:
        int numToCharIndex = numStr.charAt(index) - '0';
        String value = numToChar[numToCharIndex];
        if(value.length() == 0){
            dfs2(res, index + 1, numStr, sb, numToChar);
        }else{
            for(int i = 0; i < value.length(); i++){
                sb.append(value.charAt(i));
                dfs2(res, index + 1, numStr, sb, numToChar);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }




}
