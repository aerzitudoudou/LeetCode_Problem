public class ReverseWordsInAStringIII {
    public String reverseWords(String s) {
        //sol1, my， O(n), O(n)
        char[] ary = s.toCharArray();
        int left = 0, right = 0;
        int l = s.length();
        while(right < l){
            while(right < l && ary[right] != ' ') right++;
            reverse(ary, left, right - 1);
            right++;
            left = right;
        }

        return String.valueOf(ary);
    }

    private void reverse(char[] ary, int left, int right){
        while(left < right){
            char tmp = ary[left];
            ary[left] = ary[right];
            ary[right] = tmp;
            left++;
            right--;
        }
    }

}
