package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecClass {
    public static void main(String[] args) {
        subsetSum(new int[]{1, 2, 3, 4, 5, 6}, 0, 7, 0, "");
    }

    private static void subsetSum(int[] arr, int idx, int target, int current, String psf) {
        if (idx >= arr.length) return;
        if (target == current) {
            System.out.println(psf);
            return;
        }

        subsetSum(arr, idx + 1, target, current + arr[idx], arr[idx] + " " + psf);
        subsetSum(arr, idx + 1, target, current, psf);
    }


    private static List<String> getMazePath(int xStart, int yStart, int xEnd, int yEnd) {
//        System.out.println(Arrays.toString(getMazePath(1, 1, 3, 3).toArray()));
        if (xStart > xEnd || yStart > yEnd) return List.of();
        if (xStart == xEnd && yStart == yEnd) return List.of("");


        List<String> horizontal = getMazePath(xStart, yStart + 1, xEnd, yEnd);
        List<String> vertical = getMazePath(xStart + 1, yStart, xEnd, yEnd);
        List<String> tempPath = new ArrayList<>();

        for (String h : horizontal) {
            tempPath.add("H " + h);
        }
        for (String v : vertical) {
            tempPath.add("V " + v);
        }
        return tempPath;

    }

    // did it by myself, good medium level question
    private static List<String> getSubsequence(String str) {
//        System.out.println(Arrays.toString(getSubsequence("abc").toArray()));
        if (str.length() == 0) {
            return List.of("");
        }

        char frontChar = str.charAt(0);
        List<String> tempSub = getSubsequence(str.substring(1));
        List<String> toReturn = new ArrayList<>();

        for (String st : tempSub) {
            toReturn.add(st);
            toReturn.add(frontChar + st);
        }
        return toReturn;
    }
}
