import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Arifin
 *
 * fibonacci number using dp
   F(0)=0
   F(1)=1
   F(n)=F(n-1)+F(n-2)
 *
 * // Input N
 */
public class Main {

    static int N;
    static long dp[];

    public static void main(String[] args) throws IOException {

        InputStreamReader isr;
        isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);

        N = Integer.parseInt(in.readLine());
        dp = new long[N + 1];
        filldp(-1);
        fibonacci(N);

        /*for (int i = 0; i <= N; i++) {
            System.out.println(dp[i]);
        }*/
    }

    static void filldp(int val) {
        for (int i = 1; i <= N; i++) {
            dp[i] = val;
        }
    }

    static long fibonacci(int n) {
        if (n < 2) {
            dp[n] = n;
            return n;
        }
        if (dp[n] != -1) {
            return dp[n];
        } else {
            dp[n] = fibonacci(n - 1) + fibonacci(n - 2);
            return dp[n];
        }
    }
}
