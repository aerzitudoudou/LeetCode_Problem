import java.util.*;

/**
 * lintcode
 *
 *892. Alien Dictionary
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 *
 * Example
 * Example 1:
 *
 * Input：["wrt","wrf","er","ett","rftt"]
 * Output："wertf"
 * Explanation：
 * from "wrt"and"wrf" ,we can get 't'<'f'
 * from "wrt"and"er" ,we can get 'w'<'e'
 * from "er"and"ett" ,we can get 'r'<'t'
 * from "ett"and"rftt" ,we can get 'e'<'r'
 * So return "wertf"
 *
 * Example 2:
 *
 * Input：["z","x"]
 * Output："zx"
 * Explanation：
 * from "z" and "x"，we can get 'z' < 'x'
 * So return "zx"
 * Notice
 * You may assume all letters are in lowercase.
 * The dictionary is invalid, if a is prefix of b and b is appear before a.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return the smallest in normal lexicographical order
 *
 *
 */


//word 的平均长度是M, words list 的平均长度是N
//T: O(M * N)
//S: O(26^ 2)


public class AlienDictionary {

    /**
     * @param words: a list of words
     * @return: a string which is correct order
     */

    public String alienOrder(String[] words) {
        //build the graph by compare every two words
        Map<Character, Set<Character>> indegree = new HashMap<>();
        Map<Character, Set<Character>> neis = new HashMap<>();
        for(int i = 0; i < words.length; i++){ //T: (m * n)
            //collect all the characters appeared
            for(char c : words[i].toCharArray()){
                indegree.putIfAbsent(c, new HashSet());
                //第一次这里错了，没有initialize neis. 导致后面没有neighbor 的节点在generate的时候null pointer.
                //所以这里不管点有没有nei, 有没有indegree, 都要先放一个空set as value
                neis.putIfAbsent(c, new HashSet());
            }
        }

        //O(M *N)
        for(int i = 0; i < words.length - 1; i++){
            //更新indegree, nei
            String pre = words[i];
            String post = words[i + 1];
            for(int j = 0; j < Math.min(pre.length(), post.length()); j++){
                char preChar = pre.charAt(j);
                char postChar = post.charAt(j);
                if(preChar != postChar){
                    //update indegree
                    //第一次做这里错了，set.add()和set.remove() 都是返回boolean, 所以需要先做完add 或remove, 再放入map
                    Set<Character> inSet = indegree.get(postChar);
                    inSet.add(preChar);
                    indegree.put(postChar, inSet);
                    //update neighbors
                    Set<Character> neiSet = neis.get(preChar);
                    neiSet.add(postChar);
                    neis.put(preChar, neiSet);
                    break;

                }
            }
        }
        //topology sort on the graph, generated order is the result


        Deque<Character> queue = new LinkedList<>();

        //find chars whose indegree is 0
        for(Map.Entry<Character, Set<Character>> entry : indegree.entrySet()){
            if(entry.getValue().size() == 0){
                //no indegree, en-queue
                queue.offerFirst(entry.getKey());
            }
        }

        StringBuilder sb = new StringBuilder();
        //N-1条边，每两个generate 出来一个边，最多26个点， 时间复杂度是O(26 + (N - 1)) = O(N)
        while(!queue.isEmpty()){
            //expand
            Character tmp = queue.pollLast();
            sb.append(tmp);
            //generate
            for(Character c :neis.get(tmp)){
                Set<Character> set = indegree.get(c);
                //第一次做这里错了， 不是remove c, 而是除掉他的前序，i.e.tmp
                set.remove(tmp);
                indegree.put(c, set);
                //indegree is 0, en-queue
                if(set.size() == 0){
                    queue.offerFirst(c);
                }
            }
        }

        if(indegree.size() != sb.length()){
            return "";
        }
        return sb.toString();
    }
}
