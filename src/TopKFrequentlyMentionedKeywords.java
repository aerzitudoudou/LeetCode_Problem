import java.util.*;

public class TopKFrequentlyMentionedKeywords {
    //way1:
    public List<String> TopkKeywords(int K, String[] keywords, String[] reviews) {
        List<String> res = new ArrayList<>();
        //convert reviews into list of sets of words
        List<Set<String>> reviewList = convert(reviews);

        PriorityQueue<WordCount> pq = new PriorityQueue<>();
        //find number of counts for each word, put into pq
        for(int i = 0; i < keywords.length; i++){
            int count = 0;
            for(Set review : reviewList){
                if(review.contains(keywords[i].toUpperCase())){
                    count++;
                }
            }
            pq.offer(new WordCount(keywords[i], count));
        }

        //pop pq for k times. if k > pq size, append with ""
        while(pq.size() > 0 && K > 0){
            res.add(pq.poll().word);
            K--;
        }

        return res;
    }

    //list of set of words in each review, all to uppercase
    private List<Set<String>> convert(String[] reviews){
        List<Set<String>> list = new ArrayList<>();
        for(int i = 0; i < reviews.length; i++){
            Set<String> set = new HashSet<>();
            String str = reviews[i].toUpperCase();

            str.replaceAll("[^A-Z]", " ");
            String[] ary = str.split("\\s+");
            for(int j = 0; j < ary.length; j++){
                set.add(ary[j]);
            }
            list.add(set);

        }
        return list;
    }



    public static class WordCount implements Comparable<WordCount>{
        private String word;
        private int count;

        public WordCount(String word, int count){
            this.word = word;
            this.count = count;
        }
        @Override
        public int compareTo(WordCount wordCount){
            if(this.count == wordCount.count){
                return this.word.compareTo(wordCount.word);
            }
            return this.count > wordCount.count ? -1 : 1;
        }


    }









    //////////////////////////////////////////////////
    public List<String> TopkKeywords2(int K, String[] keywords, String[] reviews) {
        List<String> res = new ArrayList<>();
        //convert reviews into list of sets of words

        PriorityQueue<WordCount> pq = new PriorityQueue<>();
        //find number of counts for each word, put into pq
        for(int i = 0; i < keywords.length; i++){
            int count = 0;
            for(int j = 0; j < reviews.length; j++){
                if(reviews[j].toUpperCase().contains(keywords[i].toUpperCase())){ //TODO: 为什么直接uppercase 过不了 查一下\\w+是什么
                    count++;
                }
            }
            pq.offer(new WordCount(keywords[i], count));
        }

        //pop pq for k times. if k > pq size, append with ""
        while(pq.size() > 0 && K > 0){
            res.add(pq.poll().word);
            K--;
        }

        return res;
    }





}
