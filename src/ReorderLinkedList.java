//leetcode 143
//T: O(n)
//S: O(1)
public class ReorderLinkedList {
    public void reorderList(ListNode head) {
        //less than 3 nodes, return itself
        if(head == null || head.next == null || head.next.next == null){
            return;
        }
        ListNode mid = findMid(head);
        ListNode rev = reverse(mid.next);
        mid.next = null;
        merge(head, rev);
    }

    private ListNode findMid(ListNode head){
        ListNode f = head, s = head;
        while(f.next != null && f.next.next != null){
            s = s.next;
            f = f.next.next;
        }
        return s;
    }
    //              p   hnext
    //null <-  1 <- 2  null
    private ListNode reverse(ListNode head){
        ListNode pre = null, next = null, cur = head;
        while(cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;

        }
        return pre;
    }

    /*
        an1
      1  2->null
      |  /
         bn2
      3  null

    */

    //naming convention!
    private void merge(ListNode a, ListNode b){
        ListNode nextA = a,  nextB = b;
        while(nextA != null && nextB != null){
            nextA = a.next;
            nextB = b.next;
            a.next = b;
            b.next = nextA;
            a = nextA;
            b = nextB;

        }
        return;
    }

}
