import java.util.Deque;
import java.util.LinkedList;

public class DesignHitCounter_LC362 {
    //sol1, my, use queue to save the hits, when getHit then update queue to have only most recent 300s
    class HitCounter {
        Deque<Integer> queue;
        /** Initialize your data structure here. */
        public HitCounter() {
            queue = new LinkedList<>();
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            queue.offerLast(timestamp);

        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {

            while(!queue.isEmpty() && timestamp - queue.peekFirst() + 1 > 300){
                queue.pollFirst();
            }

            return queue.size();
        }
    }
}
