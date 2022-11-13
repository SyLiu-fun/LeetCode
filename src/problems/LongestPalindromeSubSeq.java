package problems;

// 516
public class LongestPalindromeSubSeq {
    public int longestPalindromeSubSeq(String s) {
        int n = s.length(), ans = 0;
        int[][] dp = new int[n][n];
        dp[n - 1][n - 1] = 1;
        for(int i = n - 2; i >= 0; --i) {
            dp[i][i] = 1;
            for(int j = i + 1; j < n; ++j) {
                if(s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i + 1][j - 1] + 2;
                else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }
}
