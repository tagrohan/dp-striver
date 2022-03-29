package trees;

import java.util.Stack;

public class BinaryTree {
   private static Node root;

   static class Node {
      int data;
      Node left;
      Node right;

      public Node(int data) {
         this.data = data;
         this.right = this.left = null;
      }
   }

   // states will be -1,0,1
   static class Pair {
      Node node;
      int state;

      public Pair(Node node, int state) {
         this.node = node;
         this.state = state;
      }
   }

   public static void main(String[] args) {
      Integer[] arr = new Integer[]{50, 25, 12, null, null, 37, 30,
           null, null, null, 75, 62, null, 70, null, null, 87, null, null};
      Integer[] arr2 = new Integer[]{1, 1, 1, null, null, 1, 1,
           null, null, null, 1, 1, null, 1, null, null, 1, null, null};
      createTree(arr);
      System.out.println(maxNode(root));
   }

   private static int maxNode(Node root) {
      if (root == null) return Integer.MIN_VALUE;

      int left = maxNode(root.left);
      int right = maxNode(root.right);
      return Integer.max(Integer.max(left, right), root.data); // same for min return max then here Integer.min();
   }

   private static int sumOfAllNodes(Node root) {
      if (root == null) return 0;
      int left = sumOfAllNodes(root.left);
      int right = sumOfAllNodes(root.right);
      return left + right + root.data;
   }


   //   Integer[] arr = new Integer[]{50, 25, 12, null, null, 37, 30,
//        null, null, null, 75, 62, null, 70, null, null, 57, null, null};
   private static void createTree(Integer[] arr) {
      Stack<Pair> stack = new Stack<>();
      stack.push(new Pair(new Node(arr[0]), -1));
      int index = 0;
      root = stack.peek().node; // root here
      while (!stack.isEmpty()) {
         Pair pair = stack.peek();
         if (pair.state == -1) {
            index++;
            pair.state += 1;
            if (arr[index] == null) {
               pair.node.left = null;
               continue;
            }// if null do nothing
            Node node = new Node(arr[index]);
            pair.node.left = node;
            stack.push(new Pair(node, -1));
         } else if (pair.state == 0) {
            index++;
            pair.state += 1;
            if (arr[index] == null) {
               pair.node.right = null;
               continue;
            }
            Node node = new Node(arr[index]);
            pair.node.right = node;
            stack.push(new Pair(node, -1));
         } else stack.pop();
      }
   }


   private static void printRecursively(Node node) {
      if (node == null) return;
      String branches = (node.left != null ? node.left.data : "") + " <- " +
           node.data + " -> " + (node.right != null ? node.right.data : "");
      System.out.println(branches);
      printRecursively(node.left);
      printRecursively(node.right);

   }

   private static void print(Node root) {
      Stack<Node> stack = new Stack<>();
      stack.push(root);
      while (!stack.isEmpty()) {
         Node node = stack.pop();
         String branches = node.data + "";
         if (node.right != null) {
            stack.push(node.right);
            branches = branches + "->" + node.right.data;
         } else branches = branches + "->";
         if (node.left != null) {
            stack.push(node.left);
            branches = node.left.data + "<-" + branches;
         } else branches = "<-" + branches;
         System.out.println(branches);
      }
   }
}
