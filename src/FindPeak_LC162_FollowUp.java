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



    //index < 0 and index >= length - 1的值都是负无穷，求峰值思路（略）：
    //                 1 3 3 2 5 1 4 4 3
    //left to right:   1 1 1 0 1 0 1 1 0
    //right to left:   0 1 1 0 1 0 1 1 1


    //index < 0 and index >= length - 1的值都是正无穷，求峰值：
    //           3 3 3 5 2 1
    //           0 0 0 1 0 0
    //           0 0 0 1 1 0
    // index < 0 || index >= length : + infinite
    //sol1, my, O(n), O(n)
    public List<Integer> findAllPeaksWithDuplicate(int[] ary){
        List<Integer> res = new ArrayList<>();
        boolean isStart = true, isEnd = true;
        int[] leftToRight = new int[ary.length];
        int[] rightToLeft = new int[ary.length];

        //left to right
        for(int i = 0; i < ary.length; i++){
            if(i == 0) continue;
            else if(isStart && ary[i] == ary[i - 1]) continue;
            else{
                isStart = false;
                if (ary[i] > ary[i - 1]) {
                    leftToRight[i] = 1;
                }else if(ary[i] == ary[i - 1]){
                    leftToRight[i] = leftToRight[i - 1];
                }else{
                    leftToRight[i] = 0;
                }
            }

        }
        for(int i = ary.length - 1; i >= 0; i--){
            if(i == ary.length - 1) continue;
            else if(isEnd && ary[i] == ary[i + 1]) continue;
            else{
                isEnd = false;
                if (ary[i] > ary[i + 1]) {
                    rightToLeft[i] = 1;
                    if(leftToRight[i] == 1){
                        res.add(ary[i]);
                    }
                }else if(ary[i] == ary[i + 1]){
                    rightToLeft[i] = rightToLeft[i + 1];
                    if(rightToLeft[i] == 1 && leftToRight[i] == 1){
                        res.add(ary[i]);
                    }
                }else{
                    rightToLeft[i] = 0;
                }
            }

        }

        return res;

    }

    //sol2, from jh, O(n), O(n)
    public List<Integer> findAllPeaksWithDuplicate2(int[] ary){
        List<Integer> res = new ArrayList<>();

        boolean[] leftToRight = new boolean[ary.length];
        boolean[] rightToLeft = new boolean[ary.length];

        //left to right
        for(int i = 1; i < ary.length; i++){
            if(ary[i] > ary[i - 1] || ary[i] == ary[i - 1] && leftToRight[i - 1]){
                leftToRight[i] = true;
            }

        }
        for(int i = ary.length - 2; i >= 0; i--){
            if(ary[i] > ary[i + 1] || ary[i] == ary[i + 1] && rightToLeft[i + 1]){
                rightToLeft[i] = true;
                if(leftToRight[i]) res.add(ary[i]);
            }

        }

        return res;







    }







}
