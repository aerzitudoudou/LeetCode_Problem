import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumCost {
//    public int MinimumCost(List<Integer> sticks) {
//        //错误解！！！！： 两个数字的和放回原来在presum里不一定是最小值， 需要持续的拿出最小值
//        if(sticks == null || sticks.size() == 0){
//            return 0;
//        }
//        int sum = 0;
//        Collections.sort(sticks);
//        int[] preSum = new int[sticks.size()];
//        for(int i = 0; i < preSum.length; i++){
//            if(i == 0){
//                preSum[i] = sticks.get(i);
//            }else{
//                preSum[i] = sticks.get(i) + preSum[i - 1];
//            }
//        }
//
//        for(int i = 1; i < preSum.length; i++){
//            sum += preSum[i];
//        }
//        return sum;
//    }


    /*
    * pq 的api:
    * poll , offer, and peek
    *
    *
    * */
    public int MinimumCost(List<Integer> sticks) {
        if(sticks == null || sticks.size() == 0){
            return 0;
        }
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Integer stick : sticks){
            pq.offer(stick);
        }

        while(pq.size() > 1){
            int x = pq.poll();
            int y = pq.poll();
            sum += x + y;
            pq.offer(x + y);
        }
        return sum;
    }
}
