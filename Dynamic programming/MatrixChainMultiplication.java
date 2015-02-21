import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Arifin
 *
 * Matrix chain multiplication
 *
 * @input
 *  40 20 30 10 30
 *  10 20 30 40 30
 * @output
 * The minimum number of multiplications are 26000.
 * The minimum number of multiplications are 30000.
 *
 * 
 * F(start, end) = 0 if start=end
 * F(start, end) = for(j=start to end-1)
 *                     min(F(start,j)+F(j+1,end)+ (row[start]*col[j]*col[end]))
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static int N, dp[][], row[], col[];

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputStreamReader isr;
        isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);

        String str, delim = "[ ]+", tokens[];
        str = in.readLine();
        tokens = str.split(delim);

        N = tokens.length;

        row = new int[N + 1];
        col = new int[N + 1];

        for (int i = 1; i < N; i++) {
            row[i] = Integer.parseInt(tokens[i - 1]);
            col[i] = Integer.parseInt(tokens[i]);
        }

        dp = new int[N + 1][N + 1];
        filldp(N + 1);

        System.out.println("The minimum number of multiplications are " + matrixmul(1, N - 1) + ".");
    }

    static void filldp(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
    }

    static int matrixmul(int start, int end) {
        if (start >= end) {
            return 0;
        }

        if (dp[start][end] != -1) {
            return dp[start][end];
        }

        int minval = Integer.MAX_VALUE;
        for (int j = start; j < end; j++) {
            int n = matrixmul(start, j) + matrixmul(j + 1, end) + (row[start] * col[j] * col[end]);
            if (minval > n) {
                minval = n;
            }
        }
        return dp[start][end] = minval;
    }
}