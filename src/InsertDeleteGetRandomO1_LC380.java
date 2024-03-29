import java.util.*;

public class InsertDeleteGetRandomO1_LC380 {
    class RandomizedSet {
        //sol1, acwing
        //list remove last digit time complexity?
        //<val, index>
        Map<Integer, Integer> map;

        List<Integer> list;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(!map.containsKey(val)){
                list.add(val);
                map.put(val, list.size() - 1);
                return true;
            }
            return false;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(map.containsKey(val)){
                int index = map.get(val);
                // if(index != list.size() - 1){
                list.set(index, list.get(list.size() - 1));
                map.put(list.get(index), index);
                // }

                list.remove(list.size() - 1);
                map.remove(val);

                return true;
            }
            return false;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            Random rand = new Random();
            return list.get(rand.nextInt(list.size()));
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
