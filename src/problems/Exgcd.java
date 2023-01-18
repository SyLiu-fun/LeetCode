package problems;

// 扩展欧几里得算法
public class Exgcd {
    // x, y一定设成全局的，因为需要在递归过程中进行修改
    static long x = 0, y = 0;
    // 求解 ax + by = m, 如果m被gcd(a, b)整除，方程有解
    // 根据贝祖定理，当 b = 0 时，a * 1 + b * 0 = gcd(a, 0) = a, 即（1,0）是递归终止时的一组解
    public static long exgcd(long a, long b) {
        if(b == 0) {
            x = 1; y = 0;
            return a;
        }
        long ans = exgcd(b, a % b);
        long t = x;
        x = y;
        y = t - a / b * y;
        return ans;
    }
}
