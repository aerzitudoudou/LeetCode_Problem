/*
*
*
*
*
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
//TODO: summary time complexity, and compare with pure dfs algorithm
public class WordSearchII {
    private static class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
        //search
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
        List<String> list = new ArrayList(); //todo: check see if there's better implementation
        list.addAll(res);
        return list;
    }


    //build trie tree
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
    private void helper(int x, int y, boolean visited[][], char[][] board, TrieNode root, Set<String> res, StringBuilder sb){
        int[] dirX = {0, 1, 0, -1};
        int[] dirY = {1, 0, -1, 0};


        //base case 1: x, y 越界 或已经被探测过
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]){
            return;
        }



        char c = board[x][y];

        //base case 2: trienode 的孩子没有(x,y) 对应的值
        if(root.children[c - 'a'] == null){
            return;
        }


        root = root.children[c - 'a'];//todo: 为什么root 不会回溯？


        //recursion rule:
        sb.append(board[x][y]);
        //!!!!!!!!!注意加結果的地方。todo: summary why here
        if(root.isWord){
            res.add(sb.toString());
        }
        visited[x][y] = true;
        for(int i = 0; i < 4; i++){
            int a = x + dirX[i];
            int b = y + dirY[i];
            helper(a, b, visited, board, root, res, sb);
        }
        visited[x][y] = false;
        sb.deleteCharAt(sb.length() - 1);



    }
}
