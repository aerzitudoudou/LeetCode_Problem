import java.util.*;

public class MaximumValuesOfSizeKSlidingWindows {
    //way 1, use heap, lazy deletion
    //TODO: dijkstra's algrithm 用到lazy deletion 复习一下
    //T: nlogn 注意是logn, 这里的heap的大小是n, 不是k
    //S: O(n)
    public List<Integer> maxWindows(int[] array, int k) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(array.length, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return 0;
                }
                return a[0] > b[0] ? -1 : 1;
            }
        });
        int count = 0;
        for(int i = 0; i < array.length; i++){
            if(i < k - 1){
                maxHeap.offer(new int[]{array[i], i});
            }else{
                maxHeap.offer(new int[]{array[i], i});
                int[] max = maxHeap.peek();
                int maxIndex = max[1];
                //lazy deletion, 最大数不在当前的sliding window 范围内的时候，再delete，实在不行了，影响结果的时候，再delete ,一开始先留着如果对结果没影响--->LAZY!
                while(!(maxIndex >= count && maxIndex <= count + k - 1)){
                    maxHeap.poll();
                    maxIndex = maxHeap.peek()[1];
                }
                res.add(array[maxIndex]);
                count++;
            }

        }
        return res;
    }

    //way 2 Deque 单调栈
    /*
    * 维护一个单调递减deque
    *
    * 对array里的每个值，加入current <value, index>：    head <------------->tail
    * step1: 查head元素的index是否<= i - k 是则表明head值不属于当前sliding window range, popHead()
    * step2: 如果当前的值又大又新，从deque尾部把小于等于它的元素消消乐搞掉，直到current值进入deque满足单调栈的value单调递减性
    *        while tail value <= current value
    *                   popTail()
    * step3：此时加入current 一定满足单调栈，即，栈head一定是当前sliding window最大值
    * */
    //T: O(n)
    //S: O(n)
    public List<Integer> maxWindows2(int[] array, int k) {
        List<Integer> res = new ArrayList<>();
        Deque<int[]> deque = new LinkedList<>();
        for(int i = 0; i < array.length; i++){
//            if(!deque.isEmpty() && !(deque.peekFirst()[1] >= i - (k - 1) && deque.peekFirst()[1] <= i)){
            if(!deque.isEmpty() && deque.peekFirst()[1] <= i - k){
            deque.pollFirst();
            }

            while(!deque.isEmpty() && deque.peekLast()[0] <= array[i]){
                deque.pollLast();
            }
            deque.offerLast(new int[]{array[i], i});
            if(i >= k - 1){
                res.add(deque.peekFirst()[0]);
            }
        }
        return res;
    }

}
