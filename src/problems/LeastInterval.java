package problems;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

//621
//艰难地模拟，注意边界条件
//使用贪心方法，优先运行剩余最多的任务，维护下一次运行时间以及剩余任务数
public class LeastInterval {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Pair<Integer, Integer>> m = new HashMap<>();
        int len = tasks.length, ans = 1;
        for(int i = 0; i < len; ++i){
            //<下一次运行时间, 剩余任务个数>
            if(m.containsKey(tasks[i])){
                Pair<Integer, Integer> p = m.get(tasks[i]);
                m.put(tasks[i], new Pair<Integer, Integer>(p.getKey(), p.getValue() + 1));
            } else {
                m.put(tasks[i], new Pair<Integer, Integer>(1, 1));
            }
        }
        while(!m.isEmpty()){
            Pair<Integer, Integer> p;
            int max = 0;
            //next表示下一个运行时间
            int next = Integer.MAX_VALUE;
            char cur = tasks[0];
            for(Map.Entry<Character, Pair<Integer, Integer>> e : m.entrySet()){
                p = e.getValue();
                //当前任务处于冷却中
                if(p.getKey() > ans) continue;
                //找到剩余最多的任务
                if(p.getValue() > max){
                    max = p.getValue();
                    cur = e.getKey();
                }
            }
            //执行cur任务
            p = m.get(cur);
            if(p.getValue() == 1) m.remove(cur);
            else m.put(cur, new Pair<Integer, Integer>(ans + n + 1, p.getValue() - 1));
            if(m.isEmpty()) break;
            for(Pair<Integer, Integer> p1 : m.values()){
                if(p1.getKey() < next) {
                    next = p1.getKey();
                }
            }
            if(ans + 1 < next) ans = next;
            else ans++;
        }
        return ans;
    }
}
