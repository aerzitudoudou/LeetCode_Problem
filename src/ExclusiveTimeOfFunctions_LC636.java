import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ExclusiveTimeOfFunctions_LC636{
    //sol1, my , O(m), O(m) m is the size of logs
    public int[] exclusiveTime(int n, List<String> logs) {
        Deque<String[]> stack = new LinkedList<>();

        int[] res = new int[n];
        for(int i = 0; i < logs.size() - 1; i++){
            String pre = logs.get(i), post = logs.get(i + 1);

            String[] preAry = pre.split(":");
            String[] postAry = post.split(":");

            String preState = preAry[1], postState = postAry[1];
            int preId = Integer.parseInt(preAry[0]), postId = Integer.parseInt(postAry[0]), preTime = Integer.parseInt(preAry[2]), postTime = Integer.parseInt(postAry[2]);
            if(preState.equals("start") && postState.equals("start")){
                res[preId] += postTime - preTime;
                stack.offerFirst(preAry);
                stack.offerFirst(postAry);

            }else if(preState.equals("end") && postState.equals("end")){
                res[postId] += postTime - preTime;
                stack.pollFirst();
                stack.pollFirst();
            }else if(preState.equals("start") && postState.equals("end")){
                res[preId] += postTime - preTime + 1;
                stack.pollFirst();

            }else{ //end -> start
                if(!stack.isEmpty()){
                    String[] tmp = stack.peekFirst();
                    res[Integer.parseInt(tmp[0])] += postTime - preTime - 1;
                }
                stack.offerFirst(postAry);
            }
        }

        return res;

    }
}
