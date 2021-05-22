
//lc 921
//greedy, O(n) O(1)
public class MinimumAddToMakeParenthesesValid {
    public int minAddToMakeValid(String s) {
        int leftCount = 0, res = 0;
        for(char c : s.toCharArray()){
            if(c == '('){
                leftCount++;
            }else{
                if(leftCount > 0) leftCount--;
                else res++;
            }
        }
        return leftCount + res;
    }

}
