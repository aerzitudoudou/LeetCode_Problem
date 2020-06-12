/*
*Laicode
*217. Largest Set Of Points With Positive Slope
Medium
Given an array of 2D coordinates of points (all the coordinates are integers), find the largest number of points that can form a set such that any pair of points in the set can form a line with positive slope. Return the size of such a maximal set.

Assumptions

The given array is not null
Note: if there does not even exist 2 points can form a line with positive slope, should return 0.
Examples

<0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum set of points are {<0, 0>, <1, 1>, <2, 3>}, the size is 3.
*
*
*
*
* */

import java.util.Arrays;
import java.util.Comparator;

public class LargestSetOfPointsWithPositiveSlope {

    /*
 题目可以翻译成找到given y1 < y2  必须满足 x1 < x2 ==> given y1 < y2 < .....< yn 必须满足 x1 < x2 < x3 < ....xn 的n的最大值是多少
 把points 按照y轴（或x轴）单调递增排列，求x轴（y轴）对应数字形成的list里面longest ascending subsequence 里的元素个数
 m[i] represents the length of the longest increasing subsequence between [0, i], ending with a[i]
 base case: m[0] = 1
 induction rule: m[i] = Max(m[j]) + 1 where 0 <= j < i and a[j] < a[i]
 res: globalMax of m[i] for 0 <= i <= a.length - 1
 */
    public int largest(Point[] points) {
        if(points == null || points.length == 0){
            return 0;
        }
        //sort the array by x axis
        //注意sort 带comparator的写法
        Arrays.sort(points, new Comparator<Point>(){
            @Override
            public int compare(Point p1, Point p2){
                if(p1.x == p2.x){
                    return 0;
                }
                return p1.x < p2.x ? - 1: 1;
            }
        });
        int res = 1;
        int[] m = new int[points.length];
        m[0] = 1;

        for(int i = 1; i < points.length; i++){
            m[i] = 1;
            for(int j = i - 1; j >= 0; j--){
                //这个地方第一次做错了，和longest ascending subsequence 不同的是这里要check both x and y make sure 2 points join as positive slope
                //如果x1 = x2 ==>point1, point2 不能看作是valid result
                /*
                *
                * x:    1   1   2
                * y:    3   4   5
                * m:    1   1   2
                *          (这个1不能是2， 因为(1,3), (1, 4) 不是positive slope)
                *
                * */
                if(points[j].x < points[i].x && points[j].y < points[i].y){
                    m[i] = Math.max(m[j] + 1, m[i]);
                }
            }

            res = Math.max(res, m[i]);
        }

        //post processing : if res = 1, then set res = 0 (注意题目 if there does not even exist 2 points can form a line with positive slope, should return 0.)
        res = res == 1 ? 0 : res;
        return res;


    }
}
