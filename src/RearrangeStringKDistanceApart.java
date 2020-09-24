/*


Leetcode
* 358. Rearrange String k Distance Apart
Hard


Share
Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:

Input: s = "aabbcc", k = 3
Output: "abcabc"
Explanation: The same letters are at least distance 3 from each other.
Example 2:

Input: s = "aaabc", k = 3
Output: ""
Explanation: It is not possible to rearrange the string.
Example 3:

Input: s = "aaadbbcc", k = 2
Output: "abacabcd"
Explanation: The same letters are at least distance 2 from each other.
*
* */


/*
* Summary:
*  1. heap.addAll(Collection<> c) is O(m log m) : PriorityQueue inherits the addAll implementation from AbstractQueue which iterates over the collection and calls add on each element.
*  2. T: O(nlog26) = O(n) 此题中堆最大是26所以logn变成常数log26  S: O(26)
*  3. Max heap: 用来store - 可构建一个由k个不重复letter组成的trunk，的可用的<letter, 次数>
      list: 用来存储在下一轮可以被用来构建长度为K的trunk的<letter,次数>


e.g. aaabc k = 3
                             Heap                              List                         sb

init                       <a, 3>,<b, 1><c, 1>                 <>                           ""
==================================(times of heap poll = k = 3) ====================================
1st time poll:<a, 3>       <b, 1><c, 1>                        <a, 2>                       "a"
2nd time poll:<b, 1>       <c, 1>                              <a, 2>                       "ab"
3rd time poll:<c, 1>       empty                               <a, 2>                       "abc"

===================================offer back what's in list to heap===============================
                           <a, 2>

==================================another round of poll for 3 times================================
1st time poll<a, 3>        empty                               <a, 1>                       "abca"

heap can't do poll but list still has elements. hence invalid string and k --> case 1



e.g. aaadbbcc k = 2
                             Heap                              List                         sb

init                       <a, 3>,<b, 2><c, 2><d, 1>           <>                           ""
==================================(times of heap poll = k = 2) ====================================
1st time poll:<a, 3>       <b, 2><c, 2><d, 1>                  <a, 2>                       "a"
2nd time poll:<b, 2>       <c, 2><d, 1>                        <a, 2> <b, 1>                "ab"

===================================offer back what's in list to heap===============================
                           <a, 2><c, 2><b, 1><d, 1>             <>

==================================another round of poll for 2 times================================
1st time poll<a, 2>        <c, 2><b, 1><d, 1>                   <a, 1>                       "aba"
2nd time poll<c, 2>        <b, 1><d, 1>                         <a, 1> <c, 1>                "abac"

===================================offer back what's in list to heap===============================
                           <a, 1><b, 1><c, 1><d, 1>             <>

==================================another round of poll for 2 times================================
1st time poll<a, 1>        <b, 1><c, 1><d, 1>                   <>                            "abaca"
2nd time poll<b, 1>        <c, 1><d, 1>                         <>                            "abacab"


===================================offer back what's in list to heap===============================
nothing to offer back to heap in this round since list is empty
==================================another round of poll for 2 times================================
1st time poll<c, 1>             <d, 1>                          <>                            "abacabc"
2nd time poll<d, 1>                                             <>                            "abacabcd"

heap is empty and list is empty, therefore valid result. --> case 2 in the code
*Here if eventually heap is empty but hasn't finish polling 2 times,  result is still valid. --> case 3 in the code
* */

import java.util.*;

public class RearrangeStringKDistanceApart {
    public String rearrangeString(String s, int k) {
        if(s == null || s.length() == 0){
            return "";
        }
        //corner case
        if(k == 0){
            return s;
        }
        //map: <letter, times> O(n)
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> t1, Map.Entry<Character, Integer> t2) {
                if(t1.getValue().equals(t2.getValue())) return t1.getKey() < t2.getKey() ? -1 : 1;
                return t1.getValue() > t2.getValue() ? -1 : 1;
            }
        });
        maxHeap.addAll(map.entrySet()); //O(26log26)
        List<Map.Entry<Character, Integer>> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while(!maxHeap.isEmpty()){
            for(int i = 0; i < k; i++){
                if(maxHeap.isEmpty()){
                    //case 1
                    if(sb.length() < s.length()){
                        return "";
                    }
                    //case 3
                    return sb.toString();
                }
                Map.Entry<Character, Integer> entry = maxHeap.poll();
                sb.append(entry.getKey());
                int count = entry.getValue() - 1;
                if(count > 0){
                    entry.setValue(count);
                    list.add(entry);
                }
            }
            //把可以构建下一轮k个的<letter, times>放回heap
            for(Map.Entry<Character, Integer> entry : list){
                maxHeap.offer(entry);
            }
            list.clear();
        }
        //case 2
        return sb.toString();
    }

}
