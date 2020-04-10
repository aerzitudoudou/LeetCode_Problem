import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public String removeSpaces(String input){
        if(input == null || input.length() == 0){
            return input;
        }
        char[] ary = input.toCharArray();
        int s = 0;
        for(int f = 0; f < ary.length; f++){
            if(ary[f] == ' ' && s != 0 && ary[s - 1] != ' ' || ary[f] != ' '){
                ary[s] = ary[f];
                s++;
            }
        }
        if(s > 0 && ary[s - 1] == ' '){
            s--;
        }

        return new String(ary, 0 ,s);
    }
}
