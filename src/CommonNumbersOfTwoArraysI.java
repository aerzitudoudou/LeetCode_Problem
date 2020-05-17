import java.util.*;

/*
*
*Laicode
* 650. Common Numbers Of Two Arrays I(Array version)
Easy
Find all numbers that appear in both of the two unsorted arrays, return the common numbers in increasing order.

Assumptions

Both arrays are not null.
There are no duplicate numbers in each of the two arrays respectively.
Exmaples

A = {1, 2, 3}, B = {3, 1, 4}, return [1, 3]
A = {}, B = {3, 1, 4}, return []
*
* */
public class CommonNumbersOfTwoArraysI {
    //way 1:
    //sort each and  find common
    //a's length = m, b's length = n
    //T: mlogm + nlogn
    //S: O(max(m,n))
    public List<Integer> common(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        while(i < a.length && j < b.length){
            if(a[i] == b[j]){
                res.add(a[i]);
                i++;
                j++;
            }else if(a[i] < b[j]){
                i++;
            }else{
                j++;
            }
        }


        return res;
    }

    //way 2
    //use a hashset
    //a's length = m, b's length = n
    //T: O(m + n + min(m,n)log(min(m,n)))
    //S: O(min(m,n))
    public List<Integer> common2(int[] a, int[] b) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < a.length; i++){
            set.add(a[i]);
        }

        for(int i = 0; i < b.length; i++){
            if(set.contains(b[i])){
                res.add(b[i]);
            }
        }
        Collections.sort(res);
        return res;
    }
}
