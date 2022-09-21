package problems;

import java.util.ArrayList;
import java.util.List;

public class Yanghui {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0;i<numRows;++i){
            List<Integer> l = new ArrayList<>();
            for(int j = 0;j<=i;++j){
                if(j == 0 || j == i) l.add(1);
                else{
                    l.add(j, ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
            }
            ans.add(l);
        }
        return ans;
    }
}
