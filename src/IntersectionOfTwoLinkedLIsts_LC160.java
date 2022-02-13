public class IntersectionOfTwoLinkedLIsts_LC160 {
    //sol1, my, O(n + m), O(1)
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


    //!!!!!sol2, physical prob, O(n), O(1)
    //                    a1
    //A              B-------
    //             a2
    //           ----
    //                b
    //B          ----A-------
    //Va = Vb, when b finishes, a must be finish the a2 part at B. at this point move b from finish line to starting of a1 garantees A and B are moving from the same position in parallel.
    public ListNode getIntersectionNode2(ListNode a, ListNode b) {
        ListNode startA = a, startB = b;
        while(a != b){ //if no intersection, then a == b == null also satisfies
            a = (a == null) ? startB : a.next;
            b = (b == null) ? startA : b.next;
        }
        return a;
    }
}
