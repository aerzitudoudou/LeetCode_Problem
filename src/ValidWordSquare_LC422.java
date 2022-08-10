import java.util.List;

public class ValidWordSquare_LC422 {
    //from https://www.youtube.com/watch?v=bP2H9lAq3E8, O(n^2), O(1)
    public boolean validWordSquare(List<String> words) {
        int n = words.size();
        for(int i = 0; i < n; i++){
            String word = words.get(i);
            for(int j = 0; j < word.length(); j++){
                char c1 = word.charAt(j);//char at ith row jth column
                if(j >= n || i >= words.get(j).length() || words.get(j).charAt(i) != c1) return false;//check if jth row ith column is valid, and if so, if char(ith row jth col) = char(j th row ith col)
            }
        }

        return true;

    }
}
