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
    int count;
    //int value;

    public TrieNode2(){
        children = new TrieNode2[26];
    }

    //trie operations
    //1.search
    //两种情况
    public boolean search(String word, TrieNode2 root){
        TrieNode2 cur = root;
        for(int i = 0; i < word.length(); i++){
            //case1: 根本找不到
            TrieNode2 next = cur.children[word.charAt(i) - 'a'];
            if(next == null){
                return false;
            }
            cur = next;
        }
        //case2: 能找到但是不是字典里的一个单词
        return cur.isWord;

    }


    //2.insert
    public boolean insert(String word, TrieNode2 root){
        if(search(word, root)){
            return false;
        }
        TrieNode2 cur = root;
        cur.count++; //TODO: 确认这个是不是应该有
        TrieNode2 next;
        for(int i = 0; i < word.length(); i++){
            next = cur.children[word.charAt(i) - 'a'];
            if(next == null){
                next = new TrieNode2();
                cur.children[word.charAt(i) - 'a'] = next;
            }
            cur = next;
            cur.count++;
        }
        cur.isWord = true;
        return true;
    }


    //3. delete
    public boolean delete(String word, TrieNode2 root){
        if(!search(word, root)){
            return false;
        }
        TrieNode2 cur = root;
        cur.count--; //todo: 确定这句是不是应该有
        TrieNode2 next;
        for(int i = 0; i < word.length(); i++){
            next = cur.children[word.charAt(i) - 'a'];
            if(next.count == 1){
                //扎到底的情况
                cur.children[word.charAt(i) - 'a'] = null;
                return true;
            }
            cur = next;
            cur.count--;

        }
        cur.isWord = false;
        return true;
    }
}
