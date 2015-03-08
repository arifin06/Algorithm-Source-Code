import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arifin
 *
 * MST(Kruskal)
 *
 * @input
    5 3
    1 2 20
    4 5 40
    3 2 30
 * @output
    1 --> 2 == 20
    3 --> 2 == 30
    4 --> 5 == 40
    Total: 90
 */
public class Main {

    static int path[];

    public static void main(String args[]) throws Exception {

        InputStreamReader isr;
        isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);

        String str = in.readLine();

        String delims = "[ ]+";
        String[] tokens = str.split(delims);

        int total_node = Integer.parseInt(tokens[0]);
        int total_edge = Integer.parseInt(tokens[1]);

        Edge e[] = new Edge[total_edge + 1];

        path = new int[total_node + 1];

        for (int p = 1; p <= total_edge; p++) {
            str = in.readLine();
            tokens = str.split(delims);
            e[p] = new Edge();
            e[p].i = Integer.parseInt(tokens[0]);
            e[p].j = Integer.parseInt(tokens[1]);
            e[p].w = Integer.parseInt(tokens[2]);
        }
        sort(e, total_edge);

        List<Edge> list = new ArrayList<Edge>();

        for (int p = 1; p <= total_edge; p++) {
            if (!isCycle(e[p])) {
                list.add(e[p]);
            }
        }

        int sum = 0;

        for (int p = 0; p < list.size(); p++) {
            sum += list.get(p).w;
            System.out.println(list.get(p).i + " --> " + list.get(p).j + " == " + list.get(p).w);
        }

        System.out.println("Total: " + sum);
    }

    static boolean isCycle(Edge e) {
        int u = e.i;
        int v = e.j;

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

    static void sort(Edge e[], int N) {
        boolean swap = true;
        int j = 1;
        Edge temp = new Edge();
        while (swap) {
            swap = false;
            for (int i = 1; i <= (N - j); i++) {
                if (e[i].w > e[i + 1].w) {
                    swap = true;
                    temp = e[i];
                    e[i] = e[i + 1];
                    e[i + 1] = temp;
                }
            }
            j++;
        }

    }

    static class Edge {
        int i, j, w;
    }
}