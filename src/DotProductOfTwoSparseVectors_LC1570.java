import java.util.HashMap;
import java.util.Map;

public class DotProductOfTwoSparseVectors_LC1570 {

}
/*

 map<index, num> only save those which value is not 0 to save space
*/

class SparseVector {
    //T： O(n) for constructer to create hashmap  O(min(L1, L2)) for calculating dot product. L1, L2 is the number of non-zeros in each map
    //S:  O(L) for constructer O(1) for calculating the dot product
    Map<Integer, Integer> map = new HashMap<>();
    SparseVector(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                map.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        //calculate with smaller size to save time as O(min(L1, L2))
        if(this.map.size() > vec.map.size()) return vec.dotProduct(this);
        int res = 0;
        for(Integer i : this.map.keySet()){
            if(vec.map.containsKey(i)) res += vec.map.get(i) * this.map.get(i);
        }
        return res;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);