public class ReOrderArray {
    public int[] reorder(int[] array) {
        if(array.length % 2 == 0){
            helper(array, 0, array.length - 1);
        }else{
            helper(array, 0, array.length - 2);
        }
        return array;
    }

    public void helper(int[] ary, int l, int r){
        int length = r - l + 1;
        if(length <= 2){
            return;
        }
        int lm = l + length / 4;
        int m = l + length / 2;
        int rm = l + length * 3 / 4;

        //i love yahoo reverse[i,j] 闭区间
        reverse(ary, lm, m - 1);
        reverse(ary, m, rm - 1);
        reverse(ary, lm, rm - 1);

        //recursively resolve this question
        int l1 = lm - l;
        helper(ary, l, l + 2 * l1);
        helper(ary, l + 2 * l1 + 1, r);
    }

    private void reverse(int[] ary, int i, int j){
        while(i < j){
            swap(ary, i++, j--);
        }
    }

    private void swap(int[] ary, int i, int j){
        int tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }

}
