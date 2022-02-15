package trees;


import nodes.Node;

public class BTree {

    public static void main(String[] args) {
        Node root = new Node();
        Node l1 = new Node();
        Node l2 = new Node();
        Node l3 = new Node();
        Node r1 = new Node();
        Node r2 = new Node();
        Node r3 = new Node();
        root.left = l1;
        l1.left = l2;
        root.right = r1;
        r1.right = r2;
        r1.left = l3;
//        r2.right = r3;
        System.out.println(heightOfTree(root, 0));
//            0
//        /      \
//     1            1
//   /            /   \
// 2             3     2
//                       \
//                        3

//        heightOfTree();
    }

    private static int heightOfTree(Node root, int height) {
        if (root == null) return height;
        return Math.max(heightOfTree(root.left, height + 1), heightOfTree(root.right, height + 1));
    }
}
