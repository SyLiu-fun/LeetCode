package problems;

//289
//原地算法，在遍历位置用其他状态来表示转化过程。2：1 -> 0, 3: 0 -> 1
//有多余位置用多余位置标识，不能的话用其他状态来标识
public class GameOfLife {
    public void gameOfLife(int[][] b) {
        int m = b.length, n = b[0].length;
        int[] x = new int[]{0, -1, -1, -1, 0, 1, 1, 1}, y = new int[]{-1, -1, 0, 1, 1 , 1, 0, -1};
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                int tot = 0;
                for(int k = 0; k < 8; ++k) {
                    int _i = i + x[k], _j = j + y[k];
                    if(_i < 0 || _i >= m || _j < 0 || _j >= n) continue;
                    if(b[_i][_j] == 1 || b[_i][_j] == 2) tot++;
                }
                if(b[i][j] == 1 && (tot < 2 || tot > 3)) b[i][j] = 2;
                if(b[i][j] == 0 && tot == 3) b[i][j] = 3;
            }
        }
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(b[i][j] == 2) b[i][j] = 0;
                if(b[i][j] == 3) b[i][j] = 1;
            }
        }
    }
}
