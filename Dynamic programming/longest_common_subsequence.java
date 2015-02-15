import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author Arifin
 *
 * Longest Common Subsequence
 *
 * @input
 * abcdef
 * fbadg
 * @output
 * 2  //common sequence length = 2
 * ad //All common subsequence
 * bd
 *
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static int len1, len2, dp[][];
    static String str1, str2, printString = "";
    static TreeSet<String> tree;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputStreamReader isr;
        isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);

        str1 = in.readLine();
        len1 = str1.length();
        str2 = in.readLine();
        len2 = str2.length();

        tree = new TreeSet<String>();
        dp = new int[len1 + 1][len2 + 1];
        filldp(-1);

        System.out.println(LCS(0, 0));
        printString = "";
        printAllLCS(0, 0);
        Iterator<String> it = tree.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    static void filldp(int val) {
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                dp[i][j] = val;
            }
        }
    }

    static int LCS(int i, int j) {
        if (i >= len1 || j >= len2) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int sum = 0;
        if (str1.charAt(i) == str2.charAt(j)) {
            sum = 1 + LCS(i + 1, j + 1);
        } else {
            int val1 = LCS(i + 1, j);
            int val2 = LCS(i, j + 1);

            sum = Math.max(val1, val2);
        }
        return dp[i][j] = sum;
    }

    static void printLCS(int i, int j) {
        if (i >= len1 || j >= len2) {
            System.out.println("******" + printString);
            return;
        }
        if (str1.charAt(i) == str2.charAt(j)) {
            printString += str1.charAt(i);
            printLCS(i + 1, j + 1);
        } else {
            if (dp[i + 1][j] > dp[i][j + 1]) {
                printLCS(i + 1, j);
            } else {
                printLCS(i, j + 1);
            }
        }
    }

    static void printAllLCS(int i, int j) {
        if (i >= len1 || j >= len2) {
            tree.add(printString);
            return;
        }
        if (str1.charAt(i) == str2.charAt(j)) {
            printString += str1.charAt(i);
            printAllLCS(i + 1, j + 1);
            printString = printString.substring(0, printString.length() - 1);
        } else {
            if (dp[i + 1][j] > dp[i][j + 1]) {
                printAllLCS(i + 1, j);
            } else if (dp[i + 1][j] < dp[i][j + 1]) {
                printAllLCS(i, j + 1);
            } else {
                printAllLCS(i, j + 1);
                printAllLCS(i + 1, j);
            }
        }
    }
}