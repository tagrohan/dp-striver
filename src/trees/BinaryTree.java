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
      createTree(arr);
      printRecursively(root);
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

}
