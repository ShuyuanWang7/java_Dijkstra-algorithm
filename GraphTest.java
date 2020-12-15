import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class GraphTest {
    @Test
    public void test01() throws IOException {
        Graph graph = new Graph();
        graph.readGraphFile("src\\graph1.txt");
        System.out.print(graph.printAdjacencyMatrix());
        assertEquals("0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 \n" +
                "0 2 0 0 0 0 5 0 \n" +
                "3 0 0 0 0 0 0 0 \n" +
                "6 0 0 0 0 0 0 0 \n" +
                "0 4 5 0 0 0 3 0 \n" +
                "0 0 9 0 9 4 0 0 \n" +
                "0 0 0 5 0 4 0 0 \n", graph.printAdjacencyMatrix());
        System.out.print(graph.printAdjacencyList());
        assertEquals("0:(0,3:3)(0,4:6)\n" +
                "1:(1,2:2)(1,5:4)\n" +
                "2:(2,5:5)(2,6:9)\n" +
                "3:(3,7:5)\n" +
                "4:(4,6:9)\n" +
                "5:(5,6:4)(5,7:4)\n" +
                "6:(6,2:5)(6,5:3)\n" +
                "7:\n", graph.printAdjacencyList());
        System.out.println(graph.ShortestPath(1));
        assertEquals("[0]null\n" +
                "[2]1,2\n" +
                "[0]null\n" +
                "[0]null\n" +
                "[4]1,5\n" +
                "[8]1,5,6\n" +
                "[8]1,5,7\n", graph.ShortestPath(1));
        System.out.println(graph.ShortestPath(1,3));
        System.out.println(graph.ShortestPath(1,6));
        assertEquals("[0]null",graph.ShortestPath(1,3));
        assertEquals("[8]1,5,6",graph.ShortestPath(1,6));
    }

    @Test
    public void test02() throws IOException {
        Graph graph = new Graph();
        graph.readGraphFile("src\\graph2.txt");
        System.out.print(graph.printAdjacencyMatrix());
        assertEquals("0 6 0 0 0 0 0 0 \n" +
                "6 0 0 7 1 0 0 0 \n" +
                "0 0 0 0 9 0 4 0 \n" +
                "0 7 0 0 0 7 0 1 \n" +
                "0 1 9 0 0 0 3 0 \n" +
                "0 0 0 7 0 0 6 5 \n" +
                "0 0 4 0 3 6 0 5 \n" +
                "0 0 0 1 0 5 5 0 \n",graph.printAdjacencyMatrix());
        System.out.print(graph.printAdjacencyList());
        assertEquals("0:(0,1:6)\n" +
                "1:(1,0:6)(1,3:7)(1,4:1)\n" +
                "2:(2,4:9)(2,6:4)\n" +
                "3:(3,1:7)(3,5:7)(3,7:1)\n" +
                "4:(4,1:1)(4,2:9)(4,6:3)\n" +
                "5:(5,3:7)(5,6:6)(5,7:5)\n" +
                "6:(6,2:4)(6,4:3)(6,5:6)(6,7:5)\n" +
                "7:(7,3:1)(7,5:5)(7,6:5)\n",graph.printAdjacencyList());
        graph.printAdjacencyMatrix();
        assertEquals("[6]1,0\n" +
                "[8]1,4,6,2\n" +
                "[7]1,3\n" +
                "[1]1,4\n" +
                "[10]1,4,6,5\n" +
                "[4]1,4,6\n" +
                "[8]1,3,7\n",graph.ShortestPath(1));
        assertEquals("[10]1,4,6,5",graph.ShortestPath(1,5));
    }
}
