package problems;

public class MyDivide {
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE){
            if(divisor == -1) return Integer.MAX_VALUE;
            if(divisor == 1) return dividend;
        };
        if(divisor == Integer.MIN_VALUE){
            if(dividend == Integer.MIN_VALUE) return 1;
            else return 0;
        }
        int f = (dividend >= 0 && divisor > 0) || (dividend <= 0 && divisor < 0) ? 1 : -1;
        if(dividend > 0) dividend = -dividend;
        if(divisor > 0) divisor = -divisor;
        return f * div(dividend, divisor);

    }

    //被除数是a，除数是b，均为负数
    public int div(int a, int b){
        if(a > b) return 0;
        int cnt = 1, step = b;
        while(step >= a - step){
            cnt += cnt;
            step += step;
        }
        return cnt + div(a - step, b);
    }


}
