import java.util.Random;


//reservoir sampling, similar to 398, from hf: https://www.youtube.com/watch?v=3dXUj5fGLXI
public class LinkedListRandomNode_LC382 {
    ListNode head;

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode_LC382(ListNode head) {
        this.head = head;

    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode cur = head;
        int k = 0;
        int x = 0;
        while(cur != null){
            k++;
            Random rand = new Random();
            if(rand.nextInt(k) == 0){
                x = cur.val;
            }
            cur = cur.next;
        }
        return x;
    }
}
