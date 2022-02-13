public class IntersectionOfTwoLinkedLIsts_LC160 {
    //sol1, my, O(n), O(1)
    //1, Get the length of the two lists.
    //
    //2, Align them to the same start point.
    //
    //3, Move them together until finding the intersection point, or the end null
    public ListNode getIntersectionNode(ListNode a, ListNode b) {
        int lenA = getLength(a), lenB = getLength(b);
        while(lenA > lenB){
            a = a.next;
            lenA--;

        }
        while(lenB > lenA){
            b = b.next;
            lenB--;
        }

        while(a != b){
            a = a.next;
            b = b.next;
        }

        return a;


    }

    private int getLength(ListNode head){
        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }
        return len;
    }
}
