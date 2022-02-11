public class FirstBadVersion_LC278 extends VersionControl {
    /*  1 + (7 - 1)/2 = 1 + 3 = 4
    1 + (2 - 1) / 2 = 1 +0 = 1

    1 2 3 4 5 6 7
    T T T T F F F


*/


    //O(logn), O(1)

    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isBadVersion(mid)) {
                if (mid == 1 || !isBadVersion(mid - 1)) return mid;
                else {
                    r = mid - 1;
                }
            } else {
                l = mid + 1;
            }

        }
        return -1;
    }

    //O(logn), O(1)
    public int firstBadVersion1(int n) {
        int l = 1, r = n;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(isBadVersion(mid)){
                r = mid;
            }else l = mid + 1;
        }

        return r;


    }

}



