public class TotalOccurrenceOfTarget_Lint462 {
    //sol1, my, O(logn), O(1), same question as Lint 61
    public int totalOccurrence(int[] A, int target) {
        int left = -1, right = -1;
        if(A == null || A.length == 0) return 0;
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
        if(left == -1) return 0;

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
        return right - left + 1;
    }
}
