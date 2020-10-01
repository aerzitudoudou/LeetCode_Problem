package amazonOA;
//leetcode 340
//lintcode 386

//T: O(n)
//S: O(256) = O(1)



/*
*
* To solve the problem in one pass let's use here sliding window approach with two set pointers left and right serving as the window boundaries.

The idea is to set both pointers in the position 0 and then move right pointer to the right while the window contains not more than k distinct characters.
If at some point we've got k + 1 distinct characters, let's move left pointer to keep not more than k + 1 distinct characters in the window.
to move sliding window along the string, to keep not more than k distinct characters in the window, and to update max substring length at each step.
*
* */
public class LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];     // there are 256 ASCII characters in the world

        int i = 0;  // i will be behind j
        int num = 0;
        int res = 0;

        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) {    // if count[s.charAt(j)] == 0, we know that it is a distinct character
                num++;
            }
            while (num > k && i < s.length()) {     // sliding window
                count[s.charAt(i)]--;
                if (count[s.charAt(i)] == 0){
                    num--;
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;

    }
}
