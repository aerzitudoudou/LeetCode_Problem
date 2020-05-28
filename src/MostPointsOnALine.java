import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/*
*
* Laicode
* 216. Most Points On A Line
Medium
Given an array of 2D coordinates of points (all the coordinates are integers), find the largest number of points that can be crossed by a single line in 2D space.

Assumptions

The given array is not null and it has at least 2 points
Examples

<0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum number of points on a line is 3(<0, 0>, <1, 1>, <3, 3> are on the same line)
*
*/
public class MostPointsOnALine {
    /*
     k: slope
     b:intercept
 y = kx + b

 for {
   point seed

   map<slope, count of points having same slope other than seed itself>

   for{

     case 1: k exists: update map  remain maximum value as mapMax
     case 2: k doesn't exit: update sameX variable

     corner case:
     1. different points with same coordinates: update mapMax value and same
     2. only X coordinates matches: update only same X

   }
 }
 add 1 to the final res for adding the self point
 */
    //way1: 不考虑精度，可以过lint和laicode
    public int most(Point[] points) {
        int res = 0;
        for(int i = 0; i < points.length; i++){
            Map<Double, Integer> slopeMap = new HashMap<>();
            int sameX = 0;
            int slopeMax = 0; //物理意义：对于一个特定的i, loop through 其他的点，其他的点和这个点组成的line的slope中点数最多的那个slope的点数
            int same = 0; //物理意义： 相同的点，或当前点
            for(int j = 0; j < points.length; j++){
                //corner case: coordinate equal points
                if(points[j].x == points[i].x && points[j].y == points[i].y){
                    same++; //当前点，或与当前点坐标相同的点
                }else if(points[j].x == points[i].x){ //only x coordinate equals    sameX意思是除了： （1. 当前点 2.与当前点坐标相同的点） 以外， 只与当前点横坐标相同的点
                    sameX++;
                }else{//have slope, update cnt map
                    Double slope = (points[j].y - points[i].y + 0.0) / (points[j].x - points[i].x);
                    Integer value = slopeMap.putIfAbsent(slope, 1);
                    if(value != null){
                        slopeMap.put(slope, slopeMap.get(slope) + 1);
                    }
                    slopeMax = Math.max(slopeMax, slopeMap.get(slope)); //只是算出了除了（1.当前点，2.和与当前点坐标相同的点）以外，不同的line上其他点的最大值
                }

            }
            res = Math.max(res, Math.max(slopeMax, sameX) + same); //注意！！第一次做错了： 这里更新总res时，要把1. 当前点，2，和与当前点坐标相同的点加上
        }
        return res;
    }
    //way2 leetcode 有一个大数据例子过不了。原因是计算slope的时候double度不够，导致求出来的double 值相同
    /*
    * 技巧： 把y1 - y2 和 x1 - x2分别除以最大公约数 存结果值 y3 和 x3  ==> 如果两组点y3 = x3 则这两组点一定共线
    * gcd(greatest common divisor): 最大公约数 辗转相除法： gcd(a, b) = gcd(b, a % b) 知道b为0 的时候， a即为两个数的gcd
    *
    *
    * */

    public int most2(Point[] points) {
        int res = 0;
        for(int i = 0; i < points.length; i++){
            Map<String, Integer> slopeMap = new HashMap<>();
            int sameX = 0;
            int slopeMax = 0; //物理意义：对于一个特定的i, loop through 其他的点，其他的点和这个点组成的line的slope中点数最多的那个slope的点数
            int same = 0; //物理意义： 相同的点，或当前点
            for(int j = 0; j < points.length; j++){
                //corner case: coordinate equal points
                if(points[j].x == points[i].x && points[j].y == points[i].y){
                    same++; //当前点，或与当前点坐标相同的点
                }else if(points[j].x == points[i].x){ //only x coordinate equals    sameX意思是除了： （1. 当前点 2.与当前点坐标相同的点） 以外， 只与当前点横坐标相同的点
                    sameX++;
                }else{//have slope, update cnt map
                    int gcd = gcd(points[j].y - points[i].y, points[j].x - points[i].x);
                    int x = (points[j].x - points[i].x) / gcd;
                    int y = (points[j].y - points[i].y) / gcd;
                    String key = x + "#" + y;
                    Integer value = slopeMap.putIfAbsent(key, 1);
                    if(value != null){
                        slopeMap.put(key, slopeMap.get(key) + 1);
                    }
                    slopeMax = Math.max(slopeMax, slopeMap.get(key)); //只是算出了除了（1.当前点，2.和与当前点坐标相同的点）以外，不同的line上其他点的最大值
                }

            }
            res = Math.max(res, Math.max(slopeMax, sameX) + same); //注意！！第一次做错了： 这里更新总res时，要把1. 当前点，2，和与当前点坐标相同的点加上
        }
        return res;
    }

    private int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        return gcd(b, a % b);
    }


}
