package problems;

//埃式筛法
public class Euler {

    public int euler(int n){
        if(n == 1 || n == 0) return 0;
        int[] prime = new int[n];
        int[] vis = new int[n];
        int cnt = 0;
        for(int i = 2;i < n;++i){
            if(vis[i] == 0) prime[cnt++] = i;
            for(int j = 0;j < cnt && i * prime[j] < n;++j){
                vis[i * prime[j]] = 1;
                if(i % prime[j] == 0) break;
            }
        }
        return cnt;
    }
}
