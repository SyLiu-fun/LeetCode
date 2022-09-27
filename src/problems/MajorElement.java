package problems;

//169
//摩尔投票法，时间复杂度O(n), 空间复杂度O(1)
public class MajorElement {
    //维护计数器和当前候选元素
    //计数器为0时，选择当前元素为候选元素，否则，当前元素等于候选元素时，cnt++，不同时cnt--
    public int majorityElement(int[] nums) {
        int len = nums.length, cnt = 0, ans = nums[0];
        for(int i = 0; i < len; ++i){
            if(cnt == 0){
                ans = nums[i];
                cnt++;
            } else {
                if(nums[i] == ans) cnt++;
                else cnt--;
            }
        }
        return ans;
    }
}
