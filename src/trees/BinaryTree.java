package trees;

public class BinaryTree {

   static class Node {
      int data;
      Node left;
      Node right;

      public Node(int data) {
         this.data = data;
         this.right = this.left = null;
      }
   }

   static class Pair {
      Node node;
      int state;

      public Pair(Node node, int state) {
         this.node = node;
         this.state = state;
      }
   }

   private void createTree(Integer[] arr) {

   }

}
