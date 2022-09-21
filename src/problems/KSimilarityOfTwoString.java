package problems;

public class KSimilarityOfTwoString {
    int ans;
    public int kSimilarity(String s1, String s2) {
        ans = Integer.MAX_VALUE;
        dfs(s1, s2, 0, 0, s1.length());
        return ans;
    }

    public void dfs(String s1, String s2, int pos, int k, int len){
        if(pos >= len){
            ans = ans > k ? k : ans;
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
