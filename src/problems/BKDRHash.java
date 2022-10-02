package problems;

public class BKDRHash {
    private final int M = 131; //取131、1331、13331...
    //子字符串（前缀）的哈希值
    private int[] h;
    //每个字符对应的权值
    private int[] p;
    //计算hash
    public void cal_hash(String s){
        int len = s.length();
        h = new int[len];
        p = new int[len];
        h[0] = s.charAt(0); p[0] = 1;
        for(int i = 1; i < len; ++i) {
            h[i] = h[i - 1] * M + s.charAt(i);
            p[i] = p[i - 1] * M;
        }
    }

    public int get(int l, int r){
        if(l == 0) return h[r];
        else return h[r] - h[l - 1] * p[r - l + 1];
    }
}
