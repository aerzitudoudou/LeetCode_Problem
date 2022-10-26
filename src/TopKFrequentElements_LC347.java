import java.util.*;

public class TopKFrequentElements_LC347 {
    //!!!sol1, my, use minheap, O(nlogk), O(k)
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> map = new HashMap<>(); //<num, count>
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        // PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) ->{
        //     return e1.getValue() - e2.getValue();
        // });

        //heap store the number itself depending on its count in map
        //no need to store Map.Entry since map is visible throughout this method, just need to store the number itself
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((i1, i2) ->{
            return map.get(i1) - map.get(i2);
        });

        for(int key : map.keySet()){
            minHeap.offer(key);
            if(minHeap.size() > k) minHeap.poll();
        }

        for(int i = 0; i < k; i++){
            res[i] = minHeap.poll();
        }
        return res;



    }

    //!!!!sol2.1, my, quick select， use 2-d array to track the actual number
    //T: average: O(n) worst O(n^2)
    //S: O(n)
    public int[] topKFrequent2_1(int[] nums, int k) {
        int[] res = new int[k];
        //find the number where it falls to index = size - k
        //calculate the frequency of each number
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        //convert set to 2-d array
        int size = map.size();
        int[][] ary = new int[size][2];
        int i = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            ary[i] = new int[]{entry.getValue(), entry.getKey()}; //frequency, number
            i++;
        }

        //run quick select on ary[i] and find index = size - k number
        int index = partition(ary, 0, size - 1);
        while(index != size - k){
            if(index < size - k){
                index = partition(ary, index + 1, size - 1);
            }else{
                index = partition(ary, 0, index - 1);
            }
        }

        for(int j = 0; j < k; j++){
            res[j] = ary[index][1];
            index ++;
        }

        return res;


    }

    private int partition(int[][] ary, int l, int r){
        Random rand = new Random();
        int randIndex = l + rand.nextInt(r - l + 1);
        int pivot = ary[randIndex][0];
        swap(ary, randIndex, r);
        int i = l, j = r - 1;
        //[l, i) < pivot
        //[i, j] tbd
        //(j, r] >= pivot
        while(i <= j){
            if(ary[j][0] >= pivot) j--;
            else if(ary[i][0] < pivot) i++;
            else{
                swap(ary, i, j);
                i++;
                j--;
            }
        }

        swap(ary, i, r);
        return i;


    }

    private void swap(int[][] ary, int i, int j){
        int[] tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }



    //!!!!sol2.2, my, quick select, use class variable to avoid using 2-d
    int[] ary;
    Map<Integer, Integer> map;
    public int[] topKFrequent2_2(int[] nums, int k) {
        int[] res = new int[k];
        //find the number where it falls to index = size - k
        //calculate the frequency of each number
        map = new HashMap<>();
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        //convert set to 2-d array
        int size = map.size();
        ary = new int[size];
        int i = 0;
        for(int key : map.keySet()){
            ary[i] = key; //frequency, number
            i++;
        }

        //run quick select on ary[i] and find index = size - k number
        int index = partition(0, size - 1);
        while(index != size - k){
            if(index < size - k){
                index = partition(index + 1, size - 1);
            }else{
                index = partition(0, index - 1);
            }
        }


        return Arrays.copyOfRange(ary, index, size);


    }

    private int partition(int l, int r){
        Random rand = new Random();
        int randIndex = l + rand.nextInt(r - l + 1);
        int pivot = map.get(ary[randIndex]);
        swap(randIndex, r);
        int i = l, j = r - 1;
        //[l, i) < pivot
        //[i, j] tbd
        //(j, r] >= pivot
        while(i <= j){
            if(map.get(ary[j]) >= pivot) j--;
            else if(map.get(ary[i]) < pivot) i++;
            else{
                swap(i, j);
                i++;
                j--;
            }
        }

        swap(i, r);
        return i;


    }

    private void swap(int i, int j){
        int tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }


}
