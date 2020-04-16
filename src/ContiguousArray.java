import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {


    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int gm = 0; //初始化是0: 不存在的时候，长度是0， 不能初始化成Integer.min
        //用Pre-sum 思想 count 记录到当前值的presum
        //presum在这里并不是说真正的"和"。而是，如果是1， 再原来presum基础上加一。如果是0， 再原来基础上减一。presum在这个程序中用count 表示。
        int count = 0;

        for(int i = 0; i < nums.length; i++){
            count = (nums[i] == 0) ? --count : ++count; //不能用count ++ 和count --, 要用count - 1 和count + 1, 或者 --count 和 ++ count
            /*
            * a = count++;  ====  count 先赋值，再加一
            * a = ++ count； ===== count 先加一，再赋值
            *  map 物理意义： 用来记录每一个presum第一次出现的index
            * */

            if(map.containsKey(count)){
                //如果presum (i.e.count) 值再次出现，即表示出现了0和1 相等的subarray. map的value值记录了这个presum值第一次出现的index，与当前index值去差值即可得这个符合条件的array的length.
                gm = Math.max(i - map.get(count), gm);
            }else{
                //presum值没有出现过，记录第一次出现的index
                map.put(count, i);
            }
        }
        return gm;

    }
}
