package amazonOA;
//https://leetcode.com/discuss/interview-question/862600/amazon-oa-2020-fraudulent-activity
import java.util.*;

//T: O(n) n is the size of the input log string array
//S: O(n)
public class TransactionLog {
    public List<String> getFraudIds(String[] input, int threshold) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new TreeSet<>();
        for(String log : input) {
            String[] parsed = log.split(" ");
            String usr1 = parsed[0];
            String usr2 = parsed[1];
            if(!usr2.equals(usr1)) {
                map.put(usr2, map.getOrDefault(usr2, 0)+1);
                if(map.get(usr2) >= threshold) set.add(usr2);
            }
            map.put(usr1, map.getOrDefault(usr1, 0)+1);
            if(map.get(usr1) >= threshold ) set.add(usr1);
        }


        List<String> result =  new ArrayList<>(set);
        return result;
    }
}
