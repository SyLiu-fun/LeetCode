package problems;

//递归线段树
public class SegmentTree {

    int[] arr;
    //sum和lazy都是针对区间和设置的
    //某一个区间的和
    int[] sum;
    //对区间修改时，并不需要对区间内所有的点都进行修改，只需要修改区间的lazy标记
    int[] lazy;

    //update和change是针对区间修改设置的，比如设置某个区间的值为k
    boolean[] update;
    int[] change;

    //问题规模为n，开4*n大小的数组
    public SegmentTree(int[] nums){
        int n = nums.length + 1;
        arr = new int[n]; //开一个大小为n+1的数组，编号从1开始
        System.arraycopy(nums, 0, arr, 1, n - 1);
        this.sum = new int[n << 2];
        this.lazy = new int[n << 2];
        this.update = new boolean[n << 2];
        this.change = new int[n << 2];
    }

    //汇总左右子区间和, 左右子区间在数组中的存储位置为2*rt和2*rt+1
    //rt是数组中表示某个大区域的下标
    public void pushUp(int rt){
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    //ln, rn表示rt结点代表的区间[l...r]中左右两部分的叶子数量
    public void pushDown(int ln, int rn, int rt){
        //update是将某个区段设置为定值，如果没有该操作，update可省略
        //子区间首先判断是否有update，如果有，取消之前的lazy加操作，用change来存储update
        if(update[rt]){
            update[rt << 1] = true;
            change[rt << 1] = change[rt];
            lazy[rt << 1] = 0;

            update[rt << 1 | 1] = true;
            change[rt << 1 | 1] = change[rt];
            lazy[rt << 1 | 1] = 0;

            sum[rt << 1] = change[rt] * ln;
            sum[rt << 1 | 1] = change[rt] * rn;
            //！！！这一步是必须的，否则sum会被无限更新
            update[rt] = false;
        }

        //先检查update，后检查lazy，父结点有可能在update后进行了add
        if(lazy[rt] != 0){
            lazy[rt << 1] += lazy[rt];
            lazy[rt << 1 | 1] += lazy[rt];
            sum[rt << 1] += ln * lazy[rt];
            sum[rt << 1 | 1] += rn * lazy[rt];
            lazy[rt] = 0; //清除父结点的标记
        }
    }

    //递归建树，用sum[rt]表示[l...r]范围内的区间和
    public void build(int l, int r, int rt){
        if(l == r){
            sum[rt] = arr[l];
            return;
        }
        int m = (l + r) >> 1;
        build(l, m, rt << 1);
        build(m + 1, r, rt << 1 | 1);
        //左右子树都构建好之后，更新该节点的sum
        pushUp(rt);
    }

    //arr[i] + C后的操作
    public void add(int i, int C, int l, int r, int rt){
        //更新到了叶子结点
        if(l == r) {
            sum[rt] += C;
            return;
        }
        int m = (l + r) >> 1;
        pushDown(m - l + 1, r - m, rt);
        if(i <= m) add(i, C, l, m, rt << 1);
        else add(i, C, m + 1, r, rt << 1 | 1);
        pushUp(rt); //更新本结点信息
    }

    //[L, R]任务区间内所有数字加C后对线段树进行更新, 更新[l,r]的区间和sum[rt]
    public void add(int L, int R, int C, int l, int r, int rt){
        //任务区间完全覆盖rt结点表示的[l, r]区间, 只需要更新该区间对应结点rt的标记, sum必须实时更新
        if(L <= l && r <= R){
            sum[rt] += (r - l + 1) * C;
            lazy[rt] += C;
            return;
        }
        //不能完全覆盖时，需要首先将本结点之前存储的lazy信息下推，再储存本次的信息
        //必须先进行下推，因为父结点的lazy标记和sum还没有更新到子结点，不下推直接将最新的任务分发，查询到的子区间和不正确
        int m = (l + r) >> 1;
        pushDown(m - l + 1, r - m, rt);
        if(L <= m){
            //更新涉及到左半部分，需要将任务分发到左半部
            add(L, R, C, l, m, rt << 1);
        }
        if(R > m){
            add(L, R, C, m + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    //基本同add方法，update[rt] = true表示当前范围有更新操作，任务范围小于[l...r]时需要先对子区间进行更新
    public void update(int L, int R, int C, int l, int r, int rt){
        if(L <= l && r <= R){
            update[rt] = true;
            change[rt] = C;
            sum[rt] = C * (r - l + 1);
            lazy[rt] = 0;
            return;
        }
        int m = (l + r) >> 1;
        pushDown(m - l + 1, r - m, rt);
        if(L <= m){
            update(L, R, C, l, m, rt << 1);
        }
        if(R > m){
            update(L, R, C, m + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    public int query(int L, int R, int l, int r, int rt){
        if(L <= l && r <= R){
            return sum[rt];
        }
        int m = (l + r) >> 1;
        pushDown(m - l + 1, r - m, rt);
        //查询操作并没有对数据进行修改，如果rt结点有lazy或者update标记，说明sum值已经被更新过了，因此没有必要再更新一次
        //pushDown只是将攒下的add和update操作更新到待查询的子区间内，保证查询结果的正确
        //sum[rt]并不总等于 sum[rt << 1] + sum[rt << 1 | 1];
        //pushUp(rt);
        int res1 = 0, res2 = 0;
        if(L <= m){
            res1 = query(L, R, l, m, rt << 1);
        }
        if(R > m){
            res2 = query(L, R, m + 1, r, rt << 1 | 1);
        }
        return res1 + res2;
    }
}
