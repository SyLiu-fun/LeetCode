package problems;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public int[][] ballGame(int num, String[] plate) {
        int len = plate.length, len1 = plate[0].length();
        char[][] c = new char[len][len1];
        List<int[]> l = new ArrayList();
        for(int i = 0; i < len; ++i) {
            char[] temp = plate[i].toCharArray();
            c[i] = temp;
        }
        for(int i = 1; i < len - 1; ++i){

            if(c[i][0] == '.' && dfs(c, num, i, 0, 3)) l.add(new int[]{i, 0});

            if(c[i][len1 - 1] == '.' && dfs(c, num, i, len1 - 1, 1)) l.add(new int[]{i, len1 - 1});
        }
        for(int i = 1; i < len1 - 1; ++i){

            if(c[0][i] == '.' && dfs(c, num, 0, i, 2)) l.add(new int[]{0, i});

            if(c[len - 1][i] == '.' && dfs(c, num, len - 1, i, 0)) l.add(new int[]{len - 1, i});
        }
        int[][]ans = new int[l.size()][2];
        for(int i = 0; i < l.size(); ++i){
            ans[i] = l.get(i);
        }
        return ans;
    }

    public boolean dfs(char[][] c, int step, int curx, int cury, int dir){
        if(curx < 0 || curx >= c.length || cury < 0 || cury >= c[0].length || step < 0) return false;
        if(step >= 0 && c[curx][cury] == 'O') return true;
        boolean res;
        if(dir == 0){
            if(c[curx][cury] == 'E') res = dfs(c, step - 1, curx, cury + 1, 3);
            else if(c[curx][cury] == 'W') res = dfs(c, step - 1, curx, cury - 1, 1);
            else res = dfs(c, step - 1, curx - 1, cury, 0);
        } else if(dir == 1){
            if(c[curx][cury] == 'E') res = dfs(c,step - 1, curx - 1, cury, 0);
            else if(c[curx][cury] == 'W') res = dfs(c, step - 1, curx + 1, cury, 2);
            else res = dfs(c, step - 1, curx, cury - 1, 1);
        } else if(dir == 2){
            if(c[curx][cury] == 'E') res = dfs(c, step - 1, curx, cury - 1, 1);
            else if(c[curx][cury] == 'W') res = dfs(c, step - 1, curx, cury + 1, 3);
            else res = dfs(c, step - 1, curx + 1, cury, 2);
        } else {
            if(c[curx][cury] == 'E') res = dfs(c, step - 1, curx + 1, cury, 2);
            else if(c[curx][cury] == 'W') res = dfs(c, step - 1, curx - 1, cury, 0);
            else res = dfs(c, step - 1, curx, cury + 1, 3);
        }

        return res;
    }
}
