package nodes;

public class MyLinkedList {

   public static void main(String[] args) {
      LinkedNode root = generateList(new int[]{1});
      printList(addFirst(root, 6));

   }

   private static LinkedNode addFirst(LinkedNode root, int data) {
      if (root == null) return new LinkedNode(data);
      LinkedNode temp = root.next;
      root.next = new LinkedNode(data, temp);
      return root;
   }

   private static LinkedNode addLast(LinkedNode root, int data) {
      LinkedNode temp = root;
      while (temp.next != null) {
         temp = temp.next;
      }
      temp.next = new LinkedNode(data);

      return root;
   }

   private static void printList(LinkedNode root) {
      while (root != null) {
         System.out.print(root.data + " ");
         root = root.next;
      }
   }

   private static LinkedNode generateList(int[] arr) {
      LinkedNode root = new LinkedNode(arr[0]);
      LinkedNode temp = root;
      for (int i = 1; i < arr.length; i++) {
         temp.next = new LinkedNode(arr[i]);
         temp = temp.next;
      }
      return root;
   }
}
