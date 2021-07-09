import java.util.*;

public class AlienDictionary_LC269 {
    /*
     0. find all letters because not all letter will be shown up in building the maps
     1. build adjancency list based on the words provided
     indegree: Map<char, Set<char>>  what points to it
        neis:     Map<char, Set<char>>  what it points to

     2. run topological sort

     */
    //2021/07/07.
    //T:O(M * N) m is length of words ary, n is length of each word
    //S:O(m * n ) --> O(26 ^ 2) 最多26个点每个点有25个边 最多需要26^2的空间在map里
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> indegree = new HashMap<>();
        Map<Character, Set<Character>> neis = new HashMap<>();

        for(int i = 0; i < words.length; i++){
            String word = words[i];
            for(int j = 0; j < word.length(); j++){
                indegree.put(word.charAt(j), new HashSet<>());
                neis.put(word.charAt(j), new HashSet<>());
            }

        }

        for(int i = 0; i < words.length - 1; i++){
            String preStr = words[i];
            String postStr = words[i + 1];
            //corner case check for [abc, ab] --> this is not sorted lexicographically but show as a test case so have to handle it
            if(preStr.length() > postStr.length() && preStr.startsWith(postStr)) return "";

            for(int j = 0; j < Math.min(preStr.length(), postStr.length()); j++){
                char pre = preStr.charAt(j);
                char post = postStr.charAt(j);
                if(pre != post){
                    //indegree
                    Set<Character> inSet =  indegree.getOrDefault(post, new HashSet<>());
                    inSet.add(pre);
                    indegree.put(post, inSet);
                    //neis
                    Set<Character> neiSet = neis.getOrDefault(pre, new HashSet<>());
                    neiSet.add(post);
                    neis.put(pre, neiSet);
                    break;
                }

            }
        }


        Deque<Character> queue = new LinkedList();
        for(Map.Entry<Character, Set<Character>> entry : indegree.entrySet()){
            if(entry.getValue().size() == 0){
                queue.offerFirst(entry.getKey());
            }
        }

        StringBuilder sb = new StringBuilder();
        //graph bfs的时间复杂度由边数决定。 m - 1个边， 所以只是bfs的话时间复杂度是O(m)
        while(!queue.isEmpty()){
            Character cur = queue.pollLast();
            sb.append(cur);
            //update indegree
            for(Character c : neis.get(cur)){
                Set<Character> inSet = indegree.get(c);
                inSet.remove(cur);
                indegree.put(c, inSet);

                if(inSet.size() == 0){
                    queue.offerFirst(c);
                }
            }
        }

        return sb.length() == indegree.size() ? sb.toString() : "";
    }
}
