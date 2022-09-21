package problems;

//整数大顶堆
public class MyHeap {
    //堆大小
    private int size;
    private int[] heap;
    //堆范围
    private int idx;

    public MyHeap(){};

    public MyHeap(int size){
        this.size = size;
        this.heap = new int[size + 1];
        this.idx = 1;
    }

    public MyHeap(int[] nums){
        this.size = nums.length;
        this.heap = new int[size + 1];
        this.idx = 1;
        for(int i = 0; i < size; ++i){
            insert(nums[i]);
        }
    }

    public boolean insert(int i){
        if(idx == size + 1) return false;
        heap[idx++] = i;
        up(idx - 1);
        return true;
    }

    public boolean remove(int pos){
        if(idx == 1) return false;
        swap(heap, pos, --idx);
        heapify(pos);
        return true;
    }

    public boolean removeFirst(){
        return remove(1);
    }

    public int getSize(){
        return this.size;
    }

    public int getCurSize(){
        return this.idx - 1;
    }

    public int getFirst(){
        if(idx == 0) throw new IndexOutOfBoundsException();
        return heap[1];
    }

    public void printHeap(){
        for (int i = 1; i < idx; ++i){
            if(i == 1) System.out.print("[");
            if(i != idx - 1){
                System.out.printf("%d, ", heap[i]);
            }else{
                System.out.printf("%d]\n", heap[i]);
            }
        }
    }

    private void up(int pos){
        //为保证堆索引不变，用递归方式实现
        if(pos == 1 || heap[pos] <= heap[pos / 2]) return;
        swap(heap, pos, pos / 2);
        up(pos / 2);
    }

    //down
    private void heapify(int pos){
        int max = pos, i = pos;
        //注意不能i++
        while(i < idx){
            if((i << 1) < idx && heap[max] < heap[2 * i]){
                max = i << 1;
            }
            if((i << 1 | 1) < idx && heap[max] < heap[2 * i + 1]){
                max = (i << 1 | 1);
            }
            if(max == i) break;
            swap(heap, i, max);
            i = max;
        }
    }

    private void swap(int[] nums, int i, int j){
        if(nums[i] != nums[j]){
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
}