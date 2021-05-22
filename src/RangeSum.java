//phone interview of Nextdoor
// Given a list of numbers and a range, find the sum of all the numbers in the list that are in this range.
// list = [3, 1, 5, 19, 20, 16]
// range [3, 5] should return 8 : (3 + 5)
// range [2, 5] should return 8 : (3 + 5)
// range [1, 5] should return 9 : (3 + 1 + 5)
// range [7, 19] should return 35 : (19 + 16)

/*
ary     1   3 5   16 19 20
preSum  1   4 9   25 44 64
          [2,  15]
          >=2  <=15
range [3, 5] should return 8 : (3 + 5)

*/
// Main class should be named 'Solution'
//1. sort(which is not required here assume it is sorted) 2. use binary search to find the indexes next to the range 3. use presum array to calucate the sum in O(1)
public class RangeSum {
    public static void main(String[] args) {
        int[] test = {1, 3, 5, 16, 19, 20};
        int[] preSum = {1, 4, 9, 25, 44, 64};
        int res1 = findIndexLarge(3, test);
        int res2 = findIndexSmall(5, test);
        int res = rangeSum(3, 5, test, preSum);
        System.out.println("first index: " + res1 + ", second index: " + res2 + " sum:" + res);
    }

    private static int rangeSum(int min, int max, int[] ary, int[] preSum) {
        if (ary == null || ary.length == 0 || min > ary[ary.length - 1] || max < ary[0]) return -1;
        int indexMin = findIndexLarge(min, ary), indexMax = findIndexSmall(max, ary);
        return indexMin == 0 ? preSum[indexMax] : preSum[indexMax] - preSum[indexMin - 1];

    }

    private static int findIndexLarge(int a, int[] ary) {
        int left = 0, right = ary.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (ary[mid] == a) {
                return mid;
            } else if (ary[mid] > a) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return ary[left] < a ? right : left;
    }

    private static int findIndexSmall(int a, int[] ary) {
        int left = 0, right = ary.length - 1; // left: 0, right: 4
        while (left + 1 < right) {
            int mid = left + (right - left) / 2; // mid: 2
            if (ary[mid] == a) {
                return mid;
            } else if (ary[mid] > a) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return ary[right] > a ? left : right;
    }


}
