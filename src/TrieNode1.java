import java.util.HashMap;
import java.util.Map;

//按一个set做，节点只考虑是不是单词，不做word --> value 的map
public class TrieNode1 {
    Map<Character, TrieNode1> children = new HashMap<>();
    boolean isWord = false;
    int count = 0; //已该节点为根节点的子树上，包括根节点，包含的单词的个数

    public TrieNode1(){
    }

    public TrieNode1(boolean isWord){
        this.isWord = isWord;
    }

    public TrieNode1(int count){
        this.count = count;
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
    //2. insert
    //way1 : my way
    public boolean insert(String word, TrieNode1 root){
        TrieNode1 cur = root;
        boolean res = false;
        for(int i = 0; i < word.length(); i++){
            TrieNode1 next = cur.children.get(word.charAt(i));
            if(next == null){
                res  = true;
                TrieNode1 node;
                if(i != word.length() - 1){
                    node = new TrieNode1(false);
                    cur.children.put(word.charAt(i), node);
                }else{
                    node = new TrieNode1(true);
                    cur.children.put(word.charAt(i), node);
                }
                cur = node;

            }else{
                cur = next;
            }
            cur.count++;
        }
        return res;

    }

    //way2 : 教案上的解法
    //T: O(M)
    public boolean insert1(String word, TrieNode1 root){
        if(search(word, root)){
            return false;
        }
        TrieNode1 cur = root;
        cur.count++;  //是不是还应该加上这一句？-->是的 使count始终满足它的物理意义呢？--> confirmed. 需要。这样才能符合物理意义。
        TrieNode1 next;
        for(int i = 0; i < word.length(); i++){
            next = root.children.get(word.charAt(i));
            if(next == null){
                next = new TrieNode1();
                cur.children.put(word.charAt(i), next);
            }

            cur = next;
            cur.count++;
        }
        cur.isWord = true;
        return true;
    }



    //3.delete
    //T: O(M), M is length of word
    public boolean delete(String word, TrieNode1 root){
        if(!search(word, root)){
            return false;
        }
        TrieNode1 cur = root;
        cur.count--;
        TrieNode1 next;
        for(int i = 0; i < word.length(); i++){
            next = root.children.get(word.charAt(i));
            //单词一直到底的情况, 最后非公共部分，需要直接拿掉
            if(next.count == 1){
                cur.children.remove(word.charAt(i));
                return true;
            }
            cur = next;
            cur.count--;
        }
        //单词是中间某一段的情况 - 虽然node还在，需要改变node的性质成非单词节点
        cur.isWord = false;
        return true;

    }





}
