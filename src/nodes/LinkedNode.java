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

      public Node() {
      }
   }

   public LinkedNode() {
      size = 0;
   }

   static int lastVal = -1;

   int findInterSectionPointMain() {
      Node n1 = new Node(1);
      Node n2 = new Node(2, n1);
      Node n3 = new Node(3, n2);
      Node n4 = new Node(4, n3);
      Node n5 = new Node(5, n4);
      Node n6 = new Node(6, n5);
      Node n7 = new Node(7, n6);

      Node n14 = new Node(12, n3); // merge at n3
      Node n13 = new Node(13, n14);
      Node n12 = new Node(14, n13);
      return findInterSectionPoint(n7, 7, n12, 5);

   }

   int findInterSectionPoint(Node n1, int n1Size, Node n2, int n2Size) {

      if (n1 == null || n2 == null) return -1;

      int call;
      if (n1Size > n2Size) {
         call = findInterSectionPoint(n1.next, n1Size - 1, n2, n2Size);
      } else if (n2Size > n1Size) {
         call = findInterSectionPoint(n1, n1Size, n2.next, n2Size - 1);
      } else {
         call = findInterSectionPoint(n1.next, n1Size - 1, n2.next, n2Size - 1);
         if (n1.data == n2.data) lastVal = n1.data;
         else return n1.data;
      }
      return call;
   }

//   Node toReturn = new Node();
//   Node pointer = toReturn;
//   Node temp;
//
//   LinkedNode foldList() {
//      temp = root;
//      foldAList(temp);
//      root = pointer;
//      return this;
//   }
//
//
//   private void foldAList(Node head) {
//      if (head == null) return;
//
//      foldAList(head.next);
//      Node n1 = new Node(root.data);
//      n1.next = new Node(head.data);
//      root = root.next;
//      toReturn = n1;
//      toReturn = toReturn.next.next;
//   }

   LinkedNode sumOfTwoList(LinkedNode root2) {
      int num1 = converter(root2.root);
      int num2 = converter(root);
      int sum = num1 + num2;
      Node temp = null;
      while (sum != 0) {
         int num = sum % 10;
         sum = sum / 10;
         Node node = new Node(num);
         node.next = temp;
         temp = node;
      }
      root = temp;
      return this;
   }

   private int converter(Node node) {
      StringBuilder str = new StringBuilder();
      while (node != null) {
         str.append(node.data);
         node = node.next;
      }
      return Integer.parseInt(str.toString());
   }


   Node palindromeNode;

   boolean isPalindrome() {
      palindromeNode = root;
      return isPalindromeHelper(this.root);
   }

   private boolean isPalindromeHelper(Node root) {
      if (root == null) return true;
      boolean isPalindrome = isPalindromeHelper(root.next);
      if (isPalindrome) {
         if (root.data == palindromeNode.data) {
            palindromeNode = palindromeNode.next;
            return true;
         } else
            return false;
      }
      return false;
   }


   void separateOddEven() { // odd first
      Node even = new Node();
      Node odd = new Node();
      Node oddTail = new Node();
      Node evenHead = even;
      Node oddHead = odd;

      while (root != null) {
         Node node = new Node();
         if (root.data % 2 == 0) {
            node.data = root.data;
            even = even.next = node;
            root = root.next;
         } else {
            node.data = root.data;
            odd = odd.next = node;
            oddTail = node;
            root = root.next;
         }
      }
      if (oddHead.next == null && evenHead.next != null) root = evenHead.next;
      else if (evenHead.next == null && oddHead.next != null) root = oddHead.next;
      else {
         oddTail.next = evenHead.next;
         root = oddHead.next;
      }
   }

   void removeDuplicate() {
      Node sorted = root;
      while (sorted != null && sorted.next != null) {
         if (sorted.data == sorted.next.data) {
            sorted.next = sorted.next.next;
         } else sorted = sorted.next;
      }
   }

   LinkedNode mergeSort() {
      if (root == null) return this;
      Node tail = root;
      while (tail.next != null) tail = tail.next;
      root = mergeSort(root, tail);
      return this;
   }

   // imp*
   Node mergeSort(Node head, Node tail) {
      if (head == tail) {
         return new Node(head.data);
      }
      Node midNode = midNode(head, tail);

      Node left = mergeSort(head, midNode);
      Node right = mergeSort(midNode.next, tail);

      return sortTwoSorted(left, right);
   }

   private Node sortTwoSorted(Node left, Node right) {
      Node node = new Node();
      Node head = node;
      while (left != null && right != null) {
         Node temp = new Node();
         if (left.data < right.data) {
            temp.data = left.data;
            left = left.next;
         } else {
            temp.data = right.data;
            right = right.next;
         }
         node.next = temp;
         node = node.next;
      }
      if (left != null) sortTheHalf(node, left);
      else if (right != null) sortTheHalf(node, right);
      return head.next;
   }

   private Node midNode(Node head, Node tail) {
      Node slow = head, fast = head;
      while (fast != tail && fast.next != tail) {
         slow = slow.next;
         fast = fast.next.next;
      }
      return slow;
   }


   LinkedNode sortTwoSortedList(LinkedNode linkedNode) {
      Node root2 = linkedNode.root;
      Node nextNode = new Node();
      Node head = nextNode;
      while (root2 != null && root != null) {
         Node tempNode = new Node();
         if (root2.data < root.data) {
            tempNode.data = root2.data;
            root2 = root2.next;
         } else {
            tempNode.data = root.data;
            root = root.next;
         }
         nextNode.next = tempNode;
         nextNode = nextNode.next;
//         nextNode = nextNode.next = tempNode; // this works too
      }
      if (root2 != null) sortTheHalf(nextNode, root2);
      else if (root != null) sortTheHalf(nextNode, root);
      root = head.next;
      return this;
   }

   private void sortTheHalf(Node nextNode, Node anyRoot) {
      while (anyRoot != null) {
         nextNode.next = new Node(anyRoot.data);
         nextNode = nextNode.next;
         anyRoot = anyRoot.next;
      }
   }


   int findMid() {
      Node slow = root, fast = root;
      while (fast.next != null && fast.next.next != null) {
         slow = slow.next;
         fast = fast.next.next;
      }
      return slow.data;
   }

   int kthFromTheEnd(int k) {
      Node slow = root, fast = root;
      while (fast != null && k > 0) {
         k--;
         fast = fast.next;
      }
      while (fast != null) {
         fast = fast.next;
         slow = slow.next;
      }
      return slow.data;
   }


   LinkedNode reverse() {
      Node prev = null, later = null;
      while (root != null) {
         prev = root;
         root = root.next;
         prev.next = later;
         later = prev;
      }
      root = prev;
      return this;
   }

   public LinkedNode remove(int index) { // 1, 2, 4, 5, 6, 7, 8, count = 5, index = 2
      if (size - 1 < index) {
         System.out.println("out of bound");
      } else if (index == 0) {
         pop();
      } else {
         int count = 1;
         Node temp = root;
         while (count != index) {
            count++;
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

   LinkedNode removeV2(int index) {
      if (index > size - 1) {
         System.out.println("out of bound");
         return this;
      }
      size--;
      if (index == 0) {
         root = root.next;
         return this;
      }
      Node temp = root;
      int count = 0;
      while (count < index - 1) {
         count++;
         temp = temp.next;
      }
      Node toRemove = temp.next;
      temp.next = toRemove.next;
      return this;
   }


   LinkedNode add(int index, int data) {
      if (index > size) {
         System.out.println("out of bound");
         return this;
      }
      size++;
      if (index == 0) {
         root = new Node(data, root);
         return this;
      }
      Node temp = root;
      int count = 0;
      while (count < index - 1) {
         count++;
         temp = temp.next;
      }
      Node toReplace = temp.next;
      temp.next = new Node(data, toReplace);
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
      if (arr.length == 0) return this;
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
