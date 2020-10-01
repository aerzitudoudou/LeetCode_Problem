package amazonOA;

import java.util.Arrays;

//https://leetcode.com/problems/k-closest-points-to-origin/submissions/
/*解答抄的这里： https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.
*
* 第一题：k nearest points to origin

但第一题如果按照力扣上的解法 会有3个case不过
然后想了半天 把max heap改成min heap
记录所有的points 然后再poll出前K个 就过所有的case了...
* */
/* Quick select.

each iteration, we choose a pivot and find the position p where the pivot belongs to.
Then we compare p with the K:
if the p is smaller than the K, all element on the left of the pivot are all proper candidates.
if the p is larger than the K, then all element on the right of the pivot are all proper candidates.
If the p is exactly equal to the K, meaning that we've found the K-th position.
In this case we'll return the first K elements, because they are not greater than the pivot.
 */

//Theoretically, the average time complexity is O(N) , but just like quick sort, in the worst case, this solution would be degenerated to O(N^2), and pratically, the real time it takes on leetcode is 15ms.
public class KclosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
