package problems;
//41
//要求时间复杂度O(n),空间复杂度O(1)
//首先数组长度为n，如果数组中存在1~n的全部元素，那么缺失的元素为n+1，否则缺失的元素在1~n之间
//该解法的目的是在数组中表示出1~n，利用数组的连续性特点直接找到最小的符合题意的正数
//利用hash方法，原地hash和数组下标有关。数组下标为0~n-1，因此一个1~n范围中的正数如果出现，就标记n-1下标位置
//可以考虑将nums[n-1]标记为负数。这样，就需要先对nums预处理，将数组中所有的非正数变为正数。为了不影响答案，将它们都改为n+1
//如果一个正数出现在1~n的范围内，就把nums[nums[i] - 1]改为-nums[nums[i] - 1]；如果当前nums[i]已经是负值，需要取绝对值进行判断
//如果修改下标时发现对应的下标已经是负值，说明之前有一个相同的数字，不需要再修改
//最后，检查数组，如果一个位置上的数字是正数，说明没有被标记，直接返回。如果都是负数，应返回n+1
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; ++i) {
            if(nums[i] <= 0) nums[i] = n + 1;
        }
        for(int i = 0; i < n; ++i) {
            int j = Math.abs(nums[i]);
            if(j >= 1 && j <= n && nums[j - 1] > 0) nums[j - 1] *= -1;
        }
        for(int i = 0; i < n; ++i) {
            if(nums[i] > 0) return i + 1;
        }
        return n + 1;
    }
}
