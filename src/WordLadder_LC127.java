import java.util.*;

public class WordLadder_LC127 {

    //!!!!!!sol2.1 bi-directional bfs
    // O(26 * m * n) = O(m * n) , O(m*n)
    //m: avg word length, n: dict length
    public int ladderLength(String begin, String end, List<String> dict) {
        Deque<String> q1 = new LinkedList<>(), q2 = new LinkedList<>();
        Set<String> s1 = new HashSet<>(), s2 = new HashSet<>(), dictSet = new HashSet<>(dict);

        if(!dictSet.contains(end)) return 0;
        int level = 1;
        q1.offerFirst(begin); s1.add(begin);
        q2.offerFirst(end); s2.add(end);
        while(!q1.isEmpty()){
            //q1.size <= q2.size
            int size = q1.size();
            for(int i = 0; i < size; i++){
                String str = q1.pollLast();
                if(s2.contains(str)) return level;

                StringBuilder sb = new StringBuilder(str);
                for(int j = 0; j < sb.length(); j++){
                    char c = sb.charAt(j);
                    for(char t = 'a'; t <= 'z'; t++){
                        sb.setCharAt(j, t);
                        String cur = sb.toString();
                        if(dictSet.contains(cur) && !s1.contains(cur)){
                            q1.offerFirst(cur);
                            s1.add(cur);
                        }
                    }
                    //set char back
                    sb.setCharAt(j, c);

                }
            }

            if(q1.size() > q2.size()){ //optimization: always use queue with less nodes to expand
                Deque<String> tmpQ = q1;
                q1 = q2;
                q2 = tmpQ;

                Set<String> tmpSet = s1;
                s1 = s2;
                s2 = tmpSet;
            }


            level++;
        }

        return 0;


    }


    //!!!!!sol2.0, my, bfs, O(26 * m * n) = O(m * n) , O(m*n)
    //m: avg word length, n: dict length

    public int ladderLength2(String begin, String end, List<String> dict) {
        Set<String> set = new HashSet<>();
        Set<String> dictSet = new HashSet<>(dict);
        Deque<String> queue = new LinkedList<>();
        int level = 1;
        queue.offerFirst(begin);
        set.add(begin);

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i = 0 ; i < size; i++){
                String cur = queue.pollLast();
                if(cur.equals(end)){
                    return level;
                }
                for(String str : findNei(cur, dictSet)){
                    if(!set.contains(str)){
                        queue.offerFirst(str);
                        set.add(str);
                    }
                }
            }
            level++;

        }
        return 0;
    }

    private List<String> findNei(String cur,  Set<String> set){
        List<String> res = new ArrayList<>();
        for(int i = 0; i < cur.length(); i++){
            char c = cur.charAt(i);
            StringBuilder sb = new StringBuilder(cur);
            for(char t = 'a'; t <= 'z'; t++){
                sb.setCharAt(i, t);
                if(set.contains(sb.toString())){
                    res.add(sb.toString());
                }
            }
            sb.setCharAt(i, c);
        }
        return res;
    }

    //sol1:original version from lai
    //T: wordlist一共有N个单词, 每个单词平均长度是M， 单词的每个char做了check, T = 26 * M * N
    //S: O(MN) set里最后有N个单词

    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
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
                wordIndex.put(wordList.get(i), i); //N次
            }
            this.wordList = wordList;

        }


        public List<Integer> findNei(int i){
            List<Integer> neighbors = new ArrayList<>();
            StringBuilder word = new StringBuilder(wordList.get(i));
            for(int k = 0; k < word.length(); k++){//T: 长度为M forloop 总的时间复杂度为M * 26
                char orig = word.charAt(k);
                for(char c = 'a'; c <= 'z'; c++){ //T: 26
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
