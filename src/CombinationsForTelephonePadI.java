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

    //way 2: TODO:答案优雅的做法


}
