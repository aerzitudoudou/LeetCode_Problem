public class RotateArray_LC189 {
    //!!!sol1, my, O(n), O(1)<iterative> or O(n)<recursive>

    public void rotate(int[] nums, int k) {
        int l = nums.length;
        //be careful! not to use k but k%l, in order to get the real offset needs rotating, e.g. [1, 2], k = 7  k% 2 = 1 real offset is 1
        int rl = k % l;
        if(rl == 0) return;
        //"i love yahoo"
        helper1(nums, 0,  l - rl - 1);
        helper1(nums, l - rl, l - 1);
        helper1(nums, 0, l - 1);


    }

    //iterative
     private void helper1(int[] ary, int i, int j){
         while(i < j){
             int tmp = ary[i];
             ary[i] = ary[j];
             ary[j] = tmp;
             i++;
             j--;
         }
     }

    //recursive
    private void helper2(int[] ary, int i, int j){
        if(i >= j) return;
        int tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
        helper2(ary, i + 1, j - 1);
    }

}
