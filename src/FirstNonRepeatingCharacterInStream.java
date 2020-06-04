/*

*
*Laicode
*288. First Non-Repeating Character In Stream
Medium
Given a stream of characters, find the first non-repeating character from stream. You need to tell the first non-repeating character in O(1) time at any moment.

Implement two methods of the class:

read() - read one character from the stream
firstNonRepeating() - return the first non-repoeating character from the stream at any time when calling the method, return null if there does not exist such characters
Examples:

read:

a   b   c   a   c   c    b

firstNonRepeating:

a   a   a   b   b   b   null
*
*-简单版LRU，一个套路。 都是用doubly linkedlist + Hashmap实现
*-LRU操作是remove一个node, 放到最后； 这道题是直接remove 或者直接放最后
* */


import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingCharacterInStream {
    class ListNode{
        char c;
        ListNode prev;
        ListNode next;
        public ListNode(char c){
            this.c = c;
        }
    }
    //class variable 和 method variable： 区别 总结
    Map<Character, ListNode> map;
    ListNode head;
    ListNode tail;
    public FirstNonRepeatingCharacterInStream() {
        this.map = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    public void read(char ch) {
        //case 1: hashmap doesn't have the char,  1. add entry into map<char, node> 2.add char to the end of the doubly linked list
        if(!map.containsKey(ch)){
            append(ch);
        }else if(map.get(ch) != null){
            deDup(ch);
        }

        //case 2: hashmap have the char,  but the value is null : nothing needs to be done


        //case 3: hashmap have the char,  but the value is not null(not a node): 1. change map to <char, null> 2. delete the char from the doubly linked list

    }


    public Character firstNonRepeating() {
        //return the first char in the doubly linked list
        if(head != null){
            return head.c;
        }
        return null;

    }

    private void deDup(char c){
        ListNode node = map.get(c);
        map.put(c, null);
        if(node.prev != null){
            node.prev.next = node.next;
        }else{
            head = node.next;
        }
        if(node.next != null){
            node.next.prev = node.prev;
        }else{
            tail = node.prev;
        }
    }



    private void append(char c){
        ListNode node = new ListNode(c);
        map.put(c, node);
        if(head == null && tail == null){
            head = node;
            tail = node;
        }else{
            tail.next = node;
            node.prev = tail;
            tail = tail.next;
        }

    }
}
