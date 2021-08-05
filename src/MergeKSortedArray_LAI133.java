import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedArray_LAI133 {
    //sol1, my, heap, O(kn logk), O(k)
    //K: number of arrays, n: average length of each array
    public int[] merge(int[][] arrays) {
        //Sol1, my, O(kn*logk), O(k)
        if(arrays == null || arrays.length == 0) return null;
        //Integer[] : <i, j, value>
        int k = arrays.length;
//        PriorityQueue<Integer[]> pq = new PriorityQueue<>(k, new Comparator<Integer[]>(){
//            @Override
//            public int compare(Integer[] a, Integer[] b){
//                return a[2].compareTo(b[2]);
//            }
//        });
        //
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(k, (ary1, ary2) -> {
            return ary1[2].compareTo(ary2[2]);
        });

        int size = 0;
        for(int i = 0; i < k; i++){
            if(arrays[i] != null && arrays[i].length != 0){
                pq.offer(new Integer[]{i, 0, arrays[i][0]});
                size+=arrays[i].length;
            }


        }
        int[] res = new int[size];
        int count = 0;
        while(!pq.isEmpty()){
            Integer[] tmp = pq.poll();

            int i = tmp[0], j = tmp[1];
            res[count++] = arrays[i][j];
            if(j < arrays[i].length - 1){
                pq.offer(new Integer[]{i, j + 1, arrays[i][j + 1]});
            }
        }


        return res;
    }
}
