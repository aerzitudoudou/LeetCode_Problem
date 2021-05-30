public class ValidPalindrome_LC125 {


    public boolean isPalindrome2(String s) {

        for(int start = 0, end = s.length() - 1; start < end; start++, end--){
            while(start < end && !Character.isLetterOrDigit(s.charAt(start))) start++;
            while(start < end && !Character.isLetterOrDigit(s.charAt(end))) end--;
            if(start < end && Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))){
                return false;
            }
        }
        return true;

    }
    public boolean isPalindrome(String s) {
        for(int start = 0, end = s.length() - 1; start < end; start++, end--){
            while(start < end && !String.valueOf(s.charAt(start)).matches("[0-9a-zA-Z]"))start++;
            while(start < end && !String.valueOf(s.charAt(end)).matches("[0-9a-zA-Z]")) end--;
            if(start < end && Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))){
                return false;
            }
        }
        return true;


    }


    public boolean isPalindrome1(String s) {
        int start = 0, end = s.length() - 1;
        while(start < end){
            while(start < end && !Character.isLetterOrDigit(s.charAt(start))) start++;
            while(start < end && !Character.isLetterOrDigit(s.charAt(end))) end--;
            if(start < end && Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))){
                return false;
            }else{
                start++;
                end--;
            }

        }
        return true;

    }
}
