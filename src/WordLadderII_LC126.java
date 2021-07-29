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

public class WordLadderII_LC126 {
    //sol 4. pure bfs from https://leetcode.com/problems/word-ladder-ii/discuss/40434/C%2B%2B-solution-using-standard-BFS-method-no-DFS-or-backtracking



    //sol 3.
    //level order traversal, from  https://leetcode.com/problems/word-ladder-ii/discuss/1359027/C%2B%2BPython-BFS-Level-by-Level-with-Picture-Clean-and-Concise
    //O(mn) O(mn)
    public List<List<String>> findLadders4(String begin, String end, List<String> wordList) {
        Map<String, List<List<String>>> level = new HashMap<>();//current level key as endword, their paths from beginword
        List<List<String>> res = new ArrayList<>();
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(end)){
            return res;
        }
        List<String> preList = new ArrayList<>();
        preList.add(begin);
        List<List<String>> preListList = new ArrayList<>();
        preListList.add(preList);
        level.put(begin, preListList);
        set.remove(begin);
        while(!level.isEmpty()){
            Map<String, List<List<String>>> newLevel = new HashMap<>();
            for(Map.Entry<String, List<List<String>>> entry : level.entrySet()){
                String word = entry.getKey(); //every word
                if(word.equals(end)) return entry.getValue();
                for(String nei : getNei(set, word)){
                    for(List<String> path : entry.getValue()){ //every path

                        path.add(nei);
                        List<List<String>> paths = newLevel.getOrDefault(nei, new ArrayList<>());
                        paths.add(path);
                        newLevel.put(nei, paths);
                    }
                }


            }

            for(String key : level.keySet()){
                set.remove(key);
            }
            level = newLevel;
        }

        return res;
    }


    private List<String> getNei(Set<String> set, String word){
        StringBuilder sb = new StringBuilder(word);
        List<String> list = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            char t = word.charAt(i);
            for(char c = 'a'; c <= 'z'; c++){
                sb.setCharAt(i, c);
                if(!sb.toString().equals(word) && set.contains(sb.toString())){
                    list.add(sb.toString());
                }
            }
            sb.setCharAt(i, t);
        }
        return list;
    }


    //!!!!2.0 bfs + dfs, acwing, O(mn), O(mn)
    //m: length of word, n: length of wordList
    /*
    * 两种比较构建图的方式
    * 1. 两两比较字典里的单词看是不是通过一个字母转换得: m*n^2
    * 2. 一个单词更换每一位上的字母【a - z】查dict: 26mn
    * mn^2 >= 26mn => n >=26 第二种方法更好
    *
    * */
    public List<List<String>> findLadders3(String begin, String end, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();


        Map<String, Integer> dist = new HashMap<>();//dist to start
        Deque<String> queue = new LinkedList();
        Set<String> set = new HashSet<>(wordList);

        if(!set.contains(end)){return res;}

        queue.offerFirst(begin);
        dist.put(begin, 0);
        while(!queue.isEmpty()){
            String pre = queue.pollLast();
            if(pre.equals(end)){
                break;
            }
            StringBuilder sb = new StringBuilder(pre);
            for(int i = 0; i < sb.length(); i++){
                char c = sb.charAt(i);
                for(char t = 'a'; t <= 'z'; t++){
                    sb.setCharAt(i, t);
                    String str = sb.toString();
                    if(set.contains(str) && !dist.containsKey(str)){
                        dist.put(str, dist.get(pre) + 1);
                        queue.offerFirst(str);
                    }
                }
                sb.setCharAt(i, c);
            }
        }


        List<String> list = new ArrayList<>();
        list.add(end);
        set.add(begin);
        dfs3(begin, end, res, dist, list, set);

        return res;


    }

    private void dfs3(String begin, String cur, List<List<String>> res, Map<String, Integer> dist, List<String> list, Set<String> set){
        if(cur.equals(begin)){
            List<String> copy = new ArrayList(list);
            Collections.reverse(copy);
            res.add(copy);
            return;
        }
        StringBuilder sb = new StringBuilder(cur);
        for(int i = 0; i < cur.length(); i++){
            char c = sb.charAt(i);
            for(char t = 'a'; t <= 'z'; t++){
                sb.setCharAt(i, t);
                String str = sb.toString();
                //!!!some nodes even doesn't have dist e.e.  hot-> dot-> dog begin = hot end = dot bfs stops at dot. dog will not has dist
                if(set.contains(str) && dist.containsKey(str) && dist.get(str) + 1 == dist.get(cur)){
                    list.add(str);
                    dfs3(begin, str, res, dist, list, set);
                    list.remove(str);
                }
            }
            sb.setCharAt(i, c);
        }

    }



    // Sol1: from Lai:way 1: 定义两个数据结构，一个存每个点的neighbor（NeiFinder）, 另一个存每个点的前序(Tracer)
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



    //Sol 1.1: 不用辅助类：
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if(wordList.indexOf(endWord) == -1){
            return res;
        }
        Map<String, List<String>> neis = new HashMap<>();
        Map<String, List<String>> preds = new HashMap<>();
        Map<String, Integer> steps = new HashMap<>();
        if(wordList.indexOf(beginWord) == -1){
            wordList.add(beginWord);
        }
        for(String str : wordList){
            buildNei(str, neis, wordList);
            preds.put(str, new ArrayList<String>());
            steps.put(str, -1);
        }

        Deque<String> queue = new LinkedList<>();
        queue.offerFirst(beginWord);
        steps.put(beginWord, 0);
        while(!queue.isEmpty()){
            //expand
            String tmp = queue.pollLast();
            if(tmp.equals(endWord)){
                List<String> list = new ArrayList<>();
                list.add(endWord);
                dfs(res, beginWord, endWord, list, preds);
                break;
            }
            //generate
            for(String nei : neis.get(tmp)){
                //de-dup
                if(steps.get(nei) == -1){
                    //1.en-queue
                    queue.offerFirst(nei);
                    //3.update steps
                    steps.put(nei, steps.get(tmp) + 1);

                }
                if(steps.get(nei) == steps.get(tmp) + 1){
                    //2.update preds
                    preds.get(nei).add(tmp);
                }
            }
        }

        return res;

    }

    private void dfs(List<List<String>> res, String begin, String end, List<String> list, Map<String, List<String>> preds){
        if(begin.equals(end)){ //这个地方第一次做错了，直接Collections.reverse(list) 然后把list 加在result里了， 这种层与层之间传递的变量不能破坏，加入结果要new 一个新的， deep copy
            List<String> tmp = new ArrayList<>(list);
            Collections.reverse(tmp);
            res.add(tmp);
            return;
        }
        for(String str : preds.get(end)){
            list.add(str);
            dfs(res, begin, str, list, preds);
            list.remove(list.size() - 1);
        }
    }

    private void buildNei(String word, Map<String, List<String>> neis, List<String> wordList){
        List<String> res = new ArrayList<>();
        neis.put(word, res);
        for(int i = 0; i < word.length(); i++){
            StringBuilder sb = new StringBuilder(word);
            char orig = word.charAt(i);
            for(char c = 'a'; c <= 'z'; c++){
                if(c == orig){
                    continue;
                }
                sb.setCharAt(i, c);
                if(wordList.indexOf(sb.toString()) != -1){
                    neis.get(word).add(sb.toString());
                }
            }
            sb.setCharAt(i, orig);

        }

    }


}
