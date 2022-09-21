package problems;

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int len = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i <= amount; ++i){
            for(int j = 0; j < len; ++j){
                if(i >= coins[j] && dp[i - coins[j]] != Integer.MAX_VALUE) dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
