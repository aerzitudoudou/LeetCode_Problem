/*
*
*
*
*Laicode
*205. Implement LRU Cache
Medium
Implement a least recently used cache.
*It should provide set(), get() operations. If not exists, return null (Java), false (C++), -1(Python).
*
*
*
* use cases:
* 1. find corresponding answer to a question --> hashmap
* 2. adjust the timestamp of a particular entry(线性数据结构排除： queue, deque) 线性数据结构只剩linkedlist. 因为需要remove一个node, doubly 可以更快的找到前后节点完成删除，所以用doubly linkedlist
* 3. find and delete the oldest entry (线性数据结构排除： array（时间复杂度太高） stack)
* 4. insert an entry as newest (线性数据结构都可以: array, stack, queue, deque, linked list)
*
* 通过分析四个case得出我们应该用hashmap + doubly linked list
*
*
*
*
*
* */


import java.util.HashMap;
import java.util.Map;
//way1: myself's 不优雅做法
//T: O(1)
//S: O(capacity)
public class LRUCache<K, V> {
    //引用variable时候用non-static. static inner class 的variable 只能class level 的引用，不能被non-static method 使用
    class ListNode{
        private K key;
        private V value;
        ListNode prev;
        ListNode next;
        public ListNode(K key, V value){
            this.key = key;
            this.value = value;
        }

    }

    private Map<K, ListNode> map = new HashMap<>();
    private int limit;
    private ListNode dummy = new ListNode(null, null);
    private ListNode tail = dummy;
    public LRUCache(int limit) {
        if(limit <= 0){
            throw new IllegalArgumentException("capacity cannot be smaller than 0.");
        }
        this.map = map;
        this.limit = limit;
    }

    // 头：旧 ----------尾： 新
    public void set(K key, V value) {

        //less than capacity
        //map 里没有Key :1. map加<key, Node>  2.linkedlist 把node加到末尾
        if(!map.containsKey(key)){
            if(map.size() < limit){
                ListNode tmp = new ListNode(key, value);
                map.put(key, tmp);
                tail.next = tmp;
                tmp.prev = tail;
                tail = tail.next;
            }else{
                //equal capacity
                //map 里没有Key，1.map删掉key对应entry(remove(Object Key)), 加新的<Key, Node>  2.把node加到末尾, LinkedList 删掉linkedList 头
                K curKey = dummy.next.key;
                map.remove(curKey);
                ListNode cur = new ListNode(key, value);
                map.put(key, cur);
                tail.next = cur;
                //doubly linkedList 需要把next 和prev都接上 一开始忘了接上prev了
                cur.prev = tail;
                tail = tail.next;
                ListNode tmp = dummy.next.next;
                dummy.next = tmp;
                tmp.prev = dummy;
            }
        }
        // else{  //regardless of capacity, map 里有， 1. map不变 2.LinkedList不用动因为不需要查询
        //     ListNode curNode = map.get(key);
        //     curNode.prev.next = curNode.next;
        //     curNode.next.prev = curNode.prev;
        //     tail.next = curNode;
        //     tail = tail.next;
        // }
    }

    public V get(K key) {
        if(!map.containsKey(key)){
            return null;
        }else{    //map 里有，1. map不变， 2.LinkedList 把node加到末尾

            ListNode curNode = map.get(key);
            if(curNode!= tail){
                ListNode n1 = curNode.prev;
                n1.next = curNode.next;
                curNode.prev.next = curNode.next;
                curNode.next.prev = curNode.prev;
                tail.next = curNode;
                tail = tail.next;
            }
            return tail.value;



        }
    }





//way 2: more elegant way de-couple same logic into methods remove, appendtails

    //不需要dummy 在做的过程中更新head 和tail
    private ListNode headNode = null, tailNode = null;
    //add a node to the end of the doubly linked list
    private void appendTail(ListNode node){
        if(tailNode == null){
            tailNode = node;
            headNode = node;
        }else{
            tailNode.next = node;
            node.prev = tailNode;
            tailNode = tailNode.next;
        }
    }
    // 头：旧 ----------尾： 新
    //T: O(1)
    public void set1(K key, V value) {

        //less than capacity
        //map 里没有Key :1. map加<key, Node>  2.linkedlist 把node加到末尾
        if(!map.containsKey(key)){
            if(map.size() < limit){
                ListNode tmp = new ListNode(key, value);
                map.put(key, tmp);
                appendTail(tmp);
            }else{
                //equal capacity
                //map 里没有Key，1.map删掉key对应entry(remove(Object Key)), 加新的<Key, Node>  2.把node加到末尾, LinkedList 删掉linkedList 头
                map.remove(headNode.key);
                ListNode cur = new ListNode(key, value);
                map.put(key, cur);
                appendTail(cur);
                //删掉头
                remove(headNode);
            }
        }
        //map 里有key 啥都不干维持原状
    }


    //T: O(1)
    //S: O(capacity)
    public V get1(K key) {
        if(!map.containsKey(key)){
            return null;
        }else{    //map 里有，1. map不变， 2.LinkedList 把node从原来位置移到末尾
            ListNode node = map.get(key);
            remove(node);
            appendTail(node);
            return tailNode.value;
        }
    }


    //任意挪linkedlist里面的一个element


    private void remove(ListNode node){
        if(headNode == null && tailNode == null){
            return;
        }
        if(node.prev != null){
            node.prev.next = node.next;
        }else{//node 是head
            headNode = node.next;
        }
        if(node.next != null){
            node.next.prev = node.prev;
        }else{//node 是tail
            tailNode = node.prev;
        }
    }


   //way3: 参考LRUCacheLinkedHashMap

}
