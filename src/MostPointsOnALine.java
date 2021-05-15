import java.util.*;

/*
*
* Laicode
* 216. Most Points On A LineN
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

                    /*
                    * x1 = 2   x2 = 3  x1 - x2 = -1    -1 and -1's gcd = -1    x1 / gcd = 1
                    * y1 = 4   y2 = 5  y1 - y2 = -1                            x2 / gcd = 1
                    * x1 = 2   x2 = 3  x2 - x1 = 1      1 and  1's gcd =  1    x1 / gcd = 1
                    * y1 = 4   y2 = 5  y2 - y1 = 1                             x2 / gcd = 1
                    *
                    *
                    * */
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


    //sol3: my solution at Gusto interview 2021/04/23

    public static int calculatePoints(List<Integer[]> list){
        Map<String, Set<Integer[]>> map = new HashMap<>();
//0,1,2,3
        for(int i= 0; i < list.size(); i++){
            for(int j = i + 1; j < list.size(); j++){
                Integer[] point1 = list.get(i);
                Integer[] point2 = list.get(j);
//x1 == x2
                String key;
                if(point1[0] == point2[0]){
                    //encode 要用一个string. 一开始用Double[] 如果ary1=[key, value] ary2=[key, value]这两个object在放进map as a key hashcode不一样不算是同一个object
                    key = "null" + "#" + new Double(point1[0]);

                }else{
                    Double slope = new Double((point1[1] - point2[1]))/(point1[0] - point2[0]);
//b = k * x2 - y2
                    Double b = slope * point1[0] - point1[1];
                    key = slope + "#" + b;
                }
//                Set<Integer[]> set = map.putIfAbsent(key, new HashSet<Integer[]>(){{
//                    add(point1);
//                    add(point2);
//                }});
                Set<Integer[]> set = map.putIfAbsent(key, new HashSet<>(Arrays.asList(point1, point2)));
                if(set != null){
                    set.add(point1);
                    set.add(point2);
                }

            }
        }
        int max = 0;
        for(Map.Entry<String, Set<Integer[]>> entry : map.entrySet()){
            Set<Integer[]> set = entry.getValue();
            max = Math.max(max, set.size());
//            System.out.println("slope:" + entry.getKey()[0] + ", b:" + entry.getKey()[1] );
//            for(Integer[] ary : set){
//                System.out.println("x:" + ary[0] + ", y:" + ary[1] );
//            }
        }
        return max;
    }


    //2021/04/23 重写gcd
    public int maxPoints2(int[][] points) {

        int max = 0;
        for(int i = 0; i < points.length; i++){ //物理意义: 以point0为起点的所有能形成的线上的点的最大值
            Map<String, Integer> map = new HashMap<>();
            String key;
            for(int j = i + 1; j < points.length; j++){ //以0点为起点的所有的线已经算过了，不需要再算了
                int x1 = points[i][0];
                int x2 = points[j][0];
                int y1 = points[i][1];
                int y2 = points[j][1];
                if(x1 == x2){
                    key = 0 + "#";
                }else if(y1 == y2){
                    key = "#" + 0;
                }else{
                    int gcd = gcd2(x1 - x2, y1 - y2);
                    int gcdX = (x1 - x2) / gcd;
                    int gcdY = (y1 - y2) / gcd;
                    key = gcdX + "#" + gcdY;
                }
                Integer count = map.putIfAbsent(key, 1);
                if(count != null){
                    map.put(key, map.get(key) + 1);
                }
                max = Math.max(max, map.get(key));
            }
        }
        return max + 1;

    }

    private int gcd2(int a, int b){
        int c;
        while(b != 0){
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

}
