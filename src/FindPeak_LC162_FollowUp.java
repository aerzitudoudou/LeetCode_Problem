import java.util.ArrayList;
import java.util.List;

class FindPeak_LC162_FollowUp {
    //1 3 3 2 4 4 4 1 -> 3 or 4 both correct
    //1 3 3 2 5 1 4 4 3 -> 3 or 5 or 4 all correct
    //3 6 6 7 2 -> 7
    public int findPeakWithDuplication(int[] ary){
        int leftPeak = findLeftPeak1(ary), rightPeak = findRightPeak2(ary);
        return leftPeak > rightPeak ? leftPeak : rightPeak;
    }

    private int findLeftPeak(int[] ary){
        int l = 0, r = ary.length - 1;
        while(l + 1 < r){
            int mid = l + (r - l) / 2;
            if(ary[mid] >= ary[mid + 1]){
                r = mid;
            }else l = mid + 1;
        }
        return ary[l] > ary[r] ? ary[l] : ary[r];
    }

    //to left
    private int findLeftPeak1(int[] ary){
        int l = 0, r = ary.length - 1;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(ary[mid] > ary[mid + 1]){
                r = mid;
            }else l = mid + 1;
        }
        return ary[r];
    }

    private int findRightPeak(int[] ary){
        int l = 0, r = ary.length - 1;
        while(l + 1 < r){
            int mid = l + (r - l) / 2;
            if(ary[mid] >= ary[mid - 1]){
                l = mid;
            }else r = mid - 1;
        }
        return ary[l] > ary[r] ? ary[l] : ary[r];
    }

   //to right
    private int findRightPeak2(int[] ary){
        int l = 0, r = ary.length - 1;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(ary[mid] > ary[mid - 1]){
                l = mid;
            }else r = mid - 1;
        }
        return ary[r];
    }







}
