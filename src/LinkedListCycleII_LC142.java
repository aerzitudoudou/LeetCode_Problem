//leetcode 142

public class LinkedListCycleII_LC142 {
    public ListNode detectCycle(ListNode head) {
        ListNode s = head, f = head;

        //sol1, from jh 1.check valid 2.run fast slow 3.check meet
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
    //!!!sol2, my, O(n), O(1)
    /*  slow fast pointers, find meet point, (if no cycle return null), then point slow to the head and move both fast and slow 1 step at a time. the next meeting point is the cycle start
    * proof:
    *   Assume the distance from head to the start of the loop is x1
        the distance from the start of the loop to the point fast and slow meet is x2
        the distance from the point fast and slow meet to the start of the loop is x3
        * S1 = x1 + x2
        * S2 = x1 + x2 + x3 + x1
        * since V2 = 2V1, for same amout of time S2 = 2S1
        * i.e. x1 + x2 + x3 + x1 = 2(x1 + x2) => x1 = x3 => slow at the beginning of x1, fast at the beginning of x3, both move 1 step at a time, next meeting point is the loop start
        *
    *
    * */
    public ListNode detectCycle1(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next!= null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){ //1st meet, slow to head, continue jump to find next meet point which is the starting of the cycle
                slow = head;
                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }

        }

        return null;

    }
}
