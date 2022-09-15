package graph;

import java.util.*;

import static graph.Graph.hover;

public class Graph {

    public static void main(String[] args) {
        List<List<Integer>> adL = new ArrayList<>();
        for (int i = 0; i < 9; i++) adL.add(new ArrayList<>());

        adL.add(1, List.of(2));
        adL.add(2, List.of(1, 3));
        adL.add(3, List.of(2));
        adL.add(4, List.of(5, 7));
        adL.add(5, List.of(4, 6));
        adL.add(6, List.of(5, 7));
        adL.add(7, List.of(4, 6));
        adL.add(8, List.of(9));
        adL.add(9, List.of(8, 10));
        adL.add(10, List.of(9));
        System.out.println(noOfProvinces(adL, 10));
    }

    private static int noOfProvinces(List<List<Integer>> adl, int len) {
        boolean[] visited = new boolean[adl.size()];
        int counter = 0;
        for (int i = 1; i <= len; i++) {
            if (!visited[i]) {
                counter++;
                hover(adl, i, visited);
            }
        }
        return counter;
    }

    private static void hover(List<List<Integer>> adl, int i, boolean[] visited) {
        visited[i] = true;

        for (Integer in : adl.get(i)) {
            if (!visited[in]) {
                visited[in] = true;
                hover(adl, in, visited);
            }
        }
    }

    private static List<Integer> DFS(List<List<Integer>> adL) {
        List<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[adL.size()];
        DFSHelper(adL, list, visited, 1);
        return list;
    }

    private static void DFSHelper(List<List<Integer>> adL, List<Integer> list, boolean[] visited, int index) {

        visited[index] = true;
        list.add(index);
        for (Integer in : adL.get(index)) {
            if (!visited[in]) {
                visited[in] = true;
                DFSHelper(adL, list, visited, in);
            }
        }

    }


    private static List<Integer> BFS(List<List<Integer>> adL) { // adL adjacency list
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[adL.size()];
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int data = queue.poll();
            list.add(data);

            for (int val : adL.get(data)) {
                if (!visited[val]) {
                    queue.add(val);
                    visited[val] = true;
                }
            }
        }
        return list;
    }
}
//adL.add(1, List.of(2, 6));
//        adL.add(2, List.of(1, 3, 4));
//        adL.add(3, List.of(2));
//        adL.add(4, List.of(2, 5));
//        adL.add(5, List.of(4, 7));
//        adL.add(6, List.of(1, 7, 8));
//        adL.add(7, List.of(5, 6, 8));
//        adL.add(8, List.of(6));
