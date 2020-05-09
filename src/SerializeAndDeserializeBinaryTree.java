import java.util.*;

/*
* Lintcode 7
*
* 7. Serialize and Deserialize Binary Tree
中文English
Design an algorithm and write code to serialize and deserialize a binary tree. Writing the tree to a file is called 'serialization' and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.

Example
Example 1:

Input：{3,9,20,#,#,15,7}
Output：{3,9,20,#,#,15,7}
Explanation：
Binary tree {3,9,20,#,#,15,7},  denote the following structure:
	  3
	 / \
	9  20
	  /  \
	 15   7
it will be serialized {3,9,20,#,#,15,7}
Example 2:

Input：{1,2,3}
Output：{1,2,3}
Explanation：
Binary tree {1,2,3},  denote the following structure:
   1
  / \
 2   3
it will be serialized {1,2,3}
Our data serialization use BFS traversal. This is just for when you got Wrong Answer and want to debug the input.

You can use other method to do serializaiton and deserialization.

Notice
There is no limit of how you deserialize or serialize a binary tree, LintCode will take your output of serialize as the input of deserialize, it won't check the result of serialize.
*
*
*
* */
public class SerializeAndDeserializeBinaryTree {

//    /**
//     * This method will be invoked first, you should design your own algorithm
//     * to serialize a binary tree which denote by a root node to a string which
//     * can be easily deserialized by your own "deserialize" method later.
//     */
//    public String serialize(TreeNode root) {
//        //normal level order traversal using a queue
//        StringBuilder sb = new StringBuilder();
//        Deque<TreeNode> queue = new LinkedList<>();
//        if(root == null){
//            return "";
//        }
//        queue.offerFirst(root);
//
//        while(!queue.isEmpty()){
//            TreeNode tmp = queue.pollLast();
//            if(tmp == null){
//                sb.append('#');
//            }else{
//                sb.append(tmp.key);
//                queue.offerFirst(tmp.left);
//                queue.offerFirst(tmp.right);
//            }
//            sb.append(',');
//        }
//        return sb.substring(0, sb.length() - 1).toString();
//
//
//    }
//
//    /**
//     * This method will be invoked second, the argument data is what exactly
//     * you serialized at method "serialize", that means the data is not given by
//     * system, it's given by your own serialize method. So the format of data is
//     * designed by yourself, and deserialize it here as you serialize it in
//     * "serialize" method.
//     */
//    public TreeNode deserialize(String data) {
//        if(data == ""){
//            return null;
//        }
//        //build queue containing the treenodes and order
//        Deque<TreeNode> queue = new LinkedList<>();
//        String[] datas = data.split(",");
//        for(int i = 0; i < datas.length; i++){
//            if(datas[i].equals("#")){
//                queue.offerFirst(null);
//            }else{
//                queue.offerFirst(new TreeNode(Integer.valueOf(datas[i])));
//
//            }
//        }
//        TreeNode root = queue.pollLast();
//        return helper(root, queue);
//
//
//    }
//
//
//    private TreeNode helper(TreeNode root, Deque<TreeNode> queue){
//        //queue, based on the serialized logic, is always even number
//        if(root == null || queue.isEmpty()){
//            return root;
//        }
//        TreeNode left = queue.pollLast();
//        TreeNode right = queue.pollLast();
//        root.left = helper(left, queue);
//        root.right = helper(right, queue);
//
//        return root;
//
//    }

    //way1: My way. use preorder and inorder to deserialize

    public String serialize(TreeNode root) {
        if(root == null){
            return "";
        }

        StringBuilder sb = new StringBuilder();

        Set<String> set = new HashSet<>();
        inOrderTraversal(root, sb, set);
        set = new HashSet<>();
        preOrderTraversal(root, sb, set);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();


    }

    private void inOrderTraversal(TreeNode root, StringBuilder sb, Set<String> set){
        if(root == null){
            return;
        }
        inOrderTraversal(root.left, sb, set);
        if(!set.contains(String.valueOf(root.key))){
            sb.append(String.valueOf(root.key));
            set.add(String.valueOf(root.key));
        }else{ //重复的key, e.g. 有两个点是10，第二个点用10_1 来存入返回string
            int i = 1;
            while(set.contains(root.key + "_" + i)){
                i++;
            }
            sb.append(root.key + "_" + i);
            set.add(root.key + "_" + i);
        }
        sb.append(",");
        inOrderTraversal(root.right, sb, set);
    }

    private void preOrderTraversal(TreeNode root, StringBuilder sb, Set<String> set){


        if(root == null){
            return;
        }
        if(!set.contains(String.valueOf(root.key))){
            sb.append(String.valueOf(root.key));
            set.add(String.valueOf(root.key));
        }else{
            int i = 1;
            while(set.contains(root.key + "_" + i)){
                i++;
            }
            sb.append(root.key + "_" + i);
            set.add(root.key + "_" + i);
        }
        //加“，” 分隔不同的数字，不能直接加数字因为有可能大于一位
        sb.append(",");
        preOrderTraversal(root.left, sb, set);
        preOrderTraversal(root.right, sb, set);
    }




    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if(data == ""){
            return null;
        }

        String[] array = data.split(",");
        String[] in = Arrays.copyOfRange(array, 0, array.length / 2);
        String[] pre = Arrays.copyOfRange(array, array.length / 2, array.length);

        Map<String, Integer> inMap = new HashMap<>();
        for(int i = 0; i < in.length; i++){
            inMap.put(in[i], i);
        }

        return helper(in, pre, inMap, 0, in.length - 1, 0, pre.length - 1);



    }

    private TreeNode helper(String[] in, String[] pre, Map<String, Integer> inMap, int inLeft, int inRight, int preLeft, int preRight){
        if(inLeft > inRight){
            return null;
        }
        int inMid = inMap.get(pre[preLeft]);
        //dup processing
        String tmp = pre[preLeft];
        int index = tmp.indexOf("_");

        if(index != -1){
            tmp = tmp.substring(0, index);
        }
        TreeNode cur = new TreeNode(Integer.valueOf(tmp));
        cur.left = helper(in, pre, inMap, inLeft, inMid - 1, preLeft + 1, preLeft + inMid - inLeft);
        cur.right = helper(in, pre, inMap, inMid + 1, inRight, preRight - inRight + inMid + 1, preRight);

        return cur;
    }

    //TODO: 总结更好的方法


}
