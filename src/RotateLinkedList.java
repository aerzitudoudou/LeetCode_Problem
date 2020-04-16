public class RotateLinkedList {

    /*
    * my way
    *
    * */
    public ListNode rotateRight(ListNode head, int k) {

        if(head == null || head.next == null){
            return head;
        }
        //find the length of the linkedlist, mark the tail
        ListNode tail = head;
        ListNode pre = null;
        int size = 0;
        while(tail != null){
            pre = tail;
            tail = tail.next;
            size++;

        }
        //out of the while loop , pre represent the last node of the original linkedlist
        k %= size;
        k = size - k - 1;

        ListNode cur = head;

        for(int i = 0; i < k; i++){
            cur = cur.next;
        }
        //加入k = 0， e.g. 1 - > 2 - > 3 --null 从1 开始走两步， 走到3, 是队尾。如果3没有在一开始就连到old head的话, 3.next 就是null了， 新的list 的newHead就是null 了。
        ListNode newHead = cur.next;
        if(cur.next == null){
            return head;    //因为之前判断出来的new tail 没有挂到old head 上去 cur.next 就是null 了， 如果要避免，就要先把环连起来，再断
        }
        cur.next = null;
        pre.next = head;
        return newHead;


    }


    //答案的方法:


    public ListNode rotateRight2(ListNode head, int k) {

        if(head == null || head.next == null){
            return head;
        }
        //find the length of the linkedlist, mark the tail
        ListNode tail = head;
        ListNode pre = null;
        int size = 0;
        while(tail != null){
            pre = tail;
            tail = tail.next;
            size++;

        }
        /*      - - - - - -
        *      |           |
        * e.g. 1 - > 2 - > 3      3在这一步连到了list的头
         *
        * */
        pre.next = head;



        //out of the while loop , pre represent the last node of the original linkedlist
        k %= size;
        k = size - k - 1;

        ListNode cur = head;

        for(int i = 0; i < k; i++){
            cur = cur.next;
        }
        //k = 0的话newHead就回到了1 = 原来的head
        ListNode newHead = cur.next;

        cur.next = null;
        return newHead;


    }
}
