/*
*
*laicode
*
* 662. Word Ladder II
Medium
Given a begin word, an end word and a dictionary, find the least number transformations from begin word to end word, and return the transformation sequences. Return empty list if there is no such transformations.

In each transformation, you can only change one letter, and the word should still in the dictionary after each transformation.

Assumptions

1. All words have the same length.

2. All words contain only lowercase alphabetic characters.

3. There is no duplicates in the word list.

4.The beginWord and endWord are non-empty and are not the same.

Example: start = "git", end = "hot", dictionary = {"git","hit","hog","hot","got"}

Output: [["git","hit","hot"], ["git","got","hot"]]
* suppose words' length is N
* T: O(n * L) + O(m * n)
* S: O(N^2 (preds list) + N (indexMap) + N (steps) + S(dfs stack tree height)) = O(N^ 2 + S)
*
* */

import java.util.*;

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //corner case check
        List<List<String>> res = new ArrayList<>();
        if(wordList.indexOf(endWord) == -1){
            return res;
        }
        List<String> words = new ArrayList<>(wordList); //S: O(N)

        if(wordList.indexOf(beginWord) == -1){
            words.add(beginWord);
        }
        int[] step = new int[words.size()]; //S: O(N)
        Arrays.fill(step, -1); //T: O(n)
        NeiFinder neiFinder = new NeiFinder(words); //T: O(n)
        Tracer tracer = new Tracer(words); //T: O(n)
        Deque<Integer> queue = new LinkedList<>();
        int beginIndex = neiFinder.getIndex(beginWord);
        queue.offerFirst(beginIndex);
        step[beginIndex] = 0;
        int lastIndex = neiFinder.getIndex(endWord);
        while(!queue.isEmpty()){ //T: O(n * L) + O(m * n)
            int tmp = queue.pollLast();
            if(tmp == lastIndex){
                res = tracer.getLadder(beginIndex, lastIndex); //T: O(m * n)
            }
            for(Integer a : neiFinder.findNei(tmp)){ //findNei T: O(L)
                //查重
                if(step[a] == -1){ //T: O(1)
                    queue.offerFirst(a);
                    step[a] = step[tmp] + 1;
                }
                if(step[a] == step[tmp] + 1){
                    //add tmp as the pred of a
                    tracer.addPred(tmp, a); //T: O(1)

                }

            }
        }

        return res;


    }

    static class Tracer{
        private List<String> words = new ArrayList<>();
        private List<List<Integer>> preds = new ArrayList<>(); //S: 这里的空间复杂度怎么算？ O(n ^ 2) worst?
        public Tracer(List<String> words){
            this.words = words;
            for(int i = 0; i < words.size(); i++){
                preds.add(new ArrayList<Integer>());
            }
        }

        public void addPred(int pred, int cur){
            List<Integer> predList = preds.get(cur);
            predList.add(pred);
        }

        //dfs: m is the number of ladders
        //T: dfs recursion tree node 个数：O(n) + reverse 用的时间： O(m * n) = O(m * n)
        //S: stack: suppose shortest transformation length is S then stack space complexity = O(S) + O(N):cur arraylist for holding current result and passing across different recursion levels
        public List<List<String>> getLadder(int begin, int last){
            List<List<String>> res = new ArrayList<>();
            List<String> cur = new ArrayList<>(); //S: O(N)
            cur.add(words.get(last));
            helper(begin, last, res, cur);
            return res;

        }



        private void helper(int begin, int last, List<List<String>> res, List<String> cur){
            if(last == begin){
                List<String> tmp = new ArrayList<>(cur);
                Collections.reverse(tmp);
                res.add(tmp);
            }
            for(Integer i : preds.get(last)){
                cur.add(words.get(i));
                helper(begin, i, res, cur);
                cur.remove(cur.size() - 1);
            }
        }

    }

    static class NeiFinder{
        private List<String> words;
        private Map<String, Integer> wordIndex = new HashMap<>(); //O(N)
        public NeiFinder(List<String> words){
            this.words = words;
            for(int i = 0; i < words.size(); i++){
                wordIndex.put(words.get(i), i);
            }
        }

        public int getIndex(String word){
            return wordIndex.getOrDefault(word, -1);
        }
        //suppose word's length is L
        public List<Integer> findNei(int tmp){
            List<Integer> res = new ArrayList<>();
            StringBuilder sb = new StringBuilder(words.get(tmp));
            //for each char in the word, replace with a - z, check against the map to see if there's match
            for(int i = 0; i < sb.length(); i++){ //T: O(L * 26) = O(L)
                char cur = sb.charAt(i);
                for(char c = 'a'; c <= 'z'; c++){ //T: O(26)
                    if(c == cur){
                        continue;
                    }
                    sb.setCharAt(i, c);
                    String str = sb.toString();
                    if(wordIndex.containsKey(str)){
                        res.add(wordIndex.get(str));
                    }
                }
                sb.setCharAt(i, cur);
            }
            return res;

        }
    }
}
