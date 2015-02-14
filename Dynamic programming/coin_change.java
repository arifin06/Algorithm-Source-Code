import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Arifin
 *
 * coin changing problem
 * This is the solution of the below uva problem:
 *  http://uva.onlinejudge.org/external/6/674.html
 *
 * suppose input is 11. Given coins are 1, 5, 10, 25, 50 cents. Now how many ways 11 can be made by using these coins?
 * So, we will check all possible combination of coins to make 11.
 *
 * @input
 * 11
 * @output
 * 4
 *
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static int N, coin[] = {1, 5, 10, 25, 50};
    static long dp[][];

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputStreamReader isr;
        isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);

        N = Integer.parseInt(in.readLine());
        dp = new long[5][N+1];
        filldp();
        
        System.out.println(coinchange(0, N));
    }

    static long coinchange(int i, int amount) {
        if (i >= 5) {
            if (0 == amount) {
                return 1;
            } else {
                return 0;
            }
        }
        if (dp[i][amount] != -1) {
            return dp[i][amount];
        }

        long ret1 = 0, ret2 = 0;

        int val = amount - coin[i];
        if (val >= 0) {
            ret1 = coinchange(i, val); //taking ith coin again
        }
        ret2 = coinchange(i + 1, amount); // don't take ith coin

        return dp[i][amount] = ret1 + ret2;
    }

    static void filldp() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = -1;
            }
        }
    }
}