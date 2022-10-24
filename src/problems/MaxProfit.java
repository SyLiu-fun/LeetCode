package problems;

// 309
public class MaxProfit {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // dp[i][0] 持有股票， dp[i][1] 不持有且处于冷冻期， dp[i][2] 不持有且处于非冷冻期
        int[][] dp = new int[n][3];
        dp[0][0] = -prices[0];
        for(int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(Math.max(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
    }
}
