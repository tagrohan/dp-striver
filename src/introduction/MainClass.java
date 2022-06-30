package introduction;

import java.util.Arrays;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("working..");
        printDeCInc(5);
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
}
