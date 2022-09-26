package problems;

import java.util.LinkedList;
import java.util.Queue;

//934
//有两个岛，先dfs遍历其中一个，全部标记为2，再从另一个岛开始BFS，遇到2即终止遍历，返回遍历层数-1
public class ShortestBridge {
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    Queue<int[]> q = new LinkedList();
    boolean f = false;
    public int shortestBridge(int[][] grid) {
        int cnt = 0;
        for(int i = 0; i < grid.length; ++i){
            if(f) break;
            for(int j = 0; j < grid[0].length; ++j){
                if(grid[i][j] == 1){
                    f = true;
                    dfs(grid, i, j);
                    break;
                }
            }
        }
        for(int i = 0; i < grid.length; ++i){
            for(int j = 0; j < grid[0].length; ++j){
                if(grid[i][j] == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        while(!q.isEmpty()){
            int len = q.size();
            cnt++;
            while(len-- > 0){
                int[] p = q.poll();
                int x = p[0];
                int y = p[1];
                for(int[] pos : dirs){
                    if(x + pos[0] >= 0 && x + pos[0] < grid.length && y + pos[1] >= 0 && y + pos[1] < grid[0].length && grid[x + pos[0]][y + pos[1]] != 1) {
                        if(grid[x + pos[0]][y + pos[1]] == 2) return cnt - 1;
                        grid[x + pos[0]][y + pos[1]] = 1;
                        q.offer(new int[]{x + pos[0], y + pos[1]});
                    }
                }
            }
        }

        return cnt - 1;
    }

    public void dfs(int[][] grid, int curx, int cury){
        if(curx < 0 || cury < 0 || curx >= grid.length || cury >= grid[0].length) return;
        if(grid[curx][cury] == 0 || grid[curx][cury] == 2) {
            return;
        }
        grid[curx][cury] = 2;
        for(int[] pos : dirs){
            dfs(grid, curx + pos[0], cury + pos[1]);
        }
    }
}
