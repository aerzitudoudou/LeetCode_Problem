public class ValidPalindrome_LC125 {

    //sol1, my, 2022/02/13, O(n), O(1)
    public boolean isPalindrome0(String s) {
        if(s == null) return false;
        int p1 = 0, p2 = s.length() - 1;
        while(p1 < p2){
            while(p1 < p2 && !Character.isLetterOrDigit(s.charAt(p1))) p1++;
            while(p1 < p2 && !Character.isLetterOrDigit(s.charAt(p2))) p2--;
            if(Character.toUpperCase(s.charAt(p1)) != Character.toUpperCase(s.charAt(p2))) return false;
            else{
                p1++;
                p2--;
            }
        }

        return true;

    }


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

    //!!!sol3, clean code, O(n), O(1)
    public boolean isPalindrome3(String s) {
        int i = 0, j = s.length() - 1;
        while(i <= j){
            if(!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }else if(!Character.isLetterOrDigit(s.charAt(j))){
                j--;
            }else if(Character.toUpperCase(s.charAt(i)) == Character.toUpperCase(s.charAt(j))) {
                i++;
                j--;
            }else return false;
        }
        return true;
    }
}
