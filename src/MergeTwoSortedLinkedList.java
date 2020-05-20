/*
*
* Laicode
*40. Merge Two Sorted Linked Lists
Easy
Merge two sorted lists into one large sorted list.

Examples

L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
L1 = null, L2 = null, merge L1 and L2 to null
*
*
* */


public class MergeTwoSortedLinkedList {
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