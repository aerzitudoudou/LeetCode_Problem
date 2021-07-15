import java.util.ArrayList;
import java.util.List;

public class DivideTwoIntegers_LC29 {
    /*
     x/y = k     x - y - y - y- y..(-y for k times)

     x = y * 2^i i: [0, 31]

     有多少的y 能被整除，即有多少的ky 可以被x减掉
     1y
     2y
     4y
     8y     如果8y能被x减掉，则商里一定包括2^3: 1<<3
     ...
     1024y   如果1024y 能被x减掉，则商里一定包括2^10: 1<<10

     res: 1<<3 +1<<10 +...

     */
    //sol1: from acwing:https://www.acwing.com/video/1398/
    public int divide(int x, int y) {
        boolean isMinus = false;
        if(x > 0 && y < 0 || x < 0 && y > 0)  isMinus = true;
        long a = Math.abs((long)x), b = Math.abs((long)y);
        List<Long> list = new ArrayList<>();
        //O(32) Put 2y, 4y, 8y...into list
        for(long i = b; i <= a; i = i + i){
            list.add(i);
        }

        long res = 0l;
        //O(32) if can x - y, add 2^i into res
        for(int i = list.size() - 1; i >= 0; i--){
            if(a >= list.get(i)){
                a -= list.get(i);
                res += 1l << i; //!!! 1 << i will be converting to int(i = 31 res will be -2147483648).
            }
        }

        res = isMinus ? -res : res;
        if(res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return Integer.MAX_VALUE;

        return (int)res;

    }



}
