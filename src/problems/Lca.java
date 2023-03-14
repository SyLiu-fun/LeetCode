package problems;

import java.util.ArrayList;
import java.util.List;

// 有多个lca查询时，用tarjan算法和并查集离线查询lca
public class Lca {
    List<Integer>[] g;
    List<int[]>[] query;
    int[] fa, ans;
    boolean[] vis;
    public int[] lca(int n, int[][] edges, int[][] queries) {
        int len = query.length;
        ans = new int[len];
        g = new List[n + 1];
        fa = new int[n + 1];
        query = new List[n + 1];
        vis = new boolean[n + 1];
        for(int i = 0; i <= n; ++i) {
            fa[i] = i;
            g[i] = new ArrayList<>();
            query[i] = new ArrayList<>();
        }
        for(int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        for(int i = 0; i < len; ++i) {
            int a = queries[i][0], b = queries[i][1];
            query[a].add(new int[]{b, i});
            query[b].add(new int[]{a, i});
        }
        tarjan(1);
        return ans;
    }

    public int find(int x) {
        if(x != fa[x]) fa[x] = find(fa[x]);
        return fa[x];
    }

    public void tarjan(int u) {
        vis[u] = true;
        for(int v : g[u]) {
            if(!vis[v]) {
                tarjan(v);
                // dfs遍历之后，再设置父结点
                // 因为最近公共祖先有两种情况，这两种情况都需要u的子结点确定最近公共祖先后，再设置u的父结点
                fa[v] = u;
            }
        }
        // 离开u时，查询和u有关的查询
        // 如果u是v的子结点，uv的lca是v，fa[v]=v因为正在进行的是v的dfs,fa[v]还没修改
        // 如果u和v的祖先是x，如果先dfs到u，v未被访问，ans不修改；如果先dfs到v，find(v)为还未dfs完的第一个祖先节点，这个节点也是u的祖先
        // 故u、v的lca是x
        for(int[] q : query[u]) {
            int v = q[0], i = q[1];
            // 特别注意，v必须已经遍历过
            if(vis[v]) ans[i] = find(v);
        }
    }
}
