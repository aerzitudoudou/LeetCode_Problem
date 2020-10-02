package amazonOA;
//https://leetcode.com/discuss/interview-question/587819/amazon-interview-question-amazon-kindle-top-features

import java.util.*;



class WordCnt implements Comparable<WordCnt> {
    String word;
    int count;

    public WordCnt(String word, int count) {
        this.word = word;
        this.count = count;
    }

    @Override
    public int compareTo(WordCnt wordCount) {
        if (this.count == wordCount.count) {
            return this.word.compareTo(wordCount.word);
        }
        return this.count > wordCount.count ? -1 : 1;
    }
}

public class KindleTopFeatures {
    //  M: average length of word
    //  N: length of review array
    //  L: length of keywords array
    //T: O(L * ((N * M) + log(L)))   S: O(L * M + N * M)
    public List<String> TopkKeywords(int topFeatures, String[] possibleFeatures , String[] featureRequests) {
        List<String> res = new ArrayList<>();
        //corner case check
        if(possibleFeatures  == null || featureRequests == null || possibleFeatures .length == 0 || featureRequests.length == 0 || topFeatures <= 0){
            return res;
        }
        //convert reviews into list of sets of words
        //T: O(m * n) S:O(N * M)
        List<Set<String>> reviewList = convert(featureRequests);

        //S: O(L * M)
        PriorityQueue<WordCnt> pq = new PriorityQueue<>();
        //find number of counts for each word, put into pq
        //T: O(L * ((N * M) + log(L)))
        for (int i = 0; i < possibleFeatures .length; i++) {
            int count = 0;
            //O(N * M)
            for (Set review : reviewList) {
                if (review.contains(possibleFeatures [i])) {
                    count++;
                }
            }
            //T:O(logL)
            pq.offer(new WordCnt(possibleFeatures [i], count));
        }

        //pop pq for k times.
        //O(K * logL)
        while (pq.size() > 0 && topFeatures > 0) {
            res.add(pq.poll().word);
            topFeatures--;
        }

        return res;
    }

    //list of set of words in each review, all to uppercase

    //O(m * n)
    private List<Set<String>> convert(String[] featureRequests) {
        List<Set<String>> list = new ArrayList<>();
        for(int i = 0; i < featureRequests.length; i++){
            Set<String> set = new HashSet<>();
            String[] ary = featureRequests[i].replaceAll("\\W+", " ").split("\\s+");
            for(String str : ary){
                set.add(str.toLowerCase());
            }
            list.add(set);

        }
        return list;
    }



}
