package graph;

import java.util.*;

public class Graph {

    public static class Pair {
        public int first; // val
        public int second; // prev, parent
        public int i;
        public int j;

        public Pair(int first, int second, int i, int j) {
            this.first = first;
            this.second = second;
            this.i = i;
            this.j = j;
        }

//        public Pair(int first, int second) {
//            this.first = first;
//            this.second = second;
//        }


        public Pair(int first, int i, int j) { // for distanceOfNearestMatrix
            this.first = first;
            this.i = i;
            this.j = j;
        }

        public Pair(int i, int j) { // for distanceOfNearestMatrix
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        int[][] arr = {
                {},
                {2},
                {3},
                {4, 7},
                {5},
                {6},
                {},
                {5},
                {2, 9},
                {10},
                {8}
        };
        System.out.println(detectCycleInDirectedGraph(arr));
    }

    private static boolean detectCycleInDirectedGraph(int[][] arr) {
        boolean[] visited = new boolean[arr.length];
        boolean[] prevVisited = new boolean[arr.length];
        for (int i = 1; i < arr.length; i++) {
            if (!visited[i]) {
                if (dfsForDirectedGraph(visited, prevVisited, arr, i)) return true;
            }
        }
        return false;
    }

    private static boolean dfsForDirectedGraph(boolean[] visited, boolean[] prevVisited, int[][] arr, int i) {

        visited[i] = true;
        prevVisited[i] = true;

        for (int index : arr[i]) {
            if (visited[index]) {
                if (prevVisited[index]) {
                    return true;
                }
                continue;
            }
            if (dfsForDirectedGraph(visited, prevVisited, arr, index)) return true;
        }
        prevVisited[i] = false;
        return false;
    }

    private static int noOf1nsInsideBoundaryV2(int[][] arr) {
//        int[][] arr = {
//                {1, 1, 1, 1, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0}};
//
//        System.out.println(noOf1nsInsideBoundaryV2(arr));

        int noOf1ns = 0;
        boolean[][] visited = new boolean[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (i == 0 || j == 0 || i == arr.length - 1 || j == arr[0].length - 1) {
                    if (!visited[i][j] && arr[i][j] == 0) {
                        dfsForNoOf1nsInsideBoundaryV2(arr, visited, i, j);
                    }
                }
            }
        }

        for (int[] in : arr) {
            System.out.println(Arrays.toString(in));
        }

        visited = new boolean[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 0 && !visited[i][j]) {
                    noOf1ns += 1;
                    dfsForIslandV2(visited, arr, i, j);
                }
            }
        }

        return noOf1ns;
    }

    private static void dfsForIslandV2(boolean[][] visited, int[][] arr, int i, int j) {
        if (i >= arr.length || i < 0 || j >= arr[i].length || j < 0 || visited[i][j] || arr[i][j] == 1) return;

        visited[i][j] = true;

        int[] ith = {0, 1, 0, -1};
        int[] jth = {1, 0, -1, 0};

        for (int k = 0; k < 4; k++) dfsForIslandV2(visited, arr, i + ith[k], j + jth[k]);

    }

    private static void dfsForNoOf1nsInsideBoundaryV2(int[][] arr, boolean[][] visited, int i, int j) {

        if (i < 0 || j < 0 || i >= arr.length || j >= arr[i].length || visited[i][j] || arr[i][j] == 1) return;

        if (arr[i][j] == 0) visited[i][j] = true;

        int[] ith = {0, 1, 0, -1};
        int[] jth = {1, 0, -1, 0};
        for (int k = 0; k < ith.length; k++) {
            dfsForNoOf1nsInsideBoundaryV2(arr, visited, ith[k] + i, jth[k] + j);
        }
    }


    private static boolean isGraphBipartiteDFS(int[][] arr, int noOfNodes, int startingIndex) {
//
//        int[][] arr = {
//                {1, 2, 3},
//                {0, 2},
//                {0, 1, 3},
//                {0, 2}};
//
//        int[][] arr2 = {
//                {1, 3},
//                {0, 2},
//                {1, 3},
//                {0, 2},
//                {3}
//        };
//
//        System.out.println(isGraphBipartiteDFS(arr2, 5, 0));
        Stack<Integer> stack = new Stack<>();
        int[] color = new int[noOfNodes];
        stack.push(startingIndex);
        color[startingIndex] = -1; // 0,-1,1

        while (!stack.isEmpty()) {
            int index = stack.pop();
            for (int it : arr[index]) {
                if (color[it] != 0) {
                    if (color[it] == color[index]) return false;
                    continue;
                }
                color[it] = -1 * color[index];
                stack.push(it);
            }
        }

        return true;
    }


    private static boolean isGraphBipartite(int[][] arr, int noOfNodes, int startingIndex) { // adj list in form of matrix
        int[] colour = new int[noOfNodes]; // -1, 1 -> for bipartite
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startingIndex);
        colour[startingIndex] = -1;
        while (!queue.isEmpty()) {
            int index = queue.poll();
            for (int it : arr[index]) {
                if (colour[it] != 0) {
                    if (colour[it] == colour[index]) return false;
                    continue;
                }
                colour[it] = -1 * colour[index];
                queue.add(it);
            }
        }
        return true;
    }


    static int countDistinctIslands(int[][] grid) { // todo : better to go with this approach

//        int[][] arr = {
//                {1, 1, 0, 0}, // orbdb
//                {1, 0, 0, 0},
//                {0, 0, 1, 1}, // ordbb
//                {0, 0, 0, 1}};
//
//        System.out.println(countDistinctIslands(arr));
        Set<String> distinct = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    distinct.add(layout(i, j, grid));
                }
            }
        }
        return distinct.size();
    }

    static String layout(int i, int j, int[][] grid) {

        int[] R = {0, 0, 1, -1};
        int[] C = {1, -1, 0, 0};
        int[] D = {1, 2, 3, 4};

        StringBuilder sb = new StringBuilder();
        Queue<int[]> queue = new LinkedList<>();
        int size;
        int[] current;
        int nR;
        int nC;

        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size != 0) {
                size--;
                current = queue.poll();
                for (int k = 0; k < R.length; k++) {
                    nR = current[0] + R[k];
                    nC = current[1] + C[k];
                    if (nR < 0 || nR == grid.length || nC < 0 ||
                            nC == grid[0].length || grid[nR][nC] != 1) {
                        sb.append(0);
                        continue;
                    }
                    if (grid[nR][nC] == 1) {
                        queue.add(new int[]{nR, nC});
                        grid[nR][nC] = 2; // modifying grid here
                        sb.append(D[k]);
                    }
                }
            }
        }
        return sb.toString();

    }

    private static int noOf1nsInsideBoundaryQueue(int[][] arr) {
        Queue<Pair> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        int noOf1ns = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (i == 0 || j == 0 || i == arr.length - 1 || j == arr[0].length - 1) {
                    if (arr[i][j] == 1 && !visited[i][j]) {
                        queue.add(new Pair(i, j));
                        visited[i][j] = true;
                    }

                }
            }
        }

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            int[] ith = {0, 1, 0, -1};
            int[] jth = {1, 0, -1, 0};
            for (int i = 0; i < ith.length; i++) {
                int row = pair.i + ith[i];
                int col = pair.j + jth[i];
                if (row < 0 || col < 0 || row == arr.length || col == arr[0].length || visited[row][col] || arr[row][col] == 0)
                    continue;
                queue.add(new Pair(row, col));
                visited[row][col] = true;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (!visited[i][j] && arr[i][j] == 1) noOf1ns += 1;
            }
        }
        return noOf1ns;
    }

    private static int noOf1nsInsideBoundary(int[][] arr) {

//        int[][] arr = {
//                {1, 0, 0, 1},
//                {0, 1, 1, 0},
//                {0, 1, 1, 0},
//                {1, 0, 0, 1}};
//
//        System.out.println(noOf1nsInsideBoundary(arr));

        int noOf1ns = 0;
        boolean[][] visited = new boolean[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (i == 0 || j == 0 || i == arr.length - 1 || j == arr[0].length - 1) {
                    if (!visited[i][j] && arr[i][j] == 1) {
                        dfsForNoOf1nsInsideBoundary(arr, visited, i, j);
                    }
                }
            }
        }


//        for (int i = 0; i < arr.length; i++) {
//            if (arr[0][i] == 1 && !visited[0][i]) { // first row
//                dfsForNoOf1nsInsideBoundary(arr, visited, 0, i);
//            }
//            if (arr[arr.length - 1][i] == 1 && !visited[arr.length - 1][i]) { // last row
//                dfsForNoOf1nsInsideBoundary(arr, visited, arr.length - 1, i);
//            }
//        }
//        for (int i = 0; i < arr[0].length; i++) {
//            if (arr[i][0] == 1 && !visited[i][0]) { // first col
//                dfsForNoOf1nsInsideBoundary(arr, visited, i, 0);
//            }
//            if (arr[i][arr[0].length - 1] == 1 && !visited[arr[0].length - 1][i]) { // last col
//                dfsForNoOf1nsInsideBoundary(arr, visited, i, arr[0].length - 1);
//            }
//        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (!visited[i][j] && arr[i][j] == 1) noOf1ns += 1;
            }
        }

        return noOf1ns;
    }

    private static void dfsForNoOf1nsInsideBoundary(int[][] arr, boolean[][] visited, int i, int j) {

        if (i < 0 || j < 0 || i >= arr.length || j >= arr[i].length || visited[i][j] || arr[i][j] == 0) return;

        if (arr[i][j] == 1) visited[i][j] = true;

        int[] ith = {0, 1, 0, -1};
        int[] jth = {1, 0, -1, 0};
        for (int k = 0; k < ith.length; k++) {
            dfsForNoOf1nsInsideBoundary(arr, visited, ith[k] + i, jth[k] + j);
        }
    }

    public static int noOfClosedIslands(int[][] arr) {

//        int[][] arr = {
//
//                {1, 1, 1, 1, 1, 1, 1, 0},
//                {1, 0, 0, 0, 0, 1, 1, 0},
//                {1, 0, 1, 0, 1, 1, 1, 0},
//                {1, 0, 0, 0, 0, 1, 0, 1},
//                {1, 1, 1, 1, 1, 1, 1, 0}
//        };
//        System.out.println(noOfClosedIslands(arr));

        boolean[][] visited = new boolean[arr.length][arr[0].length];
        int noOfIsland = 0;
        for (int i = 0; i < arr[0].length; i++) { // first row
            if (arr[0][i] == 0 && !visited[0][i]) {
                noOfIsland += 1;
                dfsForReplaceOToX(arr, visited, 0, i);
            }
            if (arr[arr.length - 1][i] == 0 && !visited[arr.length - 1][i]) { // last row
                noOfIsland += 1;
                dfsForReplaceOToX(arr, visited, arr.length - 1, i);
            }
        }

        for (int i = 0; i < arr.length; i++) { // first col
            if (arr[i][0] == 0 && !visited[i][0]) {
                noOfIsland += 1;
                dfsForReplaceOToX(arr, visited, i, 0);
            }
            if (arr[i][arr[0].length - 1] == 0 && !visited[i][arr[0].length - 1]) { // last col
                noOfIsland += 1;
                dfsForReplaceOToX(arr, visited, i, arr[0].length - 1);
            }
        }

        return noOfIsland;
    }


    private static void dfsForReplaceOToX(int[][] arr, boolean[][] visited, int i, int j) {

        if (i < 0 || j < 0 || i >= arr.length || j >= arr[i].length || visited[i][j] || arr[i][j] == 1) return;

        if (arr[i][j] == 0) visited[i][j] = true;

        dfsForReplaceOToX(arr, visited, i, j + 1);
        dfsForReplaceOToX(arr, visited, i + 1, j);
        dfsForReplaceOToX(arr, visited, i, j - 1);
        dfsForReplaceOToX(arr, visited, i - 1, j);

    }

    private static char[][] replaceOSurroundedByX(char[][] arr) { // todo : it has some fault check above

//        char[][] arr = {
//                {'x', 'x', 'x', 'x'},
//                {'x', 'o', 'o', 'x'},
//                {'x', 'o', 'x', 'o'},
//                {'x', 'x', 'o', 'o'}};
//
//        for (char[] ch : replaceOSurroundedByX(arr)) {
//            System.out.println(Arrays.toString(ch));
//        }

        boolean[][] visited = new boolean[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) { // first row
            if (arr[0][i] == 'o' && !visited[0][i]) {
                dfsForReplaceOToX(arr, visited, 0, i);
            }
            if (arr[arr.length - 1][i] == 'o' && !visited[arr.length - 1][i]) { // last row
                dfsForReplaceOToX(arr, visited, arr.length - 1, i);
            }
        }

        for (int i = 0; i < arr[0].length; i++) { // first col
            if (arr[i][0] == 'o' && !visited[i][0]) {
                dfsForReplaceOToX(arr, visited, i, 0);
            }
            if (arr[i][arr[0].length - 1] == 'o' && !visited[i][arr[0].length - 1]) { // last col
                dfsForReplaceOToX(arr, visited, i, arr[0].length - 1);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (!visited[i][j] && arr[i][j] == 'o') arr[i][j] = 'x';
            }
        }
        return arr;
    }

    private static void dfsForReplaceOToX(char[][] arr, boolean[][] visited, int i, int j) {

        if (i < 0 || j < 0 || i >= arr.length || j >= arr[i].length || visited[i][j] || arr[i][j] == 'x') return;

        if (arr[i][j] == 'o') visited[i][j] = true;

        dfsForReplaceOToX(arr, visited, i, j + 1);
        dfsForReplaceOToX(arr, visited, i + 1, j);
        dfsForReplaceOToX(arr, visited, i, j - 1);
        dfsForReplaceOToX(arr, visited, i - 1, j);

    }


    private static int[][] distanceOfNearestMatrix(int[][] grid) {

//        int[][] arr = {
//                {0, 0, 0},
//                {0, 1, 0},
//                {1, 0, 1}};
//
//        int[][] arr2 = {
//                {0},
//                {0},
//                {0},
//                {0},
//                {0}};
//
//        for (int[] a : distanceOfNearestMatrix(arr2)) System.out.println(Arrays.toString(a));

        Queue<Pair> queue = new ArrayDeque<>(); // p.first = steps
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[][] modified = new int[grid.length][grid[0].length];
//        System.arraycopy(grid, 0, arr, 0, grid.length); // making a hard copy
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                modified[i][j] = grid[i][j];
                if (grid[i][j] == 1) {
                    queue.add(new Pair(0, i, j));
                    visited[i][j] = true;
                    grid[i][j] = 0;
                }
            }
        }
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();  //(0,1,1)

            int[] ith = {0, 1, 0, -1};
            int[] jth = {1, 0, -1, 0};
            for (int i = 0; i < ith.length; i++) {
                int row = pair.i + ith[i]; // 0
                int col = pair.j + jth[i]; // 1
                if (row < 0 || row >= modified.length || col < 0 || col >= modified[row].length || visited[row][col])
                    continue;
                visited[row][col] = true;
                modified[row][col] = pair.first + 1;
                queue.add(new Pair(pair.first + 1, row, col));
            }
        }
        return modified;
    }

    private static boolean findConnectedNodesDFS(List<List<Integer>> adl) { // cycle

        //        System.out.println(findConnectedNodesDFS(ad)); // for connected component
//        for (int i = 1; i < 7; i++) {
//            if (!vis[i]) {
//                if (findConnectedNodesDFS(ad));
//            }
//        }


//        List<List<Integer>> ad = new ArrayList<>();
//        ad.add(List.of());
//        ad.add(List.of(2, 5));
//        ad.add(List.of(1, 3, 4));
//        ad.add(List.of(2));
//        ad.add(List.of(2, 6));
//        ad.add(List.of(1, 6));
//        ad.add(List.of(4, 5));
//
//        System.out.println(findConnectedNodesDFS(ad));

        Stack<Pair> stack = new Stack<>();
        boolean[] vis = new boolean[adl.size()];
        vis[1] = true;
        stack.push(new Pair(1, -1));

        while (!stack.isEmpty()) {
            Pair p = stack.pop();
            for (int i : adl.get(p.first)) {
                if (vis[i]) {
                    if (p.second != i) return true;
                    continue;
                }
                vis[i] = true;
                stack.push(new Pair(i, p.first));
            }
        }

        return false;
    }

    private static boolean findConnectedNodesBFS(List<List<Integer>> adl) {
        Queue<Pair> queue = new ArrayDeque<>();
        boolean[] vis = new boolean[adl.size()];
        vis[1] = true;
        queue.add(new Pair(1, -1));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            for (int i : adl.get(pair.first)) {
//                if (vis[i]) {       // there are two ways both are there
//                    if (i != pair.second) return true;
//                    continue;
//                }
//                vis[i] = true;
//                queue.add(new Pair(i, pair.first));

                if (!vis[i]) {
                    vis[i] = true;
                    queue.add(new Pair(i, pair.first));
                } else if (i != pair.second) return true;
            }
        }
        return false;
    }


    private static int rottenOranges(int[][] arr, int i, int j) { // in case of multiple 2's we can pass them initially
//        int[][] arr = {
//                {2, 1, 1},
//                {1, 1, 0},
//                {0, 1, 1}};
//        System.out.println(rottenOranges(arr, 0, 0));

        int timeTaken = 0;                                                                          //{2, 1, 1},
        int[][] vis = new int[arr.length][arr[0].length];                                           //{1, 1, 0}
        System.arraycopy(arr, 0, vis, 0, arr.length);                                // {0, 2, 1}};
        Queue<Pair> queue = getQueue(arr);
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            int[] ith = {0, -1, 0, 1};
            int[] jth = {1, 0, -1, 0};
            timeTaken = Integer.max(timeTaken, p.second); // second represent time here
            for (int k = 0; k < ith.length; k++) {
                if (p.i + ith[k] < 0 || p.i + ith[k] >= arr.length || p.j + jth[k] < 0 || p.j + jth[k] >= arr[0].length ||
                        vis[p.i + ith[k]][p.j + jth[k]] == 2 || vis[p.i + ith[k]][p.j + jth[k]] == 0)
                    continue;
                vis[p.i + ith[k]][p.j + jth[k]] = 2;
                queue.add(new Pair(arr[p.i + ith[k]][p.j + jth[k]], p.second + 1, p.i + ith[k], p.j + jth[k]));
            }
        }

        return timeTaken;
    }

    private static Queue<Pair> getQueue(int[][] arr) {
        Queue<Pair> queue = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 2) queue.add(new Pair(2, 0, i, j));
            }
        }
        return queue;
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
