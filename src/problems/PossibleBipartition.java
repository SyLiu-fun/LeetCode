package problems;

import java.util.ArrayList;
import java.util.List;

//886
//二分图问题，
class PossibleBiPartition {
    int n;
//    方法一：染色 + dfs
    public boolean possibleBiPartition1(int n, int[][] dislikes) {
        this.n = n;
        //这样存储省空间，而且遍历次数更少，list[i]列表存储与i不在同一集合中的点
        List<Integer>[] f = new ArrayList[n + 1];
        for(int i = 0; i <= n; ++i) f[i] = new ArrayList();
        //g表示各点的分组，0代表未分组，1和-1表示分成的两个组
        int[] g = new int[n + 1];
        for(int[] p : dislikes) {
            f[p[0]].add(p[1]);
            f[p[1]].add(p[0]);
        }
        // 如果一个点已经分好组了，不用dfs，因为该点的分组是正确的
        // 这是因为之前有点与该点不能分到同一组，那么该点已经使用dfs搜索过且返回为true(false的话在前面迭代时就已经返回了)
        for(int i = 1; i <= n; ++i) {
            if(g[i] == 0 && !dfs(i, 1, f, g)) return false;
        }
        return true;
    }

    // color表示点i需要分的组，dfs搜索该点是否能被分到color组中
    // 进行搜索说明i未被分组，需要对不能与i同组的点进行判断
    public boolean dfs(int i, int color, List<Integer>[] f, int[] g) {
        g[i] = color;
        //遍历每个不能与i同组的点，如果未分组，dfs查看是否可以分到同组中；如果已分组且与i分到同一组，说明不能二分
        for(int j = 0; j < f[i].size(); ++j) {
            int ne = f[i].get(j);
            if((g[ne] == 0 && !dfs(ne, -color, f, g)) || g[ne] == g[i]) return false;
        }
        return true;
    }

    // 方法二：并查集，不需要具体将某个点分到哪个集合，只需要保证互斥的点不在同一集合中，将与某个点互斥的点都归并到同一个集合
    int[] p;
    public boolean possibleBiPartition2(int n, int[][] dislikes) {
        List<Integer>[] f = new ArrayList[n + 1];
        p = new int[n + 1];
        for(int i = 0; i <= n; ++i) {
            f[i] = new ArrayList();
            p[i] = i;
        }
        for(int[] p : dislikes) {
            f[p[0]].add(p[1]);
            f[p[1]].add(p[0]);
        }

        for(int i = 1; i <= n; ++i) {
            for(int j : f[i]) {
                if(find(i) == find(j)) return false;
                // 与i互斥的点都设为与其中一个点在一个连通块上
                union(f[i].get(0), j);
            }
        }
        return true;
    }

    public int find(int x) {
        if(x != p[x]) p[x] = find(p[x]);
        return p[x];
    }

    public void union(int i, int j) {
        int fi = find(i);
        int fj = find(j);
        if(fi == fj) return;
        p[fj] = p[fi];
    }
}