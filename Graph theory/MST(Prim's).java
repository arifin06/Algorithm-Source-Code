import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author Arifin
 *
 * MST(Prim's)
 *
 * @input
	5 3 // number of nodes=5, number of edges=3
	1 2 20
	4 5 40
	3 2 30
 * @output
	1 --> 2 == 20
	2 --> 3 == 30
	4 --> 5 == 40
	Total: 90
 */
public class Main {

    static int adjMatrix[][];
    static int path[];
    static boolean visited[];
    static int total_node;
    static int total_edge;
    static ArrayList<Edge> list;
    static PriorityQueue<Edge> edgePriorityQueue;

    static class Edge implements Comparable<Edge> {

        int u, v, weight;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.weight = w;
        }

        public int compareTo(Edge o) {
            return (weight - o.weight);
        }
    }

    public static void main(String[] args) throws Exception {

        InputStreamReader isr;
        isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);
        String str = in.readLine();
        String delims = "[ ]+";
        String[] tokens = str.split(delims);
        total_node = Integer.parseInt(tokens[0]);
        total_edge = Integer.parseInt(tokens[1]);

        adjMatrix = new int[total_node + 1][total_node + 1];
        path = new int[total_node + 1];
        visited = new boolean[total_node + 1];

        int u, v, w;
        for (int i = 0; i < total_edge; i++) {
            str = in.readLine();
            tokens = str.split(delims);
            u = Integer.parseInt(tokens[0]);
            v = Integer.parseInt(tokens[1]);
            w = Integer.parseInt(tokens[2]);
            adjMatrix[u][v] = w;
            adjMatrix[v][u] = w;
        }

        edgePriorityQueue = new PriorityQueue<Edge>();
        list = new ArrayList<Edge>();

        for (int i = 1; i <= total_node; i++) {
            if (!visited[i]) {
                visited[i] = true;
                for (int j = 1; j <= total_node; j++) {
                    if (adjMatrix[i][j] > 0) {
                        Edge e = new Edge(i, j, adjMatrix[i][j]);
                        edgePriorityQueue.add(e);
                    }
                }
                prims();
            }
        }

        int sum = 0;
        for (int p = 0; p < list.size(); p++) {
            sum += list.get(p).weight;
            System.out.println(list.get(p).u + " --> " + list.get(p).v + " == " + list.get(p).weight);
        }
        System.out.println("Total: " + sum);
    }

    static void prims() {
        while (null != edgePriorityQueue.peek()) {
            Edge e = edgePriorityQueue.remove();

            if (!isCycle(e)) {
                list.add(e);
                if (!visited[e.v]) {
                    visited[e.v] = true;
                    for (int j = 1; j <= total_node; j++) {
                        if (adjMatrix[e.v][j] > 0) {
                            Edge e1 = new Edge(e.v, j, adjMatrix[e.v][j]);
                            edgePriorityQueue.add(e1);
                        }
                    }
                }
            }
        }
    }

    static boolean isCycle(Edge e) {
        int u = e.u;
        int v = e.v;
        while (path[u] > 0) {
            u = path[u];
        }
        while (path[v] > 0) {
            v = path[v];
        }
        if (u == v) {
            return true;
        } else {
            path[u] = v;
        }
        return false;
    }
}


