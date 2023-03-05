package problems;

// 线性筛法
// 核心原理：每个合数都只被最小质因子筛选一次
public class Euler {
    public int euler(int n){
        if(n == 1 || n == 0) return 0;
        int[] prime = new int[n + 1];
        boolean[] vis = new boolean[n + 1];
        int cnt = 0;
        for(int i = 2; i <= n; ++i) {
            // i 之前没有被筛到，i 是质数
            if(!vis[i]) prime[cnt++] = i;
            for(int j = 0; j < cnt && i * prime[j] <= n; ++j) {
                // 筛掉i * prime[j]
                vis[i * prime[j]] = true;
                // i % prime[j] == 0 说明 i 是合数且是prime[j]的倍数，下一个遍历的是i * prime[j + 1]
                // i * prime[j + 1] 也一定是 prime[j] 的倍数，如果不break，下一轮循环会筛掉i * prime[j + 1]
                // 但是i * prime[j + 1]的最小质因子 <= prime[j]，不符合线性筛的核心原理。i * prime[j + 1]一定会被再筛一次
                // 举例：i = 4 时，如果不break，可以筛选出12，但是后面i = 6时，12会被再筛一次
                if(i % prime[j] == 0) break;
            }
        }
        return cnt;
    }
}
