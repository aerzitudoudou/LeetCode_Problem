import java.util.LinkedHashMap;
import java.util.Map;

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
