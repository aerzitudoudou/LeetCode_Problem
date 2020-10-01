package amazonOA;
//https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
//leetcode 1010

/*
* range of any integer % 60 would be [0, 59]. We initialize an array of length 60 to store the numbers of times each remainder occurs
* if remainder = 0, then the other number in the pair can only have remainder = 0 too.
* if counter[0] = n, find the combinations of 2 numbers from a set within n
* if counter[30] = n, find the combinations of 2 numbers from a set within n
* for other cases then it's 1 with 59, 2 with 58...use 2 pointers from each end to iterate towards the middle
* if 1 occurs m times, 59 n times, total number of combination is m * n
* T: O(n)  S:O(1)
* */
public class PairsOfSongsWithTotalDurationsDivisibleBy60 {
    public int numPairsDivisibleBy60(int[] time) {
        int res = 0;
        int[] counter = new int[60];
        for(int t : time) {
            counter[t % 60] += 1;
        }
        res += combination(counter[30], 2);
        res += combination(counter[0], 2);
        int i = 1, j = 59;
        while(i < j) {
            res += counter[i++] * counter[j--];
        }
        return res;
    }

    // find number of combinations with k number in
    public int combination(int n, int k) {
        long res = 1;
        for(int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
        }
        return (int)res;
    }
}
