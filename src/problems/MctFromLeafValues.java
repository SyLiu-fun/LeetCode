package problems;

import java.util.Arrays;

public class MctFromLeafValues {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = arr[i];
        }
        for(int l = 2; l <= n; ++l) {
            for(int i = n - l; i >= 0; --i) {
                int r = i + l - 1;
                for(int j = i + 1; j <= r; ++j) {
                    int mx = getMax(i, j - 1, arr) *  getMax(j, r, arr);
                    if(j != i + 1) mx += dp[i][j - 1];
                    if(j != r) mx += dp[j][r];
                    dp[i][r] = Math.min(mx, dp[i][r]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public int getMax(int l, int r, int[] arr) {
        int ans = 0;
        for(int i = l; i <= r; ++i) {
            ans = Math.max(ans, arr[i]);
        }
        return ans;
    }
}
