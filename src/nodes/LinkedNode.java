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

      public Node(int data) {
         this.data = data;
      }
   }

   public LinkedNode() {
      size = 0;
   }

   public LinkedNode add(int data) {
      if (size == 0) {
         root = new Node(data, null);
      } else {
         Node temp = root;
         while (temp.next != null) {
            temp = temp.next;
         }
         temp.next = new Node(data);
      }
      size++;
      return this;
   }

   public LinkedNode addAlL(int... arr) {
      if (size == 0) {
         root = new Node(arr[0], null);
         size++;
      }
      Node temp = root;
      while (temp.next != null) temp = temp.next;
      for (int data : arr) {
         temp.next = new Node(data);
         temp = temp.next;
      }
      return this;
   }

   public void print() {
      Node temp = root;
      while (temp != null) {
         System.out.print(temp.data + " ");
         temp = temp.next;
      }
   }

}
