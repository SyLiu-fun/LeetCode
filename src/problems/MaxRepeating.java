package problems;

//1668
public class MaxRepeating {
    public int maxRepeating(String sequence, String word) {
        int sn = sequence.length(), wn = word.length(), ans = 0;
        int[] dp = new int[sn + 1];
        for(int i = wn; i <= sn; ++i) {
            if(sequence.substring(i - wn, i).equals(word)) {
                dp[i] = dp[i - wn] + 1;
            } else dp[i] = 0;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
