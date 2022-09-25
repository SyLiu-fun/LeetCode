package problems;

import java.util.Arrays;

//1652
//前缀和解决，注意推导目标数组与前缀和数组的元素对应关系
public class Decrypt {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] copy = new int[n << 1], sum = new int[n << 1];
        System.arraycopy(code, 0, copy, 0, n);
        System.arraycopy(code, 0, copy, n, n);
        for(int i = 0; i < n << 1; ++i){
            if(i == 0) sum[i] = copy[i];
            else sum[i] = sum[i - 1] + copy[i];
        }
        //k > 0, code[i] = sum[i + k] - sum[i]
        //k < 0, code[i] = sum[i + n - 1] - sum[i + n + k - 1](防越界)
        for(int i = 0; i < n; ++i){
            if(k == 0) code[i] = 0;
            else if(k > 0) code[i] = sum[i + k] - sum[i];
            else code[i] = sum[i + n - 1] - sum[i + n + k - 1];
        }
        return code;
    }
}
