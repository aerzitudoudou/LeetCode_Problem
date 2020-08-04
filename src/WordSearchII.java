/*
*
* Laicode
*
* 431. Word Search II
Hard
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell,
where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].

Note:
You may assume that all inputs are consist of lowercase letters a-z.

*
*
* */


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
* average length of word = L
* number of words: k
* m * n matrix
*
*O(L * K +  m * n * 4^L)
*
* */
public class WordSearchII {
    private static class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
        //search
        //T: O(L)
        public boolean search(String word, TrieNode root){
            if(word == null || word.length() == 0){
                return false;
            }
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(root.children[c - 'a'] == null){
                    return false;
                }else{
                    root = root.children[c - 'a'];
                }
            }
            return root.isWord;
        }
        //O(L)
        public boolean insert(String word, TrieNode root){
            if(word == null || word.length() == 0 || search(word, root)){
                return false;
            }
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(root.children[c - 'a'] == null){
                    TrieNode node = new TrieNode();
                    root.children[c - 'a'] = node;

                }
                root = root.children[c - 'a'];

            }
            root.isWord = true;
            return true;
        }

    }

    public List<String> findWords(char[][] board, String[] words) {
        //sanity check
        if(board == null || board.length == 0 || board[0] == null || board.length == 0 || words == null || words.length == 0){
            throw new IllegalArgumentException("Invalid input!");
        }
        //build trie tree
        TrieNode root = buildTrie(words);
        int row = board.length;
        int col = board[0].length;
        Set<String> res = new HashSet<>(); //答案判重
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                boolean visited[][] = new boolean[row][col];
                StringBuilder sb = new StringBuilder();
                helper(i, j, visited, board, root, res, sb);
            }
        }
        List<String> list = new ArrayList();
        list.addAll(res);
        return list;
    }


    //build trie tree
    //O(L * K)
    private TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();
        for(String word : words){
            root.insert(word, root);
        }
        return root;
    }
    /*
    物理意义： 开闭区间，一一对应
    dfs + trie to check if the current char is valid
    visited ： 从(i, j) 到 (x, y)不包括(x, y) 走过的cell
    root ：以[从(i, j) 到(x, y) 不包括(x,y) board 上走过的cell对应的字母]为前缀所对应的trienode
    res: 结果集
    */
    //O(4 ^ L) 有L层，每层最多分出4个叉，每个recursion tree 结点做的事儿
    private void helper(int x, int y, boolean visited[][], char[][] board, TrieNode root, Set<String> res, StringBuilder sb){
        int[] dirX = {0, 1, 0, -1};
        int[] dirY = {1, 0, -1, 0};


        //base case 1: x, y 越界 或已经被探测过
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]){
            return;
        }



        char c = board[x][y];

        //base case 2: trienode 的孩子没有(x,y) 对应的值
        if(root.children[c - 'a'] == null){//O(1)
            return;
        }


        root = root.children[c - 'a'];//这里只有reference 的copy， 没有对root的de-reference 所以不用recursion之后吐出来


        //recursion rule: 这里存在对sb的de-ref 所以recursion之后需要吐出来
        sb.append(board[x][y]); //worst case 是O(L), amortized O(1) https://www.quora.com/What-is-the-complexity-of-Java-StringBuffer-append

        //!!!!!!!!!注意加結果的地方。
        if(root.isWord){
            res.add(sb.toString());
        }
        visited[x][y] = true; //visited也存在对二维数组的de-ref, 所以recursion之后要恢复回去
        for(int i = 0; i < 4; i++){
            int a = x + dirX[i];
            int b = y + dirY[i];
            helper(a, b, visited, board, root, res, sb);
        }
        visited[x][y] = false;
        sb.deleteCharAt(sb.length() - 1);
        //root 在这里为什么不需要返回之前的状态：
        /*
        * root只是表示在每一个recursion call中的当前节点，仅仅是一个reference。由于Java的pass by value的性质，
        * 每一个recursion call里面的root都是各自不相干的不同的reference。当该层return时，该层的root这个reference也就被gc回收了。
        * 而我们需要去还原visited和sb，那是因为我们所修改并不是那两个references，而是那两个references所指向的objects上面的value.
        * */



    }
}
