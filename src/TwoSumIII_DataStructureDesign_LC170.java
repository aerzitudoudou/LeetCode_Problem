import java.util.HashMap;
import java.util.Map;

//sol1, my
public class TwoSumIII_DataStructureDesign_LC170 {

    Map<Integer, Integer> map;


    public TwoSumIII_DataStructureDesign_LC170() {
        map = new HashMap<>();

    }

    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    //O(n), O(n)
    public boolean find(int value) {
        for(int key : map.keySet()){
            int second = value - key;
            if(key != second && map.containsKey(second) ||
                    key == second && map.get(key) > 1) return true;
        }
        return false;
    }
}
