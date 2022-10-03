package problems;

//树状数组，以求和操作为例，树状数组中每个元素都是一个区间的前缀和，优点在于可以动态修改前缀和
//相比于线段树，树状数组实现更简单；树状数组维护的是前缀和，线段树维护的是区间和
//https://oi-wiki.org/ds/fenwick/
public class BIT {
    //a数组管理arr数组，下标从1开始
    int[] a;
    int n;

    public BIT(int len){
        n = len + 1;
        a = new int[n];
    }

    //使用lowbit计算a数组的管理区间。a[i]的管理区间是[i - lowbit(i) + 1, i]，是一部分区间的和
    public int lowbit(int x){
        return x & -x;
    }

    //单点修改操作，arr[i] + x; 前缀和思想
    //修改i所在区间以及其所有父结点，由树状数组的定义可知，i一定在a[i]的管理区间内
    public void add(int i, int x) {
        while(i <= n){
            a[i] += x;
            i += lowbit(i);
        }
    }

    //求arr1 .... arri的和, 即求部分前缀的加和
    public int query(int i){
        int ans = 0;
        while(i >= 1){
            ans += a[i];
            i -= lowbit(i);
        }
        return ans;
    }

}
