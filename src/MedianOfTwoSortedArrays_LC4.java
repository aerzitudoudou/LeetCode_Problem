public class MedianOfTwoSortedArrays_LC4 {
    //sol1, from acwing, https://www.acwing.com/video/1316/,47:00 divide and conquer, T: O(log(m + n)) everytime half of elements being valued is discarded，therefore k -> k/2 -> k/4... where k = (m+ n)/ 2
    // S: O(log(m+n)): no new object created, stack depth = O(log(m + n))
    public double findMedianSortedArrays(int[] ary1, int[] ary2) {
        int m = ary1.length, n = ary2.length;
        int total = m + n;
        if(total % 2 == 0){
            return (findKth(ary1, 0, ary2, 0, total / 2) + findKth(ary1, 0, ary2, 0, total / 2 + 1)) / 2.0;
        }else{
            return findKth(ary1, 0, ary2, 0, total / 2 + 1);
        }
    }

    private int findKth(int[] ary1, int i, int[] ary2, int j, int k){
        int m = ary1.length, n = ary2.length;
        if(m - i > n - j) return findKth(ary2, j, ary1, i, k);
        if(i >= m) {
            return ary2[j + k - 1];
        }
        if(k == 1){
            return Math.min(ary1[i], ary2[j]);
        }
        //recursion rule
        //part1 表示ary1能删的数， 删k/2个数，或者m - i 个数（如果ary1s ize不够删k/2个数）
        //因为一共要考虑k个数， ary2 要删的个数就是k - part1
        int part1 = Math.min(k / 2, m - i), part2 = k - part1;
        if(ary1[i + part1 - 1] < ary2[j + part2 - 1]){
            //i + part1 表示删完了part1个数， 下一轮的recursion起始index
            return findKth(ary1, i + part1, ary2, j, part2);
        }else{
            return findKth(ary1, i, ary2, j + part2, part1);
        }

    }
}
