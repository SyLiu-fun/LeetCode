package problems;

public class quickPow {
    public int quickPow(int a, int b){
        int num = 1;
        while(b > 0){
            if((b&1) == 1) num *= a;
            a *= a;
            b >>= 1;
        }
        return num;
    }
}
