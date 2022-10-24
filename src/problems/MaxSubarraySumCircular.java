package problems;

// 918
// 求最大子数组和是dp问题，不是双指针问题
// 环形数组中，最大子数组和：1. 和非环形的情况一样；2. 越界时，等于total - 最小子数组和
public class MaxSubarraySumCircular {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length, cur_max = Integer.MIN_VALUE, cur_min = Integer.MAX_VALUE, ans1 = cur_max, ans2 = cur_min, tot = 0;
        for (int num : nums) {
            cur_max = Math.max(cur_max, 0) + num;
            cur_min = Math.min(cur_min, 0) + num;
            ans1 = Math.max(cur_max, ans1);
            ans2 = Math.min(cur_min, ans2);
            tot += num;
        }
        if(ans2 == tot) return ans1;
        return Math.max(ans1, tot - ans2);
    }
}
