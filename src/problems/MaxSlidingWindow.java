package problems;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

//239
public class MaxSlidingWindow {
    /**
     * 方法1：堆
     * 使用大顶堆来记录滑动窗口中的最大值，堆顶存数组的最大值和数组下标。查找滑动窗口中的最大值时，只需要检查堆顶元素
     * 是否在当前的滑窗范围内，把不在滑窗范围内的堆顶元素删去
     *
     * 思维陷阱：滑动窗口滑过的时候，并不需要把滑出的元素删除掉，只需要把滑入的元素加入堆中。因为滑入和滑出的元素不一定会影响到
     * 最终结果
     **/
    public int[] maxSlidingWindow1(int[] nums, int k) {
        Queue<int[]> q = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int n = nums.length, l = 0;
        int[] ans = new int[n - k + 1];
        for(int i = 0; i < k; ++i) {
            q.offer(new int[]{nums[i], i});
        }
        while(l + k - 1 < n) {
            while(!q.isEmpty() && q.peek()[1] < l) q.poll();
            ans[l] = q.peek()[0];
            if(l + k < n) q.offer(new int[]{nums[l + k], l + k});
            l++;
        }
        return ans;
    }

    /**
     * 方法2：单调队列
     * 思想：1. 遍历元素时，后遍历的元素下标一定在先遍历的元素下标之后
     * 2. 如果滑动窗口中一个元素nums[j]的下标大于另一个元素nums[i]的下标，即j > i，且nums[j] > nums[i]，那么nums[i]一定不会出现在
     * 答案中。所以，滑动窗口新滑入的元素可以和前面的元素从后往前比较，如果大于前面的某个元素，可以确定前面的元素不在答案中
     * 3. 从上面两点分析，可以采用单调队列或者单调栈的结构。最早进入的元素是窗口的最大值，又要保证前面的元素在滑窗中，
     * 因此选择单调队列。
     * 4. 使用单调队列时，头部元素如果不在滑窗范围内，只出队队首一个元素。
     * 5. 直观来看，这题的双向单减队列的特点有两个。一是队列中包含从i到i+k的若干元素下标，且队首元素是滑窗中的最大值，
     * 二是如果一个元素不是某个滑窗中的最大值，它一定会被后续的元素替换掉。
     **/
    public int[] maxSlidingWindow2(int[] nums, int k) {
        Deque<Integer> dk = new ArrayDeque<>();
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for(int i = 0; i < k; ++i) {
            while(!dk.isEmpty() && nums[dk.peekLast()] < nums[i]) dk.removeLast();
            dk.offerLast(i);
        }
        for(int i = 0; i + k - 1 < n; ++i) {
            int idx = dk.peekFirst();
            if(idx < i) dk.removeFirst();
            ans[i] = nums[dk.peekFirst()];
            if(i + k < n) {
                while(!dk.isEmpty() && nums[dk.peekLast()] < nums[i + k]) dk.removeLast();
            }
            dk.offerLast(i + k);
        }
        return ans;
    }

    /**
     * 方法3：线段树
     **/
    int[] tree;
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        tree = new int[n << 2];
        build(0, n - 1, 1, nums);
        for(int i = 0; i + k - 1 < n; ++i) {
            ans[i] = query(i, i + k - 1, 0, n - 1, 1);
        }
        return ans;
    }

    public void pushUp(int rt) {
        tree[rt] = Math.max(tree[rt << 1], tree[rt << 1 | 1]);
    }

    public void build(int l, int r, int rt, int[] nums) {
        if(l == r) {
            tree[rt] = nums[l];
            return;
        }
        int m = l + r >> 1;
        build(l, m, rt << 1, nums);
        build(m + 1, r, rt << 1 | 1, nums);
        pushUp(rt);
    }

    public int query(int L, int R, int l, int r, int rt) {
        if(L <= l && R >= r) {
            return tree[rt];
        }
        int m = l + r >> 1, lmax = Integer.MIN_VALUE, rmax = Integer.MIN_VALUE;
        if(L <= m) lmax = query(L, R, l, m, rt << 1);
        if(R > m) rmax = query(L, R, m + 1, r, rt << 1 | 1);
        return Math.max(lmax, rmax);
    }
}
