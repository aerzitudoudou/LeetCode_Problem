public class SerializeAndDeserializeBinaryTree_LC297 {

    // Encodes a tree to a single string.
    //pre-sum
    //O(n) O(h)
    public String serialize(TreeNode root) {

        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sb.toString();

    }

    private void helper(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append("#");
            sb.append(",");
            return;
        }
        sb.append(root.key);
        sb.append(",");
        helper(root.left, sb);
        helper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    //O(n) O(n)
    public TreeNode deserialize(String data) {
        String[] dataAry = data.split(",");
        int[] index = new int[1]; //can't be int. int will be copy of value, not the reference of the value.
        return construct(dataAry, index);

    }

    private TreeNode construct(String[] dataAry, int[] index){
        String curStr = dataAry[index[0]];
        index[0]++;

        if(curStr.equals("#")){
            return null;
        }

        TreeNode cur = new TreeNode(Integer.parseInt(curStr));
        cur.left = construct(dataAry, index);
        cur.right = construct(dataAry, index);
        return cur;



    }


//      private TreeNode construct(String[] dataAry, int[] index){
//         TreeNode cur;
//         String str = dataAry[index[0]++];

//         if(!str.equals("#")){

//             int curVal = Integer.parseInt(str);
//             cur = new TreeNode(curVal);
//             cur.left = construct(dataAry, index);
//             cur.right = construct(dataAry, index);
//         }else{
//             cur = null;
//         }



//         return cur;


//     }




}
