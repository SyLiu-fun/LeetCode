package problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 前缀和优化的区间dp问题
public class BeautifulPartitions {
    final int M = (int)(1e9 + 7);
    public int beautifulPartitions(String s, int k, int minLength) {
        int n = s.length();
        Set<Character> prime = new HashSet<>();
        prime.add('2'); prime.add('3'); prime.add('5'); prime.add('7');
        long[][] dp = new long[n + 1][k + 1], pre = new long[n + 1][k + 1];
        Arrays.fill(dp[0], 1L);
        Arrays.fill(pre[0], 0L);
        for(int j = 1; j <= k; ++j) {
            for(int i = j * minLength; i <= n; ++i) {
                int x = i - minLength + 1;
                pre[x][j - 1] = prime.contains(s.charAt(x - 1)) ? pre[x - 1][j - 1] + dp[x - 1][j - 1] : pre[x - 1][j - 1];
                dp[i][j] = !prime.contains(s.charAt(i - 1)) ? (dp[i][j] + pre[x][j - 1]) % M : 0;
            }
        }
        return (int)dp[n][k];
    }
}
