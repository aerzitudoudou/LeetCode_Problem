public class RemoveDuplicatesFromSortedLIst {
    public ListNode deleteDuplicates(ListNode head) {
        //需要用傻头的两个条件： 1.需要创建一个linkedList from scratch
        //2. 不确定头是什么。 这道题确定头一定是原来的头，所以不需要傻头
        //corner case check:

        if(head == null || head.next == null){ //linkedList 的题一定要加corner case check. 否则很容易null pointer
            return head;
        }
        ListNode s = head, f = head;
        while(f != null){
            if(f.val != s.val){
                s.next = f;
                s = f;
            }
            f = f.next;
        }                                                  //        s          f
        s.next = null; //f 再s 前面，f 走完了如果最后元素重复 e.g. 1--> 3--> 3 -->null    所以要记得把尾巴处理干净
        return head;
    }
    //写完linked list 的代码一定要跑基本的test case: null, 一个listnode --> base case 两个listnode --> general case
}
