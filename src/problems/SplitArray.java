package problems;

// 410
// 最小化最大值典型问题，确定答案都可以取到后，通过二分的方式搜索答案
public class SplitArray {
    public int splitArray(int[] nums, int k) {
        int mx = 0, tot = 0;
        for(int i : nums) {
            mx = Math.max(i, mx);
            tot += i;
        }
        while(mx <= tot) {
            int m = mx + tot >> 1;
            if(cut(nums, m, k)) mx = m + 1;
            else tot = m - 1;
        }
        return mx;
    }

    public boolean cut(int[] nums, int x, int k) {
        int tot = 0, cnt = 0;
        for(int i : nums) {
            if(tot + i > x) {
                tot = i;
                cnt++;
            } else tot += i;
            if(cnt >= k) return true;
        }
        return false;
    }
}