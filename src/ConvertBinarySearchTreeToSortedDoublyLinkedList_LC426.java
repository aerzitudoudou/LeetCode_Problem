public class ConvertBinarySearchTreeToSortedDoublyLinkedList_LC426 {
    //make class variable to sure pre and head always get updated by dereference "this" object throughout the call stack


    /*
    *
    * Dereferencing in Java
Dereferencing happens using the . operator:

Object object = new Object();

String text = object.toString(); // 'object' is dereferenced.
Dereferencing pursues the memory address placed in a reference, to the place in memory where the actual object is. When an object has been found, the requested method is called; if the reference has the value null, dereferencing results in a NullPointerException:

Object obj = null;

obj.toString(); // Throws a NullpointerException when this statement is executed.
Null indicates the absence of a value, so there is no object on which the requested method can be called.
    *
    *
    *
    *
    * */
    // int a = 0; for primitive & change of reference instead of de-reference of the object reference is pointing to, make them either global or put them in a container class

    //Sol1
    //O(n), O(h)
    public Node treeToDoublyList(Node root) {
        Node[] head = new Node[1];
        Node[] pre = new Node[1];
//     StringBuilder sb = new StringBuilder();

//     sb  --> object       head --> object

//     sb1                  head1 = root is not change the original object head is pointing to
//     sb.append(a)         head1.val = 3  de-reference head and change value in the node

//      int[] a
//         a[0] is like a.0 =
        dfs(root, head, pre);
        if(head[0] == null) return head[0];


        head[0].left = pre[0];
        pre[0].right = head[0];
        return head[0];


    }
    private void dfs(Node root, Node[] head, Node[] pre){

        if(root == null) return;
        dfs(root.left, head, pre);
        //do something on the current level
        if(head[0] == null){
            head[0] = root;
        }else{
            pre[0].right = root;
            root.left = pre[0];
        }
        pre[0] = root;
        dfs(root.right, head, pre);

    }


    //！！！！！sol2: make head and pre as a global variable to be able to update it in the dfs
    //O(n), O(h)
//      Node head = null, pre = null;
//      public Node treeToDoublyList(Node root) {
//         dfs(root);
//         if(head == null) return head;
//         head.left = pre;
//         pre.right = head;
//         return head;


//     }
//     private void dfs(Node root){
//         if(root == null) return;
//         dfs(root.left);
//         //do something on the current level
//         if(head == null){
//             head = root;
//         }else{
//             pre.right = root;
//             root.left = pre;
//         }
//         pre = root;
//         dfs(root.right);

//     }



}
