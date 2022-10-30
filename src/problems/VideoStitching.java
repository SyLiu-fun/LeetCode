package problems;

// 1024
public class VideoStitching {
    public int videoStitching(int[][] clips, int time) {
        int ans = 0, idx = 0;
        int[] right = new int[time + 1];
        for(int i = 0; i <= time; ++i) {
            for(int[] clip : clips) {
                if(clip[0] <= i && clip[1] > i) {
                    right[i] = Math.max(clip[1], right[i]);
                }
            }
        }
        while(idx < time) {
            if(right[idx] <= idx) return -1;
            idx = right[idx];
            ans++;
        }
        return ans;
    }
}
