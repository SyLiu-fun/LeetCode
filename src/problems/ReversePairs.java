package problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

//493
//树状数组BIT的应用
public class ReversePairs {
    int[] a;
    public int reversePairs(int[] nums) {
        int ans = 0;

        //由于nums中的数字是整个整型范围，直接分配数组空间会MLE，需要用map映射
        //使用TreeSet保证元素的大小顺序
        Set<Long> s = new TreeSet<>();
        Map<Long, Integer> m = new HashMap<>();
        for(long num : nums){
            s.add(num);
            //这里需要把num和num *2都添加到map中，因为给树状数组分配的空间必须大于查询的空间
            s.add(num * 2);
        }
        int idx = 1;
        long max = 0L;
        //set中元素的大小顺序与idx大小顺序相同，一一映射
        for(Long i : s) {
            m.put(i, idx++);
            max = i;
        }
        a = new int[idx];
        for (int num : nums) {
            //查询 > nums[j]*2的元素，用前缀和相减的方式
            int x = query(m.get((long) num * 2));
            ans += query(m.get(max)) - x;
            add(m.get((long) num), 1);
        }
        return ans;
    }

    public int lb(int x){
        return x & -x;
    }
    public void add(int i, int v) {
        int n = a.length;
        while(i < n) {
            a[i] += v;
            i += lb(i);
        }
    }

    public int query(int i){
        int ans = 0;
        while(i > 0) {
            ans += a[i];
            i -= lb(i);
        }
        return ans;
    }
}
