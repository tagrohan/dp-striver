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

//            0
//        /      \
//     1            1
//   /            /   \
// 2             3     2
//                       \
//                        3

//        heightOfTree();
        System.out.println(heightOfTreeV2(root));
    }

    private static int heightOfTreeV2(Node root) {
//        System.out.println(heightOfTreeV2(root));
        if (root == null) {
            return 0;
        }
        int left = heightOfTreeV2(root.left);
        int right = heightOfTreeV2(root.right);
        return 1 + Math.max(left, right);
    }

    private static int heightOfTree(Node root, int height) {
//        System.out.println(heightOfTree(root, 0));
        if (root == null) return height;
        return Math.max(heightOfTree(root.left, height + 1), heightOfTree(root.right, height + 1));
    }
}
