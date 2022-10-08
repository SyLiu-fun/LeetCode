package problems;

import java.util.Arrays;
import java.util.Comparator;

//870
//贪心法，田忌赛马的思想，对于nums2中的每个数，在nums1中找到最小的一个比它更大的数，找不到就在该位置放置nums1中最小的且未被选中的数
//***在实现上，对两个数组的下标排序，从小到大遍历nums1中每个数
//对于nums2，使用双指针l,r，l表示nums2中该元素可以在nums1中找到比它更大的数，r表示该位置只能填充nums1中的最小数
//每次比较nums1、nums2中最小的未被选择的元素
public class AdvantageCount {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int len = nums1.length;
        Integer[] idx1 = new Integer[len];
        Integer[] idx2 = new Integer[len];
        for(int i = 0; i < len; ++i) {
            idx1[i] = i;
            idx2[i] = i;
        }
        // Arrays.sort(idx1, (a, b) -> nums1[a] - nums1[b]);
        // Arrays.sort(idx2, (a, b) -> nums2[a] - nums2[b]);
        Arrays.sort(idx1, Comparator.comparingInt(a -> nums1[a]));
        Arrays.sort(idx2, Comparator.comparingInt(a -> nums2[a]));
        int l = 0, r = len - 1;
        for(int i = 0; i < len; ++i) {
            if(nums1[idx1[i]] <= nums2[idx2[l]]) nums2[idx2[r--]] = nums1[idx1[i]];
            else nums2[idx2[l++]] = nums1[idx1[i]];
        }
        return nums2;
    }
}
