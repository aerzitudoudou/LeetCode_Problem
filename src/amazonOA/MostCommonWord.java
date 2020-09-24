package amazonOA;

import java.util.*;

//leetcode
public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        String s = paragraph.toLowerCase().replaceAll("[^a-z]", " ");
        String[] words = s.split("\\s+");
        int maxCount = 0;
        String res = "";

        Set<String> bannedSet = new HashSet<>();
        for(int i = 0; i < banned.length; i++){
            bannedSet.add(banned[i]);
        }

        Map<String, Integer> wordMap = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            if(!bannedSet.contains(words[i])){
                int count = wordMap.getOrDefault(words[i], 0) + 1;
                wordMap.put(words[i], count);
                if(count > maxCount){
                    maxCount = count;
                    res = words[i];
                }

            }
        }
        return res;

    }


    //简洁版java8 code best one solution!!
    //T: O(M + N) M is the number of words in banned, N is the number of words in paragraph
    //S: O(M + N)
    public String mostCommonWord2(String paragraph, String[] banned) {
        //1. put all letters to lowercase, replace all punctuations with spaces
        String s = paragraph.toLowerCase().replaceAll("\\W+", " "); //or [^a-z]
        //2. split the string into words
        String[] words = s.split("\\s+");


        Set<String> bannedSet = new HashSet<>();
        for(int i = 0; i < banned.length; i++){
            bannedSet.add(banned[i]);
        }



        //3. count the appearance of each word that are not in the banned set
        Map<String, Integer> wordMap = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            if(!bannedSet.contains(words[i])){
                wordMap.put(words[i], wordMap.getOrDefault(words[i], 0) + 1);

            }
        }

        /*
        * Collections.max:
        *  static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) Returns the maximum element of the given collection, according to the natural ordering of its elements.
           static <T> T	max(Collection<? extends T> coll, Comparator<? super T> comp) Returns the maximum element of the given collection, according to the order induced by the specified comparator.

           Map.Entry:
           static <K,V> Comparator<Map.Entry<K,V>> comparingByKey(Comparator<? super K> cmp)
           static <K,V> Comparator<Map.Entry<K,V>> comparingByValue(Comparator<? super V> cmp)

        * */
        //return the word with highest frequency
        return Collections.max(wordMap.entrySet(), Map.Entry.comparingByValue()).getKey();



    }
}
