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


    //!!Sol1, my, O(n), O(h)
    Node head = null; //global head of the half before current root
    Node tail = null; //global tail of the half before current root
    public Node treeToDoublyList(Node root) {
        if(root == null) return null;
        dfs(root);
        head.left = tail;
        tail.right = head;
        return head;

    }

    //BST, in-order traverse to make the result ordered
    private void dfs(Node root){
        if(root == null) return;

        dfs(root.left);
        if(head == null) head = root;//bottom left node. i.e. smallest node
        else{
            root.left = tail;
            tail.right = root;
        }
        tail = root;
        dfs(root.right);

    }



}
