public class ValidWordAbbreviation_LC408 {
    public boolean validWordAbbreviation(String word, String abbr) {
        if(word == null || abbr == null) return false;
        int i = 0, j = 0;
        while(i < word.length() && j < abbr.length()){
            if(word.charAt(i) == abbr.charAt(j)){
                i++;
                j++;
                continue;
            }
            if(abbr.charAt(j) <= '0' || abbr.charAt(j) > '9'){
                return false;
            }
            int numStartIndex = j;
            while(j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9'){
                j++;
            }
            int numValue = Integer.valueOf(abbr.substring(numStartIndex, j));
            i += numValue;
        }

        return i == word.length() && j == abbr.length();

    }
}
