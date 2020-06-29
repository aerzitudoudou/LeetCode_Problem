/*
*
*
* Trie data structure
*
*
*
*
*
*
* */


public class TrieNode2 {
    TrieNode2[] children;
    boolean isWord;
    int value;

    public TrieNode2(){
        children = new TrieNode2[26];
    }

    //trie operations
    //1.search
    public boolean search(String word, TrieNode2 root){
        TrieNode2 cur = root;
        for(int i = 0; i < word.length(); i++){
            TrieNode2 next = cur.children[word.charAt(i) - 'a'];
            if(next == null){
                return false;
            }
            cur = next;
        }
        //能找到但是不是字典里的一个单词
        return cur.isWord;

    }
}
