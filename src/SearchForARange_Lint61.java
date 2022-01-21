public class SearchForARange_Lint61 {
    //sol1, my, O(logn), O(1)
    public int[] searchRange(int[] A, int target) {

        int left = -1, right = -1;
        if(A == null || A.length == 0) return new int[]{-1, -1};
        int l = 0, r = A.length - 1;
        //find starting
        while(l < r){
            int mid = l + (r - l) / 2;
            if(A[mid] >= target){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        left = A[r] == target ? r : -1;
        if(left == -1) return new int[]{-1, -1};

        //find end
        l = 0;
        r = A.length - 1;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(A[mid] <= target){
                l = mid;
            }else{
                r = mid - 1;
            }
        }
        right = r;
        return new int[]{left, right};

    }
}
