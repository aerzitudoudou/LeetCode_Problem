package amazonOA;

import java.util.*;
import java.util.stream.Collectors;

/*
* Given a list of reviews, a list of keywords and an integer k. Find the most popular k keywords in order of most to least frequently mentioned.

The comparison of strings is case-insensitive.
Multiple occurances of a keyword in a review should be considred as a single mention.
If keywords are mentioned an equal number of times in reviews, sort alphabetically.

Example 1:

Input:
k = 2
keywords = ["anacell", "cetracular", "betacellular"]
reviews = [
  "Anacell provides the best services in the city",
  "betacellular has awesome services",
  "Best services provided by anacell, everyone should use anacell",
]

Output:
["anacell", "betacellular"]

Explanation:
"anacell" is occuring in 2 different reviews and "betacellular" is only occuring in 1 review.
Example 2:

Input:
k = 2
keywords = ["anacell", "betacellular", "cetracular", "deltacellular", "eurocell"]
reviews = [
  "I love anacell Best services; Best services provided by anacell",
  "betacellular has great services",
  "deltacellular provides much better services than betacellular",
  "cetracular is worse than anacell",
  "Betacellular is better than deltacellular.",
]

Output:
["betacellular", "anacell"]

Explanation:
"betacellular" is occuring in 3 different reviews. "anacell" and "deltacellular" are occuring in 2 reviews, but "anacell" is lexicographically smaller.
* */
public class TopKFrequentlyMentionedWords {
    //  M: average length of word
    //  N: length of review array
    //  L: length of keywords array
    //T: O(L * ((N * M) + log(L)))   S: O(L * M + N * M)
    public List<String> TopkKeywords(int K, String[] keywords, String[] reviews) {
        List<String> res = new ArrayList<>();
        //corner case check
        if(keywords == null || reviews == null || keywords.length == 0 || reviews.length == 0 || K <= 0){
            return res;
        }
        //convert reviews into list of sets of words
        //T: O(m * n) S:O(N * M)
        List<Set<String>> reviewList = convert(reviews);

        //S: O(L * M)
        PriorityQueue<WordCount> pq = new PriorityQueue<>();
        //find number of counts for each word, put into pq
        //T: O(L * ((N * M) + log(L)))
        for (int i = 0; i < keywords.length; i++) {
            int count = 0;
            //O(N * M)
            for (Set review : reviewList) {
                if (review.contains(keywords[i])) {
                    count++;
                }
            }
            //T:O(logL)
            pq.offer(new WordCount(keywords[i], count));
        }

        //pop pq for k times.
        //O(K * logL)
        while (pq.size() > 0 && K > 0) {
            res.add(pq.poll().word);
            K--;
        }

        return res;
    }

    //list of set of words in each review, all to uppercase

    //O(m * n)
    private List<Set<String>> convert(String[] reviews) {
        List<Set<String>> list = new ArrayList<>();
        for(int i = 0; i < reviews.length; i++){
            Set<String> set = new HashSet<>();
            String[] ary = reviews[i].replaceAll("\\W+", " ").split("\\s+");
            for(String str : ary){
                set.add(str.toLowerCase());
            }
            list.add(set);

        }
        return list;
    }


    public static class WordCount implements Comparable<WordCount> {
        private String word;
        private int count;

        public WordCount(String word, int count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public int compareTo(WordCount wordCount) {
            if (this.count == wordCount.count) {
                return this.word.compareTo(wordCount.word);
            }
            return this.count > wordCount.count ? -1 : 1;
        }
    }




}
