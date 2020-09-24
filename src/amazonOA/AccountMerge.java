package amazonOA;

import java.util.*;

/*leetcode 721*/


/*

* 没有path compression， 没有union by rank时间复杂度：
 https://en.wikipedia.org/wiki/Proof_of_O(log*n)_time_complexity_of_union%E2%80%93find#:~:text=Statement%3A%20If%20m%20operations%2C%20either,log*%20is%20the%20iterated%20logarithm.
*Statement: If m operations, either Union or Find, are applied to n elements, the total run time is O(m log*n), where log* is the iterated logarithm.
*
* 有path compression 没有union by rank的时间复杂度分析：
* Seidel and Sharir proved in 2005 [1] that using path compression with arbitrary linking roughly on m operations has a complexity of roughly O((m+n)log(n)).
*
* 有union by rank, 没有path compression: O(m*logn)

有union by rank, 有path compression:
O( alpha(n))amortized time per operation.  Here, the function alpha (n) is the inverse Ackermann function. The inverse Ackermann function grows extraordinarily slowly,
so this factor is 4 or less for any n that can actually be written in the physical universe i.e. O(1)
This makes disjoint-set operations practically constant time.




 */
public class AccountMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //map: <parent, set of string for that parent>
        Map<String, TreeSet<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        UF uf = new UF(accounts);

        //nlogn
        for(List<String> list : accounts){
            for(int i = 2; i < list.size(); i++){
                uf.union(list.get(i), list.get(1));
            }
        }
        //entry: <str, parentString>

        //O(nlogn) where n is the total number of strings in the list of list of strings
        for(List<String> list : accounts){
            for(int i = 1; i < list.size(); i++){
                String cur = uf.find(list.get(i)); //!!!做错。判断是不是在一个set的时候需要调用find 而不能直接check parent
                TreeSet<String> set = map.getOrDefault(cur, new TreeSet<String>());
                set.add(list.get(i)); //O(logn)
                map.put(cur, set);

            }
        }

        //generate result
        for(Map.Entry<String, TreeSet<String>> entry : map.entrySet()){
            List<String> list = new ArrayList<>();
            list.add(uf.owner.get(entry.getKey()));
            list.addAll(entry.getValue());
            res.add(list);
        }

        return res;
    }

    static class UF{
        Map<String, String> parent = new HashMap<>();
        Map<String, String> owner = new HashMap<>();

        public UF(List<List<String>> input){
            for(List<String> list : input){
                for(int i = 1; i < list.size(); i++){
                    owner.putIfAbsent(list.get(i), list.get(0));
                    parent.putIfAbsent(list.get(i), list.get(i));
                }
            }

        }
        //O(1)??
        public String find(String str){
            while(!parent.get(str).equals(str)){
                //parent[x] = parent[parent[x]] path compression
                String p = parent.get(str);
                parent.put(p, parent.get(p));
                str = parent.get(str);
            }
            return str;
        }

        //O(1)??
        public void union(String str1, String str2){
            String pStr1 = find(str1);
            String pStr2 = find(str2);
            parent.put(pStr1, pStr2);
        }

    }
}
