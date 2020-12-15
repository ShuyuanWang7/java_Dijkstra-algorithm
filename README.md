# java_Dijkstra-algorithm
Use Dijkstra-algorithm to find shortest path.

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Graph {
    boolean direct;
    int max = 0;
    int[][] matrix;
    public static final int MAX = Integer.MAX_VALUE;

    //
    public void readGraphFile(String strFile) throws IOException {
        File filename = new File(strFile);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line;
        line = br.readLine();
        if (Integer.parseInt(line) == 1) {
            direct = true;
        } else if (Integer.parseInt(line) == 0) {
            direct = false;
        }
        while ((line = br.readLine()) != null) {
            String ss[] = line.split(" ");
            for (int i = 0; i < ss.length - 1; i++) {
                if (max < Integer.parseInt(ss[i])) {
                    max = Integer.parseInt(ss[i]);
                }
            }
        }
        br.close();
        matrix = new int[max + 1][max + 1];
        InputStreamReader reader1 = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br1 = new BufferedReader(reader1);
        line = br1.readLine();
        int i, j, distance;
        while ((line = br1.readLine()) != null) {
            String ss[] = line.split(" ");
            i = Integer.parseInt(ss[0]);
            j = Integer.parseInt(ss[1]);
            distance = Integer.parseInt(ss[2]);
            matrix[i][j] = distance;
            if (!direct) {
                matrix[j][i] = distance;
            }
        }
        br.close();
    }

    // print each vertex in a line with other vertices and their weight in ascending order according to vertices number
    public String printAdjacencyList() {
        String strList = "";
        StringBuffer a = new StringBuffer();
        for (int i = 0; i < matrix.length; i++) {
            a.append(i + ":");
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > 0) {
                    a.append("(" + i + "," + j + ":" + matrix[i][j] + ")");
                }
            }
            a.append("\n");
        }
        strList = a.toString();
        return strList;// each line prints the start vertex and other vertices numbers and weights of edges ascending
    }

    // print each weight in matrix with vertices number as row and col in ascending order according to vertices number
    public String printAdjacencyMatrix() {
        String strMatrix = "";
        StringBuffer a = new StringBuffer();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                a.append(matrix[j][i] + " ");
            }
            a.append("\n");
        }
        strMatrix = a.toString();
        return strMatrix;// each row and each line print the vertex numbers and weights ascending
    }

    // print the shortest path from startVertex to other vertices, except itself
    // output format: [shortestPathLength]v1,v2,...,vn
    // if no path from v1 to vn, print [0]null
    public String ShortestPath(int startVertex) {
        String strPath = "";
        StringBuffer a = new StringBuffer();
        int[][] matrix_1 = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix_1[i][j] = MAX;
                } else {
                    matrix_1[i][j] = matrix[i][j];
                }
            }
        }
        boolean[] visited = new boolean[matrix_1.length];
        int[] distance = new int[matrix_1[0].length];
        int[] midpoint = new int[matrix_1[0].length];
        for (int i = 0; i < matrix_1.length; i++) {
            distance[i] = matrix_1[startVertex][i];
            visited[i] = false;
            if (i != startVertex && distance[i] < MAX)
                midpoint[i] = startVertex;
            else
                midpoint[i] = -1;
        }
        visited[startVertex] = true;
        int min;
        int v = 0;
        for (int i = 1; i < matrix_1.length; i++) {
            min = MAX;
            for (int j = 0; j < matrix_1.length; j++) {
                if ((!visited[j]) && distance[j] < min) {
                    v = j;
                    min = distance[j];
                }
            }
            visited[v] = true;
            for (int j = 0; j < matrix_1.length; j++) {
                if ((!visited[j]) && matrix_1[v][j] < MAX) {
                    if (min + matrix_1[v][j] <= distance[j]) {
                        distance[j] = min + matrix_1[v][j];
                        midpoint[j] = v;
                    }
                }
            }
        }
        for (int i = 0; i < matrix_1.length; i++) {
            if (i == startVertex) {
                continue;
            } else if (distance[i] == MAX) {
                a.append("[0]null\n");
            } else {
                int j = midpoint[i];
                Stack<Integer> sources = new Stack<>();
                while (j != startVertex) {
                    sources.push(j);
                    j = midpoint[j];
                }
                a.append("[" + distance[i] + "]" + startVertex);
                while (!sources.isEmpty()) {
                    a.append("," + sources.pop());
                }
                a.append("," + i + "\n");
            }
        }
        strPath = a.toString();
        return strPath;
    }

    public String ShortestPath(int startVertex, int endVertex) {
        String strPath = "";
        String[] a = ShortestPath(startVertex).split("\n");
        if (startVertex < endVertex) {
            strPath = a[endVertex - 1];
        } else {
            strPath = a[endVertex];
        }
        return strPath;
    }


}
