package nodes;


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
// todo have to work on this
   public LinkedNode remove(int index) {
      if (size - 1 < index) {
         System.out.println("out of bound");
      } else if (index == 0) {
         pop();
      } else {
         int count = size - 1;
         Node temp = root;
         while (count - 1 != index) {
            count--;
            temp = temp.next;
         }
         temp.next = temp.next.next;
//         root = temp;
         size--;
      }
      return this;
   }

   public LinkedNode pop() {
      if (size == 0) System.out.println("can't operate on empty list");
      else {
         root = root.next;
         size--;
      }

      return this;
   }

   public LinkedNode addFirst(int data) {
      root = new Node(data, root);
      size++;
      return this;
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
      for (int i = 1; i < arr.length; i++) {
         temp.next = new Node(arr[i]);
         temp = temp.next;
         size++;
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

   public int size() {
      return size;
   }

}
