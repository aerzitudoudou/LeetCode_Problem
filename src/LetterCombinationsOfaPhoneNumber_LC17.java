import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfaPhoneNumber_LC17 {

    //sol1, mine, T: O(4^n * n) , n = digits length, S: O(n)
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits == null || digits.length() == 0) return res;

        char[] ary = new char[digits.length()];
        int index = 0;
        Map<Character, char[]> map = Map.of(
                '2', new char[]{'a','b','c'},
                '3', new char[]{'d','e','f'},
                '4', new char[]{'g','h','i'},
                '5', new char[]{'j','k','l'},
                '6', new char[]{'m','n','o'},
                '7', new char[]{'p','q','r','s'},
                '8', new char[]{'t','u','v'},
                '9', new char[]{'w','x','y','z'}
        );

        char[] digitAry = digits.toCharArray();
        dfs(res, ary, index, map, digitAry);

        return res;
    }
    private void dfs(List<String> res, char[] ary, int index, Map<Character, char[]> map, char[] digitAry){
        if(index == ary.length){
            res.add(String.valueOf(ary));
            return;
        }
        char curDigit = digitAry[index];
        char[] letters = map.get(curDigit);
        for(int i = 0; i < letters.length; i++){
            ary[index] = letters[i];
            dfs(res, ary, index + 1, map, digitAry);

        }


    }
}
