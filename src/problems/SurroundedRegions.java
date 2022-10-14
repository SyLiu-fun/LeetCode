package problems;

//130
//并查集解法，合并时一定先用find找根节点，根节点合并
public class SurroundedRegions {
    int[] p;
    final int[] _x = new int[]{0, -1, 0, 1}, _y = new int[]{-1, 0, 1, 0};
    public void solve(char[][] b) {
        int m = b.length, n = b[0].length;
        p = new int[m * n + 1];
        for(int i = 0; i < m; ++i)
            for(int j = 0; j < n; ++j)
                p[i * n + j] = i * n + j;
        //dummyNode，与所有边界O相邻
        p[m * n] = m * n;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(b[i][j] == 'O') {
                    //dummyNode父结点可能会变
                    if(i == 0 || i == m - 1 || j == 0 || j == n - 1) p[find(i * n + j)] = find(m * n);
                    else {
                        for(int k = 0; k < 4; ++k) union(i, j, i + _x[k], j + _y[k], b, m, n);
                    }
                }
            }
        }
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j) {
                if(b[i][j] == 'O') {
                    if(find(m * n) != find(i * n + j)) b[i][j] = 'X';
                }
            }
        }
    }

    public int find(int x) {
        if(x != p[x]) p[x] = find(p[x]);
        return p[x];
    }

    public void union(int i, int j, int x, int y, char[][] b, int m, int n) {
        if(x < 0 || x >= m || y < 0 || y >= n || b[x][y] == 'X') return;
        int rootX = find(i * n + j);
        int rootY = find(x * n + y);
        if(rootX == rootY) return;
        p[rootY] = rootX;
    }
}
