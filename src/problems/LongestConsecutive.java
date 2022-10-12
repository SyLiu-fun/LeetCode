package problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//!!!128
//时间复杂度O(n)
public class LongestConsecutive {
    /**
     * 方法1：hash
     * 遍历数组中的每个元素i，在hashset中查找i+1, i+2,...,i+n.对于每个元素，如果hashset中存在i-1，不用进行查找
     * 因为以i-1开头的序列长度一定大于以i开头的序列长度。因此，从连续序列的最小元素开始查找
     **/
    public int longestConsecutive1(int[] nums) {
        Set<Integer> s = new HashSet<>();
        int ans = 0;
        for(int i : nums) {
            s.add(i);
        }
        for(int i : nums) {
            //说明在连续序列中i不是最小元素
            if(s.contains(i - 1)) continue;
            int cur = 0, j = i;
            while(s.contains(j++)) cur++;
            ans = Math.max(cur, ans);
        }
        return ans;
    }

    /**
     * 方法2 并查集
     * 该方法有非常多的细节。首先，把每个元素视为单独的连通分量。初始化并查集m，m是一个哈希表，key表示当前元素的值，
     * value表示该元素最远能够到达的右边界；cnt哈希表记录以该元素为根结点的连通分量中元素的个数。
     * 初始化时，令所有元素的右边界为自身。遍历Nums中的全部元素，对于每个元素i，如果存在i+1，且属于不同连通分量
     * 将i的根节点合并到i+1的根节点，同时将i连通分量的数量加到i+1的连通分量上
     *
     * 在这个合并的过程中，并不用具体考虑每个结点的父结点的修改顺序。因为他们只要位于一个连通分量中，最终总数量一定能加到根节点
     * 的cnt上，这个过程与添加顺序无关。举例：三个结点x,y,z, z是x和y的根节点，y是x的父结点。假如先遍历到y，y的父结点就是z，
     * 再遍历x结点时自然就能找到z结点。如果先遍历到的是x结点，此时x的父结点设为y，y的cnt对应值修改为cnt(y) + cnt(x)，再遍历y时，
     * 就可以把cnt(y) + cnt(x)加到cnt(z).
     **/
    Map<Integer, Integer> m = new HashMap<>();
    Map<Integer, Integer> cnt = new HashMap<>();
    public int longestConsecutive2(int[] nums) {
        int ans = 0;
        for(int i : nums) {
            m.put(i, i);
            cnt.put(i, 1);
        }
        //如果不使用cnt哈希表记录每个连通分量的元素个数，需要再进行一次遍历，将一部分元素的父结点进行修改，使一个连通分量的元素根节点为具体某个元素
        for(int i : nums) {
            if(m.containsKey(i + 1)) {
                //这里必须要找i的根节点，因为如果出现了重复元素，m中i的父结点很可能不是i，需要找到i的根节点
                int a = find(i);
                int b = find(i + 1);
                if(a == b) continue;
                m.put(a, b);
                //！！！注意：一定要把数量加到右边连通分量的根节点上
                cnt.put(b, cnt.get(b) + cnt.get(a));
            }
        }
        for(int i : nums) {
            ans = Math.max(ans, cnt.get(i));
        }
        return ans;
    }

    public int find(int x) {
        if(x != m.get(x)) {
            int fa = find(m.get(x));
            m.put(x, fa);
        }
        return m.get(x);
    }
}