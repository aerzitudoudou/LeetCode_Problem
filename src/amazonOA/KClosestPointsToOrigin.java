package amazonOA;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

//https://leetcode.com/problems/k-closest-points-to-origin/submissions/
/*解答抄的这里： https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.
*
* 第一题：k nearest points to origin

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

public class KClosestPointsToOrigin {
    //sol1 TODO：考虑代码优化

    //quick select O(n), O(1)
    public int[][] kClosest2(int[][] points, int k) {
        /*
         if 3 is pivot then left half = [0,mid] which is all the number smaller or equal to the pivot
         right half = [mid + 1, length - 1] which is all the number larger than pivot
         until i = k then numbers from [0, k - 1] is the result
         mid < k check [mid + 1, length - 1]
         mid > k check [0, mid - 1]
                i
        1 2 3 3 3 4 7 6

        */
        helper(points, k);
        return Arrays.copyOfRange(points, 0, k);


    }
    //平均每次指针的搜索域减少一半 T: O(n) + O(n / 2) + O(n / 4)... = O(n) worst case O(n ^ 2) if everytime pivot is the chosen to be smallest and put at the right side
    private void helper(int[][] points, int k){
        int l = 0, r = points.length - 1;
        while(l <= r){
            int mid = partition(points, l, r);
            if(mid == k) return;
            else if(mid < k){
                l = mid + 1;
            }else{
                r = mid - 1;
            }

        }

    }

    private int partition(int[][] points, int l, int r){
        Random rand = new Random();
        int pivot = l + rand.nextInt(r - l + 1);
        swap(points, pivot, r);
        int i = l, j = r - 1;
        while(i <= j){
            if(compare(points[i], points[r]) <= 0) {
                i++;
            }else if(compare(points[j], points[r]) > 0){
                j--;
            }else{
                swap(points, i, j);
                i++;
                j--;
            }
        }
        swap(points, i, r);
        return i;
    }


    private void swap(int[][] points, int i, int j){
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }




    //////////////////////////sol2 copied
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



    //2021/05/30 sol3: maxheap
     /*
        x^2 + y^2 smallest
        max heap
    */
    public int[][] kClosest1(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] p1, int[] p2) -> {
            int x1 = p1[0], y1 = p1[1], x2 = p2[0], y2 = p2[1];
            int sum1 = x1 * x1 + y1 * y1, sum2 = x2 * x2 + y2 * y2;
            if(sum1 == sum2) return 0;
            return sum1 > sum2 ? -1 : 1;
        });

        for(int[] point : points){
            pq.offer(point);
            if(pq.size() > k){
                pq.poll();
            }
        }



        int[][] res = new int[k][2];
        int i = 0;
        while(!pq.isEmpty()){
            res[i++] = pq.poll();
        }

        return res;

    }
}
