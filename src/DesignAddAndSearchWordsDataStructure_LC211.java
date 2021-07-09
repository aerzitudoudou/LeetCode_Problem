public class DesignAddAndSearchWordsDataStructure_LC211 {
    //sol1, my
    class TrieNode{
        boolean isWord;
        TrieNode[] children = new TrieNode[26];
    }

    TrieNode root;
    /** Initialize your data structure here. */
    public DesignAddAndSearchWordsDataStructure_LC211() {
        root = new TrieNode();

    }

    //O(n) n is the length of the word, O(n)
    public void addWord(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(cur.children[c - 'a'] == null){
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;

    }


    //T: O(26^n) worst case, O(n) best case
    //S: O(n) for running search
    public boolean search(String word) {
        char[] charAry = word.toCharArray();
        boolean[] flag = new boolean[1];
        dfs(charAry, 0, root, flag);
        return flag[0];
    }

    private void dfs(char[] charAry, int index, TrieNode root, boolean[] flag){
        if(index == charAry.length){
            flag[0] = root.isWord;
            return;
        }
        char c = charAry[index];
        if(c == '.'){
            for(int i = 0; i < 26; i++){
                //!!!!!!pruning, if word has been found then return, no need to see future branching in trie tree to avoid overwrite the flag
                if(root.children[i] != null && !flag[0]){
                    dfs(charAry, index + 1, root.children[i], flag);
                }
            }
        }else{
            if(root.children[c - 'a'] != null){
                dfs(charAry, index + 1, root.children[c - 'a'], flag);
            }
        }

    }
}
