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
        //a > b，说明不够除，商为0
        if(a > b) return 0;
        //a < b,够÷，商至少为1，step记录当前除数的倍数
        int cnt = 1, step = b;
        //step + step >= a判断被除数是否还能除以step，如果够除，除数翻倍，除数的个数也翻倍
        //如果step < a/2，说明不能继续翻倍
        //魔鬼细节，如果写成step + step >= a, 当a为MIN_VALUE时，step + step可能会溢出，换成减法则不会
        while(step >= a - step){
            cnt += cnt;
            step += step;
        }
        //当前迭代含有cnt个b，余数小于step，大于b，继续递归计算还有多少个b
        return cnt + div(a - step, b);
    }


}
