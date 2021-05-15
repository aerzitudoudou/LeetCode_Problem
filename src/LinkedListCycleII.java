//leetcode 142

public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        ListNode s = head, f = head;

        //from jh 1.check valid 2.run fast slow 3.check meet
        while(true){
            if(f == null || f.next == null){ //f.next == null first exclude invalid case
                return null;
            }
            f = f.next.next;
            s = s.next;
            if(f == s){
                break;
            }
        }


        s = head;
        while(f != s){
            s = s.next;
            f = f.next;
        }
        return s;

    }
}
