import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Arifin
 *
 * UVA-10003-Cutting Sticks
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=944
 *
 * @input
 *  100 --> length of the stick
    3 --> number of cuts to be made
    25 50 75 --> places of the cuts
    10
    4
    4 5 7 8
    0
 * @output
 *  The minimum cutting is 200.
    The minimum cutting is 22.
 *
 * 
 * F(start, end) = 0 if start+1=end
 * F(start, end) = for(j=start+1 to end-1)
 *                     min(F(start,j)+F(j,end)+ (val[end]-val[start]))
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static int len, dp[][], val[];

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputStreamReader isr;
        isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);

        String str, delim = "[ ]+", tokens[];

        int count = 0;

        while (true) {
            str = in.readLine();
            if (str.equals("0")) {
                break;
            }

            len = Integer.parseInt(str);

            int n = Integer.parseInt(in.readLine());
            val = new int[n + 2];

            str = in.readLine();
            tokens = str.split(delim);

            for (int i = 1; i <= n; i++) {
                val[i] = Integer.parseInt(tokens[i - 1]);
            }
            val[n + 1] = len;

            dp = new int[n+2][n+2];
            filldp(n+2);

            System.out.println("The minimum cutting is "+ mincut(0, n+1) + ".");
            
        }
    }

    static void filldp(int n)
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                dp[i][j]=-1;
            }
        }
    }

    static int mincut(int start, int end) {
        if (start + 1 == end) {
            return 0;
        }

        if(dp[start][end]!=-1)
        {
            return dp[start][end];
        }

        int minval = Integer.MAX_VALUE;
        for(int j=start+1; j<end; j++)
        {
            int n = mincut(start, j)+mincut(j,end)+(val[end]-val[start]);

            if(minval>n)
            {
                minval = n;
            }
        }
        return dp[start][end] = minval;
    }
}