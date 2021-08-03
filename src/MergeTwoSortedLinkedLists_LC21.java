
public class MergeTwoSortedLinkedLists_LC21 {

    //sol1, my , iterative, O(m+n), O(1)
     public ListNode mergeTwoLists1(ListNode one, ListNode two) {
         ListNode d = new ListNode(-1), cur = d;
         while(one != null && two != null){
             ListNode tmp;
             if(one.val < two.val){
                 tmp = one;
                 one = one.next;
             }else{
                 tmp = two;
                 two = two.next;
             }
             cur.next = tmp;
             cur = cur.next;
         }

         if(one == null){
             cur.next = two;
         }else{
             cur.next = one;
         }
         return d.next;
     }

    //sol2, recursive, O(m + n), O(m + n)
    //m: one's length n: two's length
    public ListNode mergeTwoLists(ListNode one, ListNode two) {
        if(one == null) return two;
        if(two == null) return one;
        ListNode tmp;
        if(one.val < two.val){
            tmp = mergeTwoLists(one.next, two);
            one.next = tmp;
            return one;
        }else{
            tmp = mergeTwoLists(two.next, one);
            two.next = tmp;
            return two;
        }

    }


    public ListNode merge(ListNode one, ListNode two) {
        //iterative
        //T: O(m + n)
        //S: O(1)
        ListNode dummy = new ListNode(0), cur = dummy;
        while (one != null && two != null) {
            if (one.val < two.val) {
                cur.next = one;
                one = one.next;
            } else {
                cur.next = two;
                two = two.next;
            }
            cur = cur.next;
        }
        if (one != null) {
            cur.next = one;
        }
        if (two != null) {
            cur.next = two;
        }
        return dummy.next;
    }

    //recursion
    //T:O(m + n)
    //S:O(m + n)
    public ListNode merge2(ListNode one, ListNode two) {
        if (one == null) {
            return two;
        }
        if(two == null){
            return one;
        }
        if(one.val < two.val) {
            ListNode tmp = merge2(one.next, two);
            one.next = tmp;
            return one;
        }else{
            ListNode tmp = merge2(one, two.next);
            two.next = tmp;
            return two;
        }

    }
}