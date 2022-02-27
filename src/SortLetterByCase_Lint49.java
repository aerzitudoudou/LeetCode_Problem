public class SortLetterByCase_Lint49 {
    //!!!sol1, my, O(n), O(1)
    public void sortLetters(char[] chars) {
        int l = 0, r = chars.length - 1;
        while(l <= r){
            while(l <= r && Character.isLowerCase(chars[l])) l++;
            while(l <= r && Character.isUpperCase(chars[r])) r--;
            if(l <= r){
                swap(chars, l, r);
                l++;
                r--;
            }
        }


    }
    private void swap(char[] chars, int l, int r){
        char tmp = chars[l];
        chars[l] = chars[r];
        chars[r] = tmp;
    }
}
