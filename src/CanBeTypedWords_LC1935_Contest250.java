import java.util.HashSet;
import java.util.Set;

public class CanBeTypedWords_LC1935_Contest250 {
    public int canBeTypedWords(String text, String brokenLetters) {
        Set<Character> set = new HashSet<>();
        for(char c : brokenLetters.toCharArray()){
            set.add(c);
        }

        String[] ary = text.split(" ");
        int count = ary.length;
        for(String word : ary){
            for(char c : word.toCharArray()){
                if(set.contains(c)) {
                    count--;
                    break;
                }
            }
        }

        return count;

    }
}
