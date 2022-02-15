package nodes;

public class Node {
    public Node left;
    public Node right;
    public int data;

    public Node() {
    }

    public Node(Node left, Node right, int data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }
}
