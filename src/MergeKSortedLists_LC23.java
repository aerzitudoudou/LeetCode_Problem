/**
 *
 *
 *
 *
 * Laicode 134
 * 134. Merge K Sorted Lists
 * Medium
 * Merge K sorted lists into one big sorted list in ascending order.
 *
 * Assumptions
 *
 * ListOfLists is not null, and none of the lists is null.
 *
 */






import java.util.*;



public class MergeKSortedLists_LC23 {



    //!!!!sol1, from lai, O(kn*logk), O(k)
    //n: number of nodes in a list  k: length of lists
    public ListNode mergeKLists1(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        int k = lists.length;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(k, (n1, n2) -> {
            return n1.val - n2.val;
        });

        for(int i = 0; i < k; i++){
            ListNode tmp = lists[i];
            if(tmp != null){
                pq.offer(tmp);
            }
        }

        ListNode dummy = new ListNode(-1), cur = dummy;
        while(!pq.isEmpty()){
            ListNode tmp = pq.poll();
            cur.next = tmp;
            cur = cur.next;
            if(tmp.next != null){
                pq.offer(tmp.next);
            }

        }

        return dummy.next;
    }

    //sol2: binary reduction， from lai
    /**
     * list 1
     * list 2   ==> 2n
     * list 3
     * list 4   ==> 2n                   ==> 4n
     * list 5
     * list 6   ==> 2n
     * list 7
     * list 8   ==> 2n                   ==> 4n                 ==> 8n
     *             (k/2) * 2n= kn     (k/4) * 4n = kn        (k/8) * 8n = kn
     *
     * T = logk * kn  一共有logk层
     * S = O(1)
     */

    public ListNode mergeKLists(ListNode[] ary) {
        List<ListNode> list = new ArrayList<>(Arrays.asList(ary));
        while(list.size() > 1){
            ListNode tmp = merge(list.get(0), list.get(1));
            list.add(tmp);
            list.remove(0);
            list.remove(0);
        }

        return list.get(0);
    }

    private ListNode merge(ListNode one, ListNode two){
        if(one == null) return two;
        if(two == null) return one;
        ListNode tmp;
        if(one.val < two.val){
            tmp = merge(one.next, two);
            one.next = tmp;
            return one;
        }else{
            tmp = merge(two.next, one);
            two.next = tmp;
            return two;
        }
    }


    //sol3: iterative, 一个一个轮着来，时间复杂度高
    /**
     * list 1
     * list 2   ==> 2n
     * list 3       ==>3n
     * list 4          ==> 4n
     * ...
     * list k               ==>kn
     *
     * T = (2 + 3 + 4 + ....+ k)n = k^2 * n
     * S = O(1)
     *
     *
     *
     *
     */






}
