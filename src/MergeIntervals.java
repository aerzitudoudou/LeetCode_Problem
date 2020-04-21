import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/*
* Description

Given two sorted integer arrays A and B, merge B into A as one sorted array.

You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.

Have you met this question in a real interview?
Example
Example 1:

Input：[1, 2, 3] 3  [4,5]  2
Output：[1,2,3,4,5]
Explanation:
After merge, A will be filled as [1, 2, 3, 4, 5]
Example 2:

Input：[1,2,5] 3 [3,4] 2
Output：[1,2,3,4,5]
Explanation:
After merge, A will be filled as [1, 2, 3, 4, 5]
*
*
*
* */

class Interval { //辅助class 不能是public 否则compile error 一个file里只能有一个public class and the class would be named after it
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
public class MergeIntervals {


    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals == null){
            return null;
        }
        if(intervals.size() <= 1){
            return intervals;
        }
        //at least 2 items in the list
        List<Interval> res = new ArrayList<>();
        /*
        * ? super E: some type which is a subclass of E
        * ? extends E: some type which is an ancestor of E
        *
        * 有comparator的sort:
        *list.sort(Comparator<? super E> c)
        *Arryas.sort(T[] a, Comparator<? super T>)
        *
        * */
        intervals.sort(new IntervalComparator()); //sort list 用
        Interval last = intervals.get(0);
        for(int i = 1; i < intervals.size(); i++){
            Interval cur = intervals.get(i);
            if(cur.start <= last.end){
                last.end = Math.max(last.end, cur.end); //end 的值不确定，所以要看哪个大
            }else{
                res.add(last);
                last = cur;
            }
        }
        //出loop的时候，最后一个值还没有被加进去
        res.add(last);
        return res;

    }

     class IntervalComparator implements Comparator<Interval> { //在class里面的类可以是public的， 也可以是Private的
        @Override
        public int compare(Interval i1, Interval i2){
            if(i1.start == i2.start){
                return 0;
            }
            //lower has higher priority
            return i1.start < i2.start ? -1 : 1;
        }
    }
}
