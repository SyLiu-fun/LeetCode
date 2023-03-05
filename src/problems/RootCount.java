package problems;

import java.util.*;

public class RootCount {
    int ans = 0;
    Map<Integer, Set<Integer>> mp;
    List<Integer>[] tree;
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        int n = edges.length;
        mp = new HashMap<>();
        tree = new List[n + 1];
        for(int i = 0; i < n + 1; ++i) tree[i] = new ArrayList<>();
        for(int[] e : edges) {
            int a = e[0], b = e[1];
            tree[a].add(b);
            tree[b].add(a);
        }
        for(int[] g : guesses) {
            int a = g[0], b = g[1];
            mp.computeIfAbsent(a, key -> new HashSet<>()).add(b);
        }
        int tot = dfs(0, new boolean[n + 1]);
        dfs2(0, tot, new boolean[n + 1], k);
        return ans;
    }

    public int dfs(int rt, boolean[] vis) {
        vis[rt] = true;
        int res = 0;
        Set<Integer> st = mp.get(rt);
        for(int i : tree[rt]) {
            if(vis[i]) continue;
            if(!Objects.isNull(st) && st.contains(i)) {
                res++;
            }
            res += dfs(i, vis);
        }
        return res;
    }

    public void dfs2(int rt, int tot, boolean[] vis, int k) {
        if(tot >= k) ans++;
        vis[rt] = true;
        int pre = tot;
        for(int i : tree[rt]) {
            if(vis[i]) continue;
            Set<Integer> st1 = mp.getOrDefault(i, new HashSet<>()), st2 = mp.getOrDefault(rt, new HashSet<>());
            if(st1.contains(rt)) tot++;
            if(st2.contains(i)) tot--;
            dfs2(i, tot, vis, k);
            tot = pre;
        }
    }
}
