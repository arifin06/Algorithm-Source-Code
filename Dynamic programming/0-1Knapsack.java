import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Arifin
 *
 * 0-1 knapsack
F(i, w)= 0  if(i>n)
F(i, w)= cost[i] * F(i+1, w+weight[i])  //profit by taking ith item
F(i, w)= F(i+1, w)  // profit by not taking ith item
 *@Input
    5  //total number of item
    30 // Capacity of your bag
    4 200  //total 5 item's weight and value, here, weight 4, value 200
    3 250  // weight value
    3 150  // weight value
    6 280  // weight value
    2 330  // weight value
 *
 * @Output
 *  1210
 */
public class Main {

    static int n, capacity;
    static int weight[], value[];
    static int dp[][]; //dp dimension will be number of recursive function’s arguments.

    public static void main(String[] args) throws IOException {
        InputStreamReader isr;
        isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);
        n = Integer.parseInt(in.readLine());
        capacity = Integer.parseInt(in.readLine());
        dp = new int[n + 1][capacity + 1];
        weight = new int[n + 1]; //suppose index starts with 1
        value = new int[n + 1];
        filldp(-1);

        String str, delim = "[ ]+", tokens[];

        for (int i = 1; i <= n; i++) {
            str = in.readLine();
            tokens = str.split(delim);

            weight[i] = Integer.parseInt(tokens[0]);
            value[i] = Integer.parseInt(tokens[1]);
        }

        System.out.println(knapsack(1, 0));
    }

    static void filldp(int val) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                dp[i][j] = val;
            }
        }
    }

    static int knapsack(int i, int w) {
        if (i > n) {
            return 0;
        }
        if (dp[i][w] != -1) {
            return dp[i][w];
        }

        int profit_by_taking_item = 0, profit_without_taking_item = 0;

        if ((w + weight[i]) <= capacity) {
            profit_by_taking_item = value[i] + knapsack(i + 1, w + weight[i]);
        }

        profit_without_taking_item = knapsack(i + 1, w);

        dp[i][w] = Math.max(profit_by_taking_item, profit_without_taking_item);

        return dp[i][w];
    }
}