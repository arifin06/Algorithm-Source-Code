import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Arifin
 *
 * Longest increasing subsequence
 *
 * @input
 * 2 6 3 4 1 2 3 5 8
 * @output
 * 1 -->2 -->3 -->5 -->8
 *
 * @input
 * 0 8 4 12 2 10 6 14 1 9 5 13 3 11 7 15
 * @output
 * 0 -->2 -->6 -->9 -->11 -->15
 *
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static int N;
    static int value[], dp[], path[];

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputStreamReader isr;
        isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);

        String str, delim = "[ ]+", tokens[];

        str = in.readLine();
        tokens = str.split(delim);

        int max = 0, start = 0;

        N = tokens.length;
        dp = new int[N + 1];
        value = new int[N + 1];
        path = new int[N + 1];

        for (int i = 0; i < N; i++) {
            dp[i] = -1;
            value[i + 1] = Integer.parseInt(tokens[i]);
        }
        dp[N] = -1;

        int val;

        int realval = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            val = longest(i);
            if (val >= max && realval > value[i]) {
                max = val;
                start = i;
                realval = value[i];
            }
        }

        System.out.println("Starting index: " + start);

        printPath(start);
    }

    static void printPath(int u) {
        while (path[u] > 0) {
            System.out.print(value[u] + " -->");
            u = path[u];
        }
        System.out.println(value[u]);
    }

    static int longest(int u) {
        if (dp[u] != -1) {
            return dp[u];
        }
        int max = 0;
        int realval = Integer.MAX_VALUE;
        for (int i = u + 1; i <= N; i++) {
            if (value[i] > value[u]) {
                int val = longest(i);
                if (val >= max && realval > value[i]) {
                    max = val;
                    path[u] = i;
                    realval = value[i];
                }
            }
        }
        dp[u] = 1 + max;
        return dp[u];
    }
}