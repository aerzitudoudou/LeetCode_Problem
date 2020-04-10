import java.util.Deque;
import java.util.LinkedList;

public class BackspaceCompare {
    //way1:  S.length = M T.length = N
    // T: O(M + N)
    //S: O(M + N)
    public boolean backspaceCompare(String S, String T) {
        if(generate(S).equals(generate(T))){
            return true;
        }
        return false;
    }

    private String generate(String s){
        //pollFirst
        //offerFirst
        Deque<Character> stack = new LinkedList<>();
        for(int i = 0; i < s.length(); i++){
            char tmp = s.charAt(i);
            if(tmp != '#'){
                stack.offerFirst(tmp);

            }else{
                if(!stack.isEmpty()){
                    stack.pollFirst();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.insert(0, stack.pollFirst());
        }
        return sb.toString();

    }


    //Way2 !!!!!: T: O(M + N) S: O(1)
    public boolean backspaceCompare2(String S, String T) {
        //corner case:
        if (S == null || T == null) {
            return false;
        }
        int i = S.length() - 1;
        int j = T.length() - 1;
        int ss = 0, st = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    ss++;
                    i--;
                } else if (ss > 0) {
                    i--;
                    ss--;
                } else break;
            }
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    st++;
                    j--;
                } else if (st > 0) {
                    j--;
                    st--;
                } else break;

            }
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) {
                return false;
            }
            if ((i >= 0) != (j >= 0)) {
                return false;
            }
            i--;
            j--;

        }

        return true;

    }

}
