import java.util.*;
/*

Laicode
* 661. Word Ladder
Medium
Given a begin word, an end word and a dictionary, find the least number transformations from begin word to end word, and return the length of the transformation sequence. Return 0 if there is no such transformations.

In each transformation, you can only change one letter, and the word should still in the dictionary after each transformation.

Assumptions

1. All words have the same length.

2. All words contain only lowercase alphabetic characters.

3. There is no duplicates in the word list.

4.The beginWord and endWord are non-empty and are not the same.

Example: start = "git", end = "hot", dictionary = {"git","hit","hog","hot"}

Output: 3

Explanation: git -> hit -> hot
*
*
*
* */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Deque<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        NeighborFinder finder = new NeighborFinder(wordList);
        //注意这里的corner case 处理: 单词不存在 已经 beginword = endWord 的情况 第一遍没有check
        int begin = finder.findIndex(beginWord);
        int end = finder.findIndex(endWord);
        if(begin == -1 || end == -1){
            return 0;
        }
        if(begin == end){
            return 1;
        }
        //bfs
        queue.offerFirst(begin);
        set.add(begin);
        int level = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            level++;
            for(int i = 0; i < size; i++){
                //expand
                int tmp = queue.pollLast();
                List<Integer> neis = finder.findNei(tmp);
                for(Integer a : neis){
                    if(a.equals(end)){
                        return level;
                    }
                    if(!set.contains(a)){
                        queue.offerFirst(a);
                        set.add(a);
                    }
                }
            }

        }
        return 0;

    }




    static class NeighborFinder{
        private List<String> wordList = new ArrayList<>();
        //wordIndex 是<单词， index> 的map
        private Map<String, Integer> wordIndex = new HashMap<>();

        public NeighborFinder(List<String> wordList){
            for(int i = 0; i < wordList.size(); i++){
                wordIndex.put(wordList.get(i), i);
            }
            this.wordList = wordList;

        }


        public List<Integer> findNei(int i){
            List<Integer> neighbors = new ArrayList<>();
            StringBuilder word = new StringBuilder(wordList.get(i));
            for(int k = 0; k < word.length(); k++){
                char orig = word.charAt(k);
                for(char c = 'a'; c <= 'z'; c++){
                    if(c == orig){
                        continue;
                    }
                    word.setCharAt(k, c);
                    int value = wordIndex.getOrDefault(word.toString(), -1);
                    if(value != -1){
                        neighbors.add(value);
                    }
                }
                //把word还原回去, 第一遍忘了这一步
                word.setCharAt(k, orig);
            }
            return neighbors;
        }

        public int findIndex(String word){
            return wordIndex.getOrDefault(word, -1);
        }


    }
}
