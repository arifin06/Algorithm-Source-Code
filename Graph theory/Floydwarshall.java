import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 *
 * @author Arifin
 * 
 * Floyd warshall
 * https://uva.onlinejudge.org/external/8/821.pdf
 */
public class Algorithm {

    static int mat[][], maxNode;
    static TreeSet<Integer> tree;

    public static void main(String[] args) throws Exception {
	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str, tokens[], delim = "[ ]";
        int len;
        mat = new int[101][101];

        tree = new TreeSet<>();
        
        int t=1;

        while (true) {
            str = br.readLine();
            tokens = str.split(delim);
            len = tokens.length - 2;
            maxNode = 0;
            if ("0".equals(tokens[0]) && "0".equals(tokens[1])) {
                break;
            }
            tree.clear();
            fillMat();
            int u, v;
            for (int i = 0; i < len; i += 2) {
                u = Integer.parseInt(tokens[i]);
                v = Integer.parseInt(tokens[i + 1]);
                mat[u][v] = 1;
                if (maxNode < u) {
                    maxNode = u;
                }
                if (maxNode < v) {
                    maxNode = v;
                }
                tree.add(u);
                tree.add(v);
            }

            floydWarshall();

            int val = findCost();
            int l = tree.size();
            int num = l * (l - 1);
            String result= "Case "+ t+ ": average length between pages = "+String.format("%.3f", (val / (float) num)) + " clicks";
            System.out.println(result);
            t++;
        }

    }

    static void floydWarshall() {
        int val;
        for (int k = 1; k <= maxNode; k++) {
            for (int i = 1; i <= maxNode; i++) {
                for (int j = 1; j <= maxNode; j++) {
                    if (i == j) {
                        continue;
                    }
                    val = mat[i][k] + mat[k][j];
                    if (mat[i][j] > val) {
                        mat[i][j] = val;
                    }
                }
            }
        }
    }

    static int findCost() {
        int cost = 0;
        for (int i = 1; i <= maxNode; i++) {
            for (int j = 1; j <= maxNode; j++) {
                if (i == j || mat[i][j] == 100000) {
                    continue;
                }
                cost += mat[i][j];
            }
        }
        return cost;
    }

    static void fillMat() {
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                mat[i][j] = 100000;
            }
        }
    }
}