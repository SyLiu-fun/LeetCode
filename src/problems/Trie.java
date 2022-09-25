package problems;

//208
//字典树模板
public class Trie {
    private boolean isEnd;
    private Trie[] next;
    //统计字符出现次数
    //private int time;

    public Trie() {
        isEnd = false;
        next = new Trie[26];
        //time = 0;
    }

    public void insert(String word) {
        int len = word.length();
        Trie tr = this;
        for(int i = 0; i < len; ++i){
            char c = word.charAt(i);
            if(tr.next[c - 'a'] == null){
                tr.next[c - 'a'] = new Trie();
            }
            tr = tr.next[c - 'a'];
        }
        tr.isEnd = true;
    }

    public boolean search(String word) {
        Trie tr = this;
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(tr.next[c - 'a'] == null) return false;
            tr = tr.next[c - 'a'];
        }
        return tr.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie tr = this;
        for(int i = 0; i < prefix.length(); ++i){
            char c = prefix.charAt(i);
            if(tr.next[c - 'a'] == null) return false;
            tr = tr.next[c - 'a'];
        }
        return true;
    }
}
