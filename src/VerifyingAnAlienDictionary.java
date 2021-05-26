public class VerifyingAnAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        /*for each two adjancent word
        compare char from each word one by one.
            if char1 > char2 return 1
            if every char is equal till one word's end, and word1.length > word2.length return 1
            else return -1
        */
        //map to save the order index
        int[] orderMap = new int[26];
        for(int i = 0; i < order.length(); i++){
            orderMap[order.charAt(i) - 'a'] = i;
        }
        for(int i = 0; i < words.length - 1; i++){
            String word1 = words[i], word2 = words[i + 1];
            if(compare(word1, word2, orderMap) > 0) return false;

        }
        return true;
    }

    private int compare(String word1, String word2, int[] orderMap){
        int i = 0, j = 0;
        int res = 0;
        while(i < word1.length() && j < word2.length() && res == 0){ //only continue comparing if the two letters are the same!!!
            res = orderMap[word1.charAt(i) - 'a'] - orderMap[word2.charAt(j) - 'a'];
            i++;
            j++;
        }
        if(res == 0 ) res = word1.length() - word2.length();

        return res;

    }
}
