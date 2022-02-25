public class InterleavingPositiveAndNegativeNumbers_Lint144 {
    //sol1, my, O(n), O(1)
    public void rerange(int[] A) {
        int neg = 0, pos = 0, len = A.length;
        for(int i = 0; i < len; i++){
            if(A[i] < 0) neg++;
            else pos++;
        }
        //-1, -2, -3, 4, 5 => -1, -2, -3, 4, 5    case 1 , case 4
        //-1, -2, 3, 4 => -1, -2, 3, 4   case 1, case 3
        //-1, -2, 3, 4, 5 => 3, 4, 5 -1, -2   case 2, case 4
        if(neg >= pos) partitionNegativeFirst(A, 0, len - 1); //case 1
        else partitionPositiveFirst(A, 0, len - 1); // case 2

        if(neg == pos) interleave(A, 1, len - 2); // case 3
        else interleave(A, 1, len - 1); //case 4


    }

    //[0,l) <0
    //[l, r] tbd
    //(r, len - 1] >0
    private void partitionNegativeFirst(int[] A,  int l, int r){
        while(l <= r){
            while(l <= r && A[l] < 0) l++;
            while(l <= r && A[r] > 0) r--;
            if(l <= r){
                swap(A, l, r);
                l++;
                r--;
            }
        }
    }


    private void partitionPositiveFirst(int[] A, int l, int r){
        while(l <= r){
            while(l <= r && A[l] > 0) l++;
            while(l <= r && A[r] < 0) r--;
            if(l <= r){
                swap(A, l, r);
                l++;
                r--;
            }
        }
    }


    private void interleave(int[] A, int l, int r){

        while(l < r){
            swap(A, l, r);
            l+=2;
            r-=2;
        }
    }

    private void swap(int[] A, int l, int r){
        int tmp = A[l];
        A[l] = A[r];
        A[r] = tmp;
    }
}
