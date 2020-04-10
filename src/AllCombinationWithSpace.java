import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllCombinationWithSpace {
    public void allPermutations(String a){
        StringBuilder sb =new StringBuilder();
        if(a == null || a.length() == 0){
            return;
        }
        allPermutations(a, 0, sb);
    }


    private void allPermutations(String a,  int index, StringBuilder sb){
        if(index == a.length() - 1){
            sb.append(a.charAt(a.length() - 1));
            System.out.println(sb.toString());
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        sb.append(a.charAt(index));
        allPermutations(a, index + 1, sb);
        sb.append(' ');
        allPermutations(a, index + 1, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);


    }

}
