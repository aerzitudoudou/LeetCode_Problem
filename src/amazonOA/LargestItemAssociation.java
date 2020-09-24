package amazonOA;


import java.util.*;

public class LargestItemAssociation {
    public List<String> largestItemAssociation(List<PairString> itemAssociation) {
        //T: O(nlogn) where n is the number of elements in itemAssociation
        //S: O(n)
        Map<String, String> parentMap = new HashMap<>();
        Map<String, TreeSet<String>> unionMap = new HashMap<>();
        //use Disjoint Set Union to solve this problem
        //step 1: make set, have each element's parent as themselves in the PairString
        for(PairString pair : itemAssociation){
            parentMap.putIfAbsent(pair.first, pair.first);
            parentMap.putIfAbsent(pair.second, pair.second);
        }

        //step2: union
        for(PairString pair : itemAssociation){
            parentMap.put(find(pair.second, parentMap), find(pair.first, parentMap));
        }

        //step 3: find disjoint set
         for(Map.Entry<String, String> entry : parentMap.entrySet()){
             String root = find(entry.getKey(), parentMap);
             TreeSet<String> set = unionMap.getOrDefault(root, new TreeSet<String>());
             set.add(entry.getKey());
             unionMap.put(root, set);
         }

         //step 4: generate results from disjoint set
        int size = 0;
        List<String> res = new ArrayList<>();
        for(TreeSet<String> set : unionMap.values()){
            if(set.size() > size){
                size = set.size();
                res = new ArrayList<String>(set);
            }else if(set.size() == size){
                res =  set.first().compareTo(res.get(0)) < 0 ? new ArrayList<String>(set) : res;
            }
        }
        return res;


    }

    private String find(String str, Map<String, String> parentMap){
        while(!parentMap.get(str).equals(str)){
            str = parentMap.get(str);
        }
        return str;
    }


    public static class PairString {
        String first;
        String second;

        public PairString(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }
}

