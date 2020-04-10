public class ReverseWords {
    public String reverseWords(String input) {
        if(input == null){
            return null;
        }
        char[] ary = input.toCharArray();
        char[] rev = reverse(ary, 0 , ary.length - 1);
        int start = 0;
        for(int i = 0; i < rev.length; i++){
            if(rev[i] != ' ' && (i == 0 || rev[i - 1] == ' ')){
                start = i;
            }
            if(rev[i] != ' ' && (i == rev.length - 1 || rev[i + 1] == ' ')){
                reverse(rev, start, i);
            }
        }
        return new String(rev);
    }


    private char[] reverse(char[] ary, int i, int j){
        while(i < j){
            swap(ary, i++, j--);
        }
        return ary;
    }

    private void swap(char[] ary, int i, int j){
        char tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }
}
