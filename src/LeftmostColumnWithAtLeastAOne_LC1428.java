public class LeftmostColumnWithAtLeastAOne_LC1428 {
        /*
    0000111
    0011111
    0000001
    0000000

    binary search based on column: first countcolone >= 1 column
    */

    //!!!!sol2: O(m + n), O(1) : https://leetcode.com/problems/leftmost-column-with-at-least-a-one/discuss/590828/Java-Binary-Search-and-Linear-Solutions-with-Picture-Explain-Clean-Code
    /*
    from upper-right to bottom left
    see 0 move down
    see 1 move left
    when seet 1 update ans
    */
    public int leftMostColumnWithOne1(BinaryMatrix binaryMatrix) {
        int m = binaryMatrix.dimensions().get(0), n = binaryMatrix.dimensions().get(1);
        int i = 0, j = n - 1;
        int ans = -1;
        while(i < m && j >= 0){
            if(binaryMatrix.get(i, j) == 1){
                ans = j;
                j--;
            }else{
                i++;
            }
        }

        return ans;

    }


    //sol 1: m is the row number, n is the column number, my way O(mlogn), O(1)
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rLen = binaryMatrix.dimensions().get(0), cLen = binaryMatrix.dimensions().get(1);
        int l = 0, r = cLen - 1;
        while(l + 1 < r){
            int mid = l + (r - l) / 2;
            if(exsitOne(binaryMatrix, mid, rLen)){
                r = mid;
            }else{
                l = mid + 1;
            }
        }

        if(!exsitOne(binaryMatrix, r, rLen)) return -1;
        if(exsitOne(binaryMatrix, l, rLen)) return l;
        return r;

    }

    private boolean exsitOne(BinaryMatrix binaryMatrix, int colIndex, int rLen){
        for(int i = 0; i < rLen; i++){
            if(binaryMatrix.get(i, colIndex) == 1) return true;
        }

        return false;
    }


}
