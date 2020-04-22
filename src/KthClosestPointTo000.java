import java.util.*;
/**laicode
 *194. Kth Closest Point To <0,0,0>
 * Medium
 * Given three arrays sorted in ascending order. Pull one number from each array to form a coordinate <x,y,z> in a 3D space. Find the coordinates of the points that is k-th closest to <0,0,0>.
 *
 * We are using euclidean distance here.
 *
 * Assumptions
 *
 * The three given arrays are not null or empty, containing only non-negative numbers
 * K >= 1 and K <= a.length * b.length * c.length
 * Return
 *
 * a size 3 integer list, the first element should be from the first array, the second element should be from the second array and the third should be from the third array
 * Examples
 *
 * A = {1, 3, 5}, B = {2, 4}, C = {3, 6}
 *
 * The closest is <1, 2, 3>, distance is sqrt(1 + 4 + 9)
 *
 * The 2nd closest is <3, 2, 3>, distance is sqrt(9 + 4 + 9)
 *
 *
 *这里注意题意： 去重的不是“距离”， 而是(1,2,3)最终值，或者最终值在对应input数组里的index组合。这里allow index combination不一样，但是距离原点一样的情况
 *
 */

public class KthClosestPointTo000 {
    // T: klog(k)
    //S: O(k)
    public List<Integer> closest(int[] a, int[] b, int[] c, int k) {
        List<Integer> res = new ArrayList();
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] m, int[] n){
                if(calculateDistance(a[m[0]], b[m[1]], c[m[2]]) == calculateDistance(a[n[0]], b[n[1]], c[n[2]])){
                    return 0;
                }
                return calculateDistance(a[m[0]], b[m[1]], c[m[2]]) < calculateDistance(a[n[0]], b[n[1]], c[n[2]]) ? -1 : 1;
            }
        });
        Set<List<Integer>> set = new HashSet<>();
        pq.offer(new int[]{0, 0, 0});//store index
        //int array to list, value直接list 在（）里
        //list to array 可以用Collections.toList()
        set.add(Arrays.asList(0, 0, 0));
        return bfs(pq, set, a, b, c, k);

    }


    private List<Integer> bfs(PriorityQueue<int[]> pq, Set<List<Integer>> set, int[] a, int[] b, int[] c, int k){
        for(int i = 0; i < k - 1; i++){
            //expand
            int[] tmp = pq.poll();
            //original index
            int w = tmp[0];//a's
            int y = tmp[1]; //b's
            int z = tmp[2]; //c's
            //generate
            if(w < a.length - 1 && !set.contains(Arrays.asList(w + 1, y, z))){
                pq.offer(new int[]{w + 1, y, z});
                set.add(Arrays.asList(w + 1, y, z));
            }
            if(y < b.length - 1 && !set.contains(Arrays.asList(w, y + 1, z))){
                pq.offer(new int[]{w, y + 1, z});
                set.add(Arrays.asList(w, y + 1, z));
            }
            if(z < c.length - 1 && !set.contains(Arrays.asList(w, y, z + 1))){
                pq.offer(new int[]{w, y, z + 1});
                set.add(Arrays.asList(w, y, z + 1));
            }
        }
        int[] res = pq.peek();
        return Arrays.asList(new Integer[]{Integer.valueOf(a[res[0]]), Integer.valueOf(b[res[1]]), Integer.valueOf(c[res[2]])});
    }

    private int calculateDistance(int a, int b, int c){
        return a * a + b * b + c * c;
    }
}
