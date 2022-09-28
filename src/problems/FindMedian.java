package problems;

//4
//核心思想：转化为求两数组合并后第k大的元素
public class FindMedian {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int len = m + n;
        //如果m+n是奇数，中位数（k）是(len/2+1)
        //如果是偶数，中位数是len/2+1和len/2的平均值
        if((len & 1) == 1) return getKthNum(nums1, nums2, 0, 0, len / 2 + 1) * 1.0;
        else {
            int i = getKthNum(nums1, nums2, 0, 0, len / 2);
            int j = getKthNum(nums1, nums2, 0, 0, len / 2 + 1);
            return (i + j) * 1.0 / 2;
        }
    }

    public int getKthNum(int[] nums1, int[] nums2, int s1, int s2, int k){
        //p1,p2指向需要比较的位置
        int m = nums1.length, n = nums2.length, p1 = 0, p2 = 0;
        while(k != 1){
            //如果k/2大于某个数组的剩余长度，直接指向尾元素，否则指向start+k/2的位置
            //比较两个start + k/2位置的数字，舍弃掉较小数组中nums[start + k/2]以及之前的所有元素，k需要减去舍弃数字的长度
            //nums[start + k/2]及之前的元素必不可能是第k大的元素
            if(m - s1 < k / 2) p1 = m - 1;
            else p1 = s1 - 1 + k / 2;
            if(n - s2 < k / 2) p2 = n - 1;
            else p2 = s2 - 1 + k / 2;
            if(nums1[p1] > nums2[p2]) {
                k -= p2 - s2 + 1;
                s2 = p2 + 1;
            } else {
                k -= p1 - s1 + 1;
                s1 = p1 + 1;
            }
            if(s1 >= m || s2 >= n){
                if(s1 >= m) return nums2[s2 + k - 1];
                else return nums1[s1 + k - 1];
            }
        }
        //k = 1
        return Math.min(nums1[s1], nums2[s2]);
    }
}
