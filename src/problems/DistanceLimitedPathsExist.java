package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 1697
// 离线算法，将查询按一定顺序进行排序，将查询视为一个整体，再分别顺序处理查询
public class DistanceLimitedPathsExist {
    // 并查集维护连通性，判断查询中的两个点是否连通
    int[] p;
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int len = queries.length;
        p = new int[n];
        List<Integer> idx = new ArrayList<>();
        boolean[] ans = new boolean[len];
        for(int i = 0; i < n; ++i) p[i] = i;
        for(int i = 0; i < len; ++i) idx.add(i);
        // 根据下标对查询按照limit从小到大进行排序
        Collections.sort(idx, (a, b) -> queries[a][2] - queries[b][2]);
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        for(int i = 0, j = 0; i < len; ++i) {
            int x = idx.get(i), a = queries[x][0], b = queries[x][1];
            int[] q = queries[x];
            while(j < edgeList.length && edgeList[j][2] < q[2]) {
                int fx = find(edgeList[j][0]), fy = find(edgeList[j][1]);
                if(fx != fy) {
                    p[fy] = fx;
                }
                j++;
            }
            ans[x] = find(a) == find(b);
        }
        return ans;
    }

    public int find(int x) {
        if(x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
