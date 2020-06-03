import java.util.LinkedHashMap;
import java.util.Map;


/*
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
*
*
*
* */


//Java 自带的LinkedHashMap就是干LRU这个事儿的！
public class LRUCacheLinkedHashMap extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCacheLinkedHashMap(int capacity){
        /*
        *
        *
        * A special constructor is provided to create a linked hash map whose order of iteration is the order
        * in which its entries were last accessed, from least-recently accessed to most-recently (access-order).
        * This kind of map is well-suited to building LRU caches.
        * Invoking the put, putIfAbsent, get, getOrDefault, compute, computeIfAbsent, computeIfPresent, or merge methods
        * results in an access to the corresponding entry (assuming it exists after the invocation completes). -->调用这些method使对应entry access order 变为newest
         *
        *constructor:
        * LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)
          Constructs an empty LinkedHashMap instance with the specified initial capacity, load factor and ordering mode
          accessOrder = true means it is the order from least-recently to most-recently
        * */
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }
    public int get(int key){
        return getOrDefault(key, -1);
    }


    public void put(int key, int value){
        put(key, value);
    }

    /*
    *
    * protected boolean	removeEldestEntry(Map.Entry<K,V> eldest)
      Returns true if this map should remove its eldest entry.
    *
    *
    * */
    @Override
    public boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){
        return this.size() > capacity;
    }
}
