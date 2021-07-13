public class ReverseLinkedList {
        /**
         * @param head: n
         * @return: The new head of reversed linked list.
         */
        //iterative
        //2021/07/12 fb phone screen question
        public ListNode reverse1(ListNode head) {
            ListNode pre = null;
            ListNode cur = head;
            while(cur != null){
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
        //recursive
        public ListNode reverse2(ListNode head) {
            if(head == null || head.next == null){
                return head;
            }
            ListNode tmp = reverse2(head.next);
            head.next.next = head;
            head.next = null;
            return tmp;
        }


}
