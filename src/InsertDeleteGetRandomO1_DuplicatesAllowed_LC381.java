import java.util.*;

public class InsertDeleteGetRandomO1_DuplicatesAllowed_LC381 {
    class RandomizedCollection {
        //sol1, my, use arraylist to store multiple indexes as value type in the map
        //use another arraylist to do randomization
        Map<Integer, List<Integer>> map;
        List<Integer> list;
        /** Initialize your data structure here. */
        public RandomizedCollection() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            boolean containsKey = map.containsKey(val);
            List<Integer> cur = map.getOrDefault(val, new ArrayList<>());
            list.add(val);
            cur.add(list.size() - 1);
            map.put(val, cur);
            if(containsKey) return false;
            return true;

        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove (int val) {
            if(!map.containsKey(val)) return false;

            List<Integer> curList = map.get(val);
            int curIndex = curList.get(curList.size() - 1);

            int replaceIndex = list.size() - 1;
            int replaceNum = list.get(replaceIndex);

            //swap last element in list to the current spot
            list.set(curIndex, replaceNum);

            //remove last element from list
            list.remove(replaceIndex);

            //update replace list
            if(curIndex != replaceIndex){
                List<Integer> replaceList = map.get(replaceNum);
                replaceList.set(replaceList.indexOf(replaceIndex), curIndex);
            }
            //update curList
            curList.remove(curList.size() - 1);
            if(curList.isEmpty()) map.remove(val);
            else map.put(val, curList);


            return true;



        }

        /** Get a random element from the collection. */
        public int getRandom() {
            Random rand = new Random();
            return list.get(rand.nextInt(list.size()));
        }
    }

}
