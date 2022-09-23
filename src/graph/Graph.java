package graph;

import java.util.*;

public class Graph {

    public static class Pair {
        public int val;
        public int time;
        public int i;
        public int j;

        public Pair(int val, int time, int i, int j) {
            this.val = val;
            this.time = time;
            this.i = i;
            this.j = j;
        }

        public Pair() {
        }
    }

    public static void main(String[] args) {
        int[][] arr = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}};
        System.out.println(rottenOranges(arr, 0, 0));
    }


    private static int rottenOranges(int[][] arr, int i, int j) { // todo : will start from sunday
        int timeTaken = 0;
        int[][] vis = new int[arr.length][arr[0].length];
        System.arraycopy(arr, 0, vis, 0, arr.length);
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(vis[i][j], 0, i, j));
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            int[] ith = {0, -1, 0, 1};
            int[] jth = {1, 0, -1, 0};
            for (int k = 0; k < ith.length; k++) {
                if (p.i + ith[k] < 0 || p.i + ith[k] >= arr.length || p.j + jth[k] < 0 || p.j + jth[k] >= arr[0].length || vis[p.i + ith[k]][p.j + jth[k]] == 2)
                    continue;
                vis[p.i + ith[k]][p.j + jth[k]] = 2;
                queue.add(new Pair(arr[p.i + ith[k]][p.j + jth[k]], p.time + 1, p.i + ith[k], p.j + jth[k]));
            }

        }


        return timeTaken;
    }

    private static int[][] floodFill(int[][] arr, int i, int j, int newColor) {

//        int[][] arr = {
//                {3, 1, 1},
//                {3, 1, 0},
//                {1, 0, 1}};
//
//        for (int[] row : floodFill(arr, 1, 1, 2)) {
//            System.out.println(Arrays.toString(row));
//        }

        int[][] visited = new int[arr.length][arr[0].length];
        System.arraycopy(arr, 0, visited, 0, arr.length);

        floodFillHelper(arr, i, j, visited, newColor, arr[i][j]);
        return visited;
    }

    private static void floodFillHelper(int[][] arr, int i, int j, int[][] visited, int newColor, int prevColor) {
        if (i >= arr.length || i < 0 || j >= arr[i].length || j < 0 || visited[i][j] == newColor || arr[i][j] != prevColor)
            return;

        visited[i][j] = newColor;

        int[] ith = {0, 1, 0, -1}; // next time pass from parent caller
        int[] jth = {1, 0, -1, 0};

        for (int k = 0; k < ith.length; k++) {
            floodFillHelper(arr, i + ith[k], j + jth[k], visited, newColor, prevColor);
        }

//        floodFillHelper(arr, i, j + 1, visited, newColor, prevColor); // right
//        floodFillHelper(arr, i + 1, j, visited, newColor, prevColor); // down
//        floodFillHelper(arr, i, j - 1, visited, newColor, prevColor);// left
//        floodFillHelper(arr, i - 1, j, visited, newColor, prevColor); // up
    }

    private static int noOfIslands(int[][] arr) {

//        int[][] arr = {
//                {0, 1, 1, 0},
//                {0, 1, 1, 0},
//                {1, 0, 0, 0},
//                {0, 0, 0, 0},
//                {1, 1, 0, 1}};
//        System.out.println(noOfIslands(arr));

        boolean[][] visited = new boolean[arr.length][arr[0].length];
        int noOFIsland = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (!visited[i][j] && arr[i][j] != 0) {
                    noOFIsland += 1;
                    dfsForIsland(visited, arr, i, j);
                }
            }
        }
        return noOFIsland;
    }

    private static void dfsForIsland(boolean[][] visited, int[][] arr, int i, int j) {

        if (i >= arr.length || i < 0 || j >= arr[i].length || j < 0 || visited[i][j] || arr[i][j] == 0) return;

        visited[i][j] = true;

        int[] ith = {0, 1, 0, -1};
        int[] jth = {1, 0, -1, 0};

        for (int k = 0; k < 4; k++) dfsForIsland(visited, arr, i + ith[k], j + jth[k]);


//        dfsForIsland(visited, arr, i, j + 1); // right
//        dfsForIsland(visited, arr, i + 1, j); // down
//        dfsForIsland(visited, arr, i, j - 1); // left
//        dfsForIsland(visited, arr, i - 1, j); // top
    }

    private static int noOfProvincesV2(List<List<Integer>> adl, int len) {

//        List<List<Integer>> adL = new ArrayList<>();
//        for (int i = 0; i < 9; i++) adL.add(new ArrayList<>());
//
//        adL.add(1, List.of(2));
//        adL.add(2, List.of(1, 3));
//        adL.add(3, List.of(2));
//        adL.add(4, List.of(5, 7));
//        adL.add(5, List.of(4, 6));
//        adL.add(6, List.of(5, 7));
//        adL.add(7, List.of(4, 6));
//        adL.add(8, List.of(9));
//        adL.add(9, List.of(8, 10));
//        adL.add(10, List.of(9));
//        System.out.println(noOfProvincesV2(adL, 10));

        boolean[] visited = new boolean[adl.size()];
        int counter = 0;
        for (int i = 1; i <= 10; i++) {
            if (!visited[i]) {
                counter += 1;
                DFSV2(adl, i, visited);
            }
        }
        return counter;
    }

    private static void DFSV2(List<List<Integer>> adl, int i, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(i);
        while (!stack.isEmpty()) {
            int index = stack.pop();
            for (Integer in : adl.get(index)) {
                if (!visited[in]) {
                    visited[in] = true;
                    stack.push(in);
                }
            }
        }
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
