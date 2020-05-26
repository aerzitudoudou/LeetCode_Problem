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
    //TODO: leetcode 一个case过不了，gcd是什么？
    public int most(Point[] points) {
        int res = 0;
        for(int i = 0; i < points.length; i++){
            Map<BigDecimal, Integer> slopeMap = new HashMap<>();
            int sameX = 0;
            int slopeMax = 0; //物理意义：对于一个特定的i, loop through 其他的点，其他的点和这个点组成的line的slope中点数最多的那个slope的点数
            int same = 0; //物理意义： 相同的点
            for(int j = 0; j < points.length; j++){
                //add 'self ' point at the very end, skip self in the loop through
                if(i == j){
                    continue;
                }
                //corner case: coordinate equal points
                if(points[j].x == points[i].x && points[j].y == points[i].y){
                    sameX++; //除了当前点，与当前点横坐标相同的点
                    same++; //除了当前点，与当前点坐标相同的点
                }else if(points[j].x == points[i].x){ //only x coordinate equals
                    sameX++;                       //0.999999989463830285529866159777157008647918701171875
                }else{//have slope, update cnt map //0.999999989463830285529866159777157008647918701171875
                    BigDecimal slope = new BigDecimal((points[j].y - points[i].y + 0.0) / (points[j].x - points[i].x));
                    Integer value = slopeMap.putIfAbsent(slope, 1);
                    if(value != null){
                        slopeMap.put(slope, slopeMap.get(slope) + 1);
                    }
                    slopeMax = Math.max(slopeMax, slopeMap.get(slope)); //只是算出了除了（1.当前点，2.和与当前点坐标相同的点）以外，不同的line上其他点的最大值
                }

            }
            res = Math.max(res, Math.max(slopeMax + same, sameX) + 1); //注意！！第一次做错了： 这里更新总res时，要把1. 当前点，2，和与当前点左边相同的点加上
        }

        return res;

    }
}
