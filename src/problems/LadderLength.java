package problems;

import java.util.*;

//127
//BFS(注意是无向图)
public class LadderLength {
    Set<String> set = new HashSet<>();
    Map<String, List<String>> m = new HashMap<>();
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(beginWord.equals(endWord)) return 0;
        if(!wordList.contains(beginWord)) wordList.add(beginWord);
        Queue<String> q = new LinkedList<>();
        int ans = 0;
        for(String word : wordList) {
            set.add(word);
            m.put(word, new ArrayList<>());
        }
        for(String word : wordList) generate(word);
        q.offer(beginWord);
        while(!q.isEmpty()) {
            int len = q.size();
            ans++;
            while(len-- > 0) {
                String word = q.poll();
                set.remove(word);
                List<String> list = m.get(word);
                for(String s : list) {
                    if(s.equals(endWord)) return ans + 1;
                    if(set.contains(s)) q.offer(s);
                }
            }
        }
        return 0;
    }

    public void generate(String word) {
        int n = word.length();
        List<String> ans = m.get(word);
        for(int i = 0; i < n; ++i) {
            StringBuilder sb = new StringBuilder(word);
            char c = word.charAt(i);
            for(char ch = (char)(c + 1); ch <= 'z'; ch++) {
                sb.replace(i, i + 1, String.valueOf(ch));
                String a = sb.toString();
                if(set.contains(a)) {
                    ans.add(a);
                    List<String> tmp = m.get(a);
                    tmp.add(word);
                    m.put(a, tmp);
                }
            }
        }
        m.put(word, ans);
    }
}
