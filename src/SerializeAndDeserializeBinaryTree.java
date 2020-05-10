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



    //way1: My way. use preorder and inorder to deserialize  这种做法是不需要记录空节点的，只记录有值的节点，所以需要另个方向的遍历来帮助确定null叶子节点的位子

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


    /*
                    1
                   /
                  2
                /   \
               3     4

                 1 2 3 # #  4 # # #

*/
    //第二种和第三种解法的奥义是需要记录叶子节点为null的层，即一个node的两个子节点都是null, 那么null 是叶子节点，而不是有值的node本身
    //T: O(N)
    //S: O(h) 如果算返回的String的话空间复杂度是O(N)
    public String serialize2(TreeNode root) {
        //use pre order but all the null leaf nodes as #
        StringBuilder sb = new StringBuilder();
        preOrderSer(root, sb);
        return sb.toString();

    }

    private void preOrderSer(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append("#,");
            return;
        }
        sb.append(root.key + ",");

        preOrderSer(root.left, sb);
        preOrderSer(root.right, sb);
        return;
    }
    //T: O(N)
    //S: O(h) 如果算返回的String的话空间复杂度是O(N)
    public TreeNode deserialize2(String data) {
        String[] dataAry = data.split(",");
        //List interface have remove() as optional operation i.e.
        //That an interface method is specified as optional in the JavaDoc
        //means that classes implementing this interface does not necessarily have to implement that method. Instead, they could for example, throw an exception.
        List<String> dataList = new LinkedList(Arrays.asList(dataAry));
        return preOrderDe(dataList);

    }

    private TreeNode preOrderDe(List<String> list){
        if(list.get(0).equals("#")){
            list.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
        list.remove(0);
        root.left = preOrderDe(list);
        root.right = preOrderDe(list);

        return root;


    }


    //way 3: level order traversal
    //T: O(N)
    //S: O(N)
    public String serialize3(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerFirst(root);
        while(!queue.isEmpty()){
            TreeNode tmp = queue.pollLast();
            if(tmp == null) {
                sb.append("#,");
            }else{
                sb.append(tmp.key + ",");
                queue.offerFirst(tmp.left);
                queue.offerFirst(tmp.right);

            }
        }
        return sb.toString();


    }

    //T: O(N)
    //S: O(N)
    public TreeNode deserialize3(String data) {
        String[] dataAry = data.split(",");
        List<String> dataList = new LinkedList<>(Arrays.asList(dataAry));

        TreeNode root = dataList.get(0).equals("#") ? null : new TreeNode(Integer.valueOf(dataList.get(0)));
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerFirst(root);
        dataList.remove(0);
        while(!queue.isEmpty() && dataList.size() > 0){
            TreeNode curRoot = queue.pollLast();
            //这里注意，只有不是null 的节点才有探索其左右节点的意义。如果poll出来的是null, 则其左右子节点没有在serialize的结果中记录，所以直接跳过
            /**
             * eg.
             *         1
             *      |     \
             *     2       #
             *    / \
             *   3  4
             *  /\  /\
             * # # # #
             *
             *
             * level order serialization 之后的结果： 1， 2， #， 3， 4， #， #， #， #，
             *  deserialize 时候 ， index = 2 位置这个#， 他的两个子节点也是#和#， 但是serialized之后的结果没有记录空节点下一层了，这也就意味着queue里如果pop出空节点，直接从list里删掉就好，不会影响后面deserialize结果
             *
             *
             *
             */

            if(curRoot == null){
                continue;
            }
            TreeNode left = dataList.get(0).equals("#") ? null : new TreeNode(Integer.valueOf(dataList.get(0)));
            queue.offerFirst(left);
            dataList.remove(0);
            TreeNode right = dataList.get(0).equals("#") ? null : new TreeNode(Integer.valueOf(dataList.get(0)));
            queue.offerFirst(right);
            dataList.remove(0);
            curRoot.left = left;
            curRoot.right = right;
        }
        return root;

    }





}
