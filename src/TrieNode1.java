import java.util.HashMap;
import java.util.Map;

public class TrieNode1 {
    Map<Character, TrieNode1> children;
    boolean isWord;
    int value;

    public TrieNode1(){
        children = new HashMap<>();
    }

    //operation
    //1.search
    public boolean search(String word, TrieNode1 root){
        TrieNode1 cur = root;
        for(int i = 0; i < word.length(); i++){
            TrieNode1 next = cur.children.get(word.charAt(i));
            if(next == null){
                return false;
            }
            cur = next;
        }
        return cur.isWord;
    }


}
