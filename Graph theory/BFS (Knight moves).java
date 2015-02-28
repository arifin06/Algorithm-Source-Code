import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author Arifin
 *
 * BFS (Knight moves)
 *
 * Problem link:
 * http://uva.onlinejudge.org/external/4/439.html
 *
 * @input
 *  e2 e4
    a1 b2
    b2 c3
    a1 h8
    a1 h7
    h8 a1
    b1 c3
    f6 f6
 * @output
 *  To get from e2 to e4 takes 2 knight moves.
    To get from a1 to b2 takes 4 knight moves.
    To get from b2 to c3 takes 2 knight moves.
    To get from a1 to h8 takes 6 knight moves.
    To get from a1 to h7 takes 5 knight moves.
    To get from h8 to a1 takes 6 knight moves.
    To get from b1 to c3 takes 1 knight moves.
    To get from f6 to f6 takes 0 knight moves.
 *
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static int destI, destJ;
    static boolean visited[][];
    static Map<Character, Integer> map;
    static int X[] = {2, 1, -1, -2, 2, 1, -1, -2};
    static int Y[] = {1, 2, 2, 1, -1, -2, -2, -1};
    static Queue<Edge> queue;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputStreamReader isr;
        isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);
        map = new HashMap<Character, Integer>();

        map.put('a', 1);
        map.put('b', 2);
        map.put('c', 3);
        map.put('d', 4);
        map.put('e', 5);
        map.put('f', 6);
        map.put('g', 7);
        map.put('h', 8);
        String str, delim = "[ ]+", tokens[];

        queue = new LinkedList<Edge>();

        while (true) {
            str = in.readLine();
            if (null == str) {
                break;
            }
            tokens = str.split(delim);

            int j = map.get(tokens[0].charAt(0));
            int i = Character.getNumericValue(tokens[0].charAt(1));

            destJ = map.get(tokens[1].charAt(0));
            destI = Character.getNumericValue(tokens[1].charAt(1));

            visited = new boolean[9][9];

            queue.clear();

            if (i == destI && j == destJ) {
                System.out.println("To get from " + tokens[0] + " to " + tokens[1] + " takes 0 knight moves.");
            } else {
                System.out.println("To get from " + tokens[0] + " to " + tokens[1] + " takes " + knightmove(i, j) + " knight moves.");
            }
        }
    }
    static int knightmove(int i, int j) {
        visited[i][j] = true;
        int u, v;
        Edge e = new Edge();
        e.i = i;
        e.j = j;
        e.level = 1;
        queue.add(e);
        while (!queue.isEmpty()) {
            Edge val = queue.remove();
            for (int k = 0; k < 8; k++) {
                u = X[k] + val.i;
                v = Y[k] + val.j;
                if (u > 0 && v > 0 && u < 9 && v < 9) {
                    if (!visited[u][v]) {
                        if (u == destI && v == destJ) {
                            return val.level;
                        } else {
                            Edge ee = new Edge();
                            ee.i = u;
                            ee.j = v;
                            ee.level = val.level + 1;
                            queue.add(ee);
                        }
                    }
                }
            }
        }
        return 0;
    }

    static class Edge {
        int i, j, level;
    }
}