package problems;

import java.util.*;

// 1235
// 枚举时间端点，建议抄答案
public class JobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length, idx = 0;
        // 维护当前右端点，[right, idx]形式
        Queue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Map<Integer, Integer> e2i = new HashMap<>();
        Set<Integer> s = new TreeSet<>();

        for(int time : endTime) s.add(time);
        for(int time : startTime) s.add(time);
        for(int i : s) e2i.put(i, ++idx);
        for(int i = 0; i < n; ++i) {
            q.offer(new int[]{e2i.get(endTime[i]), i});
        }
        int[] dp = new int[idx + 1];
        for(int i = 1; i <= idx; ++i) {
            if(i != q.peek()[0]) dp[i] = dp[i - 1];
            else {
                int max = 0;
                while(!q.isEmpty() && i == q.peek()[0]) {
                    int[] a = q.poll();
                    max = Math.max(max, dp[e2i.get(startTime[a[1]])] + profit[a[1]]);
                }
                dp[i] = Math.max(dp[i - 1], max);
            }
        }
        return dp[idx];
    }
}
