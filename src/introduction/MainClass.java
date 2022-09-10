package introduction;

public class MainClass {
   public static void main(String[] args) {
      char[][] arr = {
           {'A','B','C','E'},
           {'S','F','C','S'},
           {'A','D','E','E'}};
      System.out.println(exist(arr,"ABCCED"));
      System.out.println(arr.length +" "+arr[0].length);
   }

   private static boolean isExist = false;
   private static boolean exist(char[][] arr, String str){
      int[][] visited = new int[arr.length][arr[0].length];
      existHelp(arr,str,visited,0,0);
      return isExist;
   }

   private static void existHelp(char[][] arr, String str, int[][] visited, int currentRow, int currentCol){
      if(str.length() == 0) {
         isExist = true;
         return ;
      }
      if(currentRow < 0 || currentCol < 0|| currentRow >= arr.length || currentCol >= arr[0].length ||
           visited[currentRow][currentCol] == 1) return; // if(ch == arr[currentRow][currentCol]) str = str.substring(1); // todo will do later for sure
      char ch = str.charAt(0);

      visited[currentRow][currentCol] = 1;
      if(ch == arr[currentRow][currentCol]) str = str.substring(1);

         existHelp(arr, str, visited, currentRow - 1, currentCol);
         existHelp(arr, str, visited, currentRow, currentCol + 1);
         existHelp(arr, str, visited, currentRow + 1, currentCol);
         existHelp(arr, str, visited, currentRow, currentCol - 1);

      visited[currentRow][currentCol] = 0;
   }

   private static void printCombination(String str, String ssf) {
//      printCombination("abc", "");
      if (str.length() == 0) {
         System.out.print(ssf + " ");
         return;
      }
      char ch = str.charAt(0);
      printCombination(str.substring(1), ssf);
      printCombination(str.substring(1), ssf + ch);
   }

   private static int[] findAllIndex(int[] arr, int index, int noOfElementFound, int element) {
//        System.out.println(Arrays.toString(findAllIndex(new int[]{1, 4, 1, 2, 3, 4, 4, 5, 5, 6}, 0, 0, 4)));
      if (arr.length == index) return new int[noOfElementFound];
      if (arr[index] == element) noOfElementFound += 1;

      int[] indexes = findAllIndex(arr, index + 1, noOfElementFound, element);
      if (arr[index] == element) {
         indexes[noOfElementFound - 1] = index;
      }
      return indexes;
   }

   private static void printDeCInc(int n) {
      if (n == 0) return;
      System.out.println(n);
      printDeCInc(n - 1);
      System.out.println(n);
   }

   private static void TOH(int s, int d, int h, int n) {
//      TOH(11, 12, 13, 3);
      if (n == 1) {
         System.out.println(s + " " + "-->" + "" + d);
         return;
      }
      TOH(s, h, d, n - 1);
      System.out.println(s + " " + "-->" + "" + d);
      TOH(h, d, s, n - 1);
   }
}
