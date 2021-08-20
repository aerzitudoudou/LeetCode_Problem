import java.util.HashMap;
import java.util.Map;

public class LoggerRateLimiter_LC359 {

    class Logger {
        //sol1, my, O(1), O(n)
        Map<String, Integer> map;
        /** Initialize your data structure here. */
        public Logger() {
            map = new HashMap<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            // if(message == null) return null;
            if(!map.containsKey(message)){
                map.put(message, timestamp);
                return true;
            }else{
                int prev = map.get(message);
                if(timestamp >= prev + 10){
                    map.put(message, timestamp);
                    return true;
                }else return false;
            }
        }


        //todo: https://leetcode.com/problems/logger-rate-limiter/discuss/391558/Review-of-four-different-solutions%3A-HashMap-Two-Sets-Queue-with-Set-Radix-buckets-(Java-centric) for other methods

    }

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
}
