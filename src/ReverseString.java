public class ReverseString {
    public String reverse(String input) {
        //   //method 1: iterative
        //   char[] ary = input.toCharArray();
        //   int i = 0, j = ary.length - 1;
        //   while(i <= j){
        //     swap(ary, i, j);
        //     i++;
        //     j--;
        //   }
        //   return new String(ary);
        // }
        //  method 2: recursive
        char[] ary = input.toCharArray();
        helper(ary, 0, ary.length - 1);

        return new String(ary);

    }
    private void helper(char[] ary, int i, int j){
        if(i >= j){
            return;
        }
        swap(ary, i, j);

        helper(ary, ++i, --j);
    }
    private void swap(char[] ary, int i, int j){
        char tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }
}
