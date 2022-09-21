package problems;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class KSimilarityOfTwoString {
    int ans;
    public int kSimilarityDFS(String s1, String s2) {
        ans = Integer.MAX_VALUE;
        dfs(s1, s2, 0, 0, s1.length());
        return ans;
    }

    public int kSimilarityBFS(String s1, String s2) {
        Queue<Pair<String, Integer>> q = new LinkedList<>();
        Set<String> s = new HashSet<>();
        q.offer(new Pair<>(s1, 0));
        s.add(s1);
        int cnt = 0;
        while(!q.isEmpty()){
            int len = q.size();
            while(len-- > 0){
                Pair<String, Integer> p = q.poll();
                String cur = p.getKey();
                int pos = p.getValue();
                if(cur.equals(s2)) return cnt;
                while(pos < s1.length() && cur.charAt(pos) == s2.charAt(pos)) pos++;
                for(int i = pos + 1; i < s1.length(); ++i){
                    if(cur.charAt(i) == s2.charAt(pos) && cur.charAt(i) != s2.charAt(i)){
                        String temp = swap(cur, pos, i);
                        if(!s.contains(temp)){
                            //pos + 1代表下一次开始搜索的位置
                            q.offer(new Pair<>(temp, pos + 1));
                            s.add(temp);
                        }
                    }
                }
            }
            cnt++;
        }
        return cnt;
    }

    public void dfs(String s1, String s2, int pos, int k, int len){
        if(pos >= len){
            ans = Math.min(ans, k);
            return;
        }
        if(k > ans) return;
        if(s1.charAt(pos) == s2.charAt(pos)) dfs(s1, s2, pos + 1, k, len);
        else{
            for(int i = pos + 1; i < len; ++i){
                if(s1.charAt(i) == s2.charAt(pos) && s1.charAt(i) != s2.charAt(i)) {
                    s1 = swap(s1, pos, i);
                    dfs(s1, s2, pos + 1, k + 1, len);
                    s1 = swap(s1, pos, i);
                }
            }
        }
    }

    private String swap(String s, int i , int j){
        char[] arr = s.toCharArray();
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
        return new String(arr);
    }
}
