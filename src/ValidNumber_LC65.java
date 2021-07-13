public class ValidNumber_LC65 {
    /*
   from https://happygirlzt.com/code/65.html
   1. trim leading and trailing spaces
   2. 4 types of "could be right" char:
      digit   flag: seenDigit
      +/_
      .       flag: seendot
      e/E     flag: seenE
   3. loop each character and check on the 4 types
   */
    public boolean isNumber(String s) {
        String str = s.trim();
        boolean seenDigit = false, seenDot = false, seenE = false;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                seenDigit = true;
            }else if(c == '+' || c == '-'){ //can only apprear at index = 0 or after e
                if(i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') return false;

            }else if(c == '.'){
                if(seenDot || seenE) return false;
                seenDot = true;
            }else if(c == 'e' || c == 'E'){
                if(seenE || !seenDigit) return false;
                seenE = true;
                seenDigit = false;
            }else return false;
        }

        return seenDigit;
    }
}
