public class LowestCommonAncestorOfaBinaryTreeIII {
    public class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    //O(h) worst case t is O(n) if it's a linkedlist, s: O(1) from xiaoganluo: https://www.youtube.com/watch?v=31KtJn5IS9Q
        public Node lowestCommonAncestor(Node p, Node q) {
            Node p1 = p, q1 = q;
            while (p1 != q1) {
                p1 = p1.parent;
                q1 = q1.parent;
                if (p1 == null) p1 = q;
                if (q1 == null) q1 = p;

            }
            return p1;
        }

}
