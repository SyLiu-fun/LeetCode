package problems;

import java.util.*;

public class DFS {
    List<List<Integer>> ans = new LinkedList();
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        int[] f = new int[21];
        for(int i = 0; i < len; ++i){
            Arrays.fill(f, 1);
            f[nums[i] + 10] = 0;
            Deque<Integer> temp = new LinkedList();
            temp.push(nums[i]);
            dfs(len - 1, f, nums, temp);
        }
        return ans;
    }

    public void dfs(int len, int[] f, int[] nums, Deque<Integer> temp){
        if(len == 0){
            ans.add(new LinkedList(temp));
            return;
        }
        for(int i = 0; i < nums.length; ++i){
            if(f[nums[i] + 10] == 1){
                temp.push(nums[i]);
                f[nums[i] + 10] = 0;
                dfs(len - 1, f, nums, temp);
                temp.pop();
                f[nums[i] + 10] = 1;
            }
        }
    }
}
