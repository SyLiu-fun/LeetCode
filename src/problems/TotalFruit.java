package problems;

import java.util.HashSet;
import java.util.Set;

// 904
// two pointers
public class TotalFruit {
    public int totalFruit(int[] fruits) {
        int ans = 1, n = fruits.length, cur = 0;
        Set<Integer> s = new HashSet<>();
        for(int i = 0, j = 0; i < n; ++i) {
            if(!s.contains(fruits[i])) {
                if(s.size() < 2) {
                    s.add(fruits[i]);
                    cur++;
                } else {
                    s.add(fruits[i]);
                    for(int k = i - 1; k >= j; --k) {
                        if(fruits[k] != fruits[i - 1]) {
                            s.remove(fruits[k]);
                            cur = i - k;
                            j = k + 1;
                            break;
                        }
                    }
                }
            } else cur++;
            ans = Math.max(ans, cur);
        }
        return ans;
    }
}
