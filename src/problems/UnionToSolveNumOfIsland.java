package problems;

import java.util.HashSet;
import java.util.Set;

public class UnionToSolveNumOfIsland {
    class Union{
        //基于高度的并查集
        int[][] h;
        int[][] p;
        int m;
        int n;
        char[][] map;
        public Union(char[][]grid, int m, int n){
            h = new int[m][n];
            p = new int[m][n];
            this.m = m; this.n = n;
            map = grid;
            for(int i = 0; i < m; ++i){
                for(int j = 0; j < n; ++j){
                    p[i][j] = i * n + j; //唯一标识每个点
                    h[i][j] = 1;
                }
            }
        }

        public int find(int r, int c){
            while(p[r][c] != r * n + c){
                r = p[r][c] / n;
                c = p[r][c] - r * n;
            }
            return p[r][c];
        }

        public void union(int i, int j, int r, int c){
            if(r < 0 || r == m || c < 0 || c == n ) return;
            int fir = find(i, j);
            int sec = find(r, c);
            if(map[r][c] == '0' || fir == sec) return;
            i = fir / n; j = fir - i * n;
            r = sec / n; c = sec - r * n;
            if(h[i][j] > h[r][c]){
                p[r][c] = fir;
            }else if(h[i][j] < h[r][c]){
                p[i][j] = sec;
            }else{
                p[r][c] = fir;
                h[i][j] += 1;
            }
        }
    }

    public int numIslands(char[][] grid) {
        Union u = new Union(grid, grid.length, grid[0].length);
        Set<Integer> s = new HashSet<>();
        for(int i = 0; i < grid.length; ++i){
            for(int j = 0; j < grid[0].length; ++j){
                if(grid[i][j] == '1'){
                    u.union(i, j, i - 1, j);
                    u.union(i, j, i + 1, j);
                    u.union(i, j, i, j - 1);
                    u.union(i, j, i, j + 1);
                }
            }
        }
        for(int i = 0; i < grid.length; ++i){
            for(int j = 0; j < grid[0].length; ++j){
                if(grid[i][j] == '1') s.add(u.find(i, j));
            }
        }
        return s.size();
    }
}
