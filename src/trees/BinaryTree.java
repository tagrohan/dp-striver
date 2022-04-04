package trees;

import java.util.*;

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

      @Override
      public String toString() {
         return "Node{" +
              "data=" + data +
              '}';
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
      Integer[] arr2 = new Integer[]{50, 20, 10, null, null, 30, null, null, 60, 55, null, null, 70, null, null};
      createTree(arr);
      System.out.println(diameterOfTree(root));
   }
// time complexity is O(n^2) todo it has some issue
   private static int diameterOfTree(Node root) {
      if (root == null) return 0;

      int maxLeft = maxLen(root.left);
      int maxRight = maxLen((root.right));
      int height = maxLeft + maxRight + 2;
      return Integer.max(height, Integer.max(maxLeft, maxRight));
   }

   private static int maxLen(Node root) {
      if (root == null) return -1;
      return Integer.max(maxLen(root.left), maxLen(root.right)) + 1;
   }

   private static void transformBackToNormalFromLeftCloned(Node root) {
      if (root == null) return;
      root.left = root.left.left;

      transformBackToNormalFromLeftCloned(root.left);
      transformBackToNormalFromLeftCloned(root.right);

   }

   private static void transformToALeftClonedTree(Node root) {
      if (root == null) return;

      Node temp = root.left;
      root.left = new Node(root.data);
      root.left.left = temp;

      transformToALeftClonedTree(root.left.left);
      transformToALeftClonedTree(root.right);
   }

   private static void printKFarNode(Node root, int data, int k) {
      List<Node> getPathToRoot = getPathToRoot(root, data);
      for (int i = 0; i < getPathToRoot.size(); i++) {
         printKLvlDownV2(getPathToRoot.get(i), k - i, i == 0 ? null : getPathToRoot.get(i - 1));
      }
   }

   private static void printKLevelDownWithBlocker(Node root, int k, Node blocker) {
      if (root == null || k < 0 || root == blocker) return;
      if (k == 0) System.out.print(root.data + " ");

      printKLevelDownWithBlocker(root.left, k - 1, blocker);
      printKLevelDownWithBlocker(root.right, k - 1, blocker);
   }

   private static void printKLvlDownV2(Node root, int k, Node blocker) {
      Queue<Node> queue = new ArrayDeque<>();
      queue.add(root);
      while (k-- >= 0) {
         int size = queue.size();
         for (int i = 0; i < size; i++) {
            Node node = queue.remove();
            if (node.left != null && node.left != blocker) queue.add(node.left);
            if (node.right != null && node.right != blocker) queue.add(node.right);
         }
         queue.forEach(System.out::println);
      }
   }

   private static List<Node> getPathToRoot(Node root, int data) {
      if (root == null) return List.of();
      if (root.data == data) {
         List<Node> pathList = new ArrayList<>();
         pathList.add(root);
         return pathList;
      }

      List<Node> left = getPathToRoot(root.left, data);
      List<Node> right = getPathToRoot(root.right, data);
      if (left.size() > 0) left.add(root);
      if (right.size() > 0) right.add(root);

      return left.size() > 0 ? left : right;
   }

   // working like a champ
   private static Node removeChildV2(Node root) {
      if (root == null) return null;

      if (root.left == null && root.right == null) return null;

      root.left = removeChildV2(root.left);
      root.right = removeChildV2(root.right);
      return root;
   }

   // will have one above that return Node
   private static void removeChild(Node root) {
      if (root == null) return;
      if (root.left != null) {
         if (root.left.left == null && root.left.right == null) root.left = null;
      }
      if (root.right != null) {
         if (root.right.left == null && root.right.right == null) root.right = null;
      }

      removeChild(root.left);
      removeChild(root.right);
   }

   private static void printSingleChild(Node root) {
      if (root == null) return;

      if (root.left != null && root.right == null) System.out.println(root.left.data);
      else if (root.right != null && root.left == null) System.out.println(root.right.data);

      printSingleChild(root.left);
      printSingleChild(root.right);
   }

   // to identify a leaf its left and right both are null
   private static void pathToLeafInRangeSum(Node root, int lowerBound, int upperBound, int sum, String psf) {
//      pathToLeafInRangeSum(root, 50, 260, 0, "");
      if (root == null) return;
      if (root.left == null && root.right == null) {
         if (sum >= lowerBound && sum <= upperBound) {
            System.out.println(psf + root.data);
         }
      }

      pathToLeafInRangeSum(root.left, lowerBound, upperBound, root.data + sum, psf + root.data + " ");
      pathToLeafInRangeSum(root.right, lowerBound, upperBound, root.data + sum, psf + root.data + " ");
   }

   private static void printKLevelDownRec(Node root, int k) {
      if (root == null || k < 0) return;
      if (k == 0) System.out.println(root.data);

      printKLevelDownRec(root.left, k - 1);
      printKLevelDownRec(root.right, k - 1);
   }

   // it's an O(N) algo
   private static void printKLevelDown(Node root, int k) {
      int level = 1;
      Queue<Node> queue = new ArrayDeque<>();
      queue.add(root);
      while (level++ <= k) {
         int size = queue.size();
         while (size-- > 0) {
            Node node = queue.remove();
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
         }
      }
      queue.forEach(System.out::println);
   }

   private static boolean nodeToRootPath(Node root, int key) {
      if (root == null) return false;
      if (root.data == key) {
         System.out.println(root.data);
         return true;
      }
      boolean left = nodeToRootPath(root.left, key);
      boolean right = nodeToRootPath(root.right, key);
      if (left || right) System.out.println(root.data + " ");
      return left || right;
   }

   private static int heightOfTree(Node root) {
      if (root == null) return -1; // bcz we are considering vertices as height here
      int left = heightOfTree(root.left);
      int right = heightOfTree(root.right);
      return Integer.max(left, right) + 1;
   }

   private static int size(Node root) {
      if (root == null) return 0;
      return size(root.left) + size(root.right) + 1;
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
