package problems;

//927
public class ThreeEqualParts {
    public int[] threeEqualParts(int[] arr) {
        int len = arr.length, cnt = 0;
        int[] ans = new int[]{-1, -1};
        for (int k : arr) {
            cnt += k == 1 ? 1 : 0;
        }
        if(cnt == 0) return new int[]{0, len - 1};
        if(cnt % 3 != 0) return new int[]{-1, -1};
        cnt /= 3;
        int l = 0, r = 0, i = 0, j = len - 1, zeros = 0;
        for(; i < len; ++i) {
            if(arr[i] == 1) l++;
            if(l == cnt) break;
        }
        while(arr[j] == 0) {
            zeros++; j--;
        }
        i += zeros; j = i + 1;
        for(; j < len; ++j) {
            if(arr[j] == 1) r++;
            if(r == cnt) break;
        }
        j += zeros + 1;
        int k1 = 0, k2 = i + 1, k3 = j;
        while(arr[k1] == 0 && k1 <= i) k1++;
        while(arr[k2] == 0 && k2 < j) k2++;
        while(arr[k3] == 0 && k3 < len) k3++;
        if(k1 > i || k2 >= j || k3 >= len) return new int[]{-1, -1};
        for(; k1 <= i && k2 < j && k3 < len; k1++, k2++, k3++) {
            if(arr[k1] == arr[k2] && arr[k2] == arr[k3]) continue;
            return new int[]{-1, -1};
        }
        if(k1 != i + 1 || k2 != j || k3 != len) return new int[]{-1, -1};
        return new int[]{i, j};
    }
}
