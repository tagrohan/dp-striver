package nodes;

import java.util.LinkedList;

public class LinkedNode {
   private Node root;
   private int size;

   static class Node {
      int data;
      Node next;

      public Node(int data, Node next) {
         this.data = data;
         this.next = next;
      }

   }


   public LinkedNode add(int data) {
      Node node = new Node(data, null);
      return this;
   }

}
