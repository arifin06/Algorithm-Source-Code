import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Arifin
 *
 * nCr using dp
F(n, 1)= n
F(n, n)= 1
F(n, r)= F(n-1, r) + F(n-1, r-1)
 *
 * Input n, r
 *  6 //first line n
 *  4 // second line r
 *
 * Output: 15
 */
public class Main {

    static int n, r;
    static long dp[][]; //dp dimension will be number of recursive function’s arguments.

    public static void main(String[] args) throws IOException {

        InputStreamReader isr;
        isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);

        n = Integer.parseInt(in.readLine());
        r = Integer.parseInt(in.readLine());
        dp = new long[n + 1][r + 1];
        filldp(-1);

        System.out.println(nCr(n, r));
    }

    static void filldp(int val) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= r; j++) {
                dp[i][j] = val;
            }
        }
    }

    static long nCr(int n, int r) {
        if (r == 1) {
            return n;
        }
        if (n == r) {
            return 1;
        }
        if (dp[n][r] != -1) {
            return dp[n][r];
        } else {
            dp[n][r] = nCr(n - 1, r) + nCr(n - 1, r - 1);
            return dp[n][r];
        }
    }
}
