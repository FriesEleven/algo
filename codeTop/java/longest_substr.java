package codeTop.java;

public class longest_substr {
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[256];
        int l = 0, r = 0,ans=0;
        while (r < s.length()) {
            map[s.charAt(r)]++;
            while (map[s.charAt(r)] > 1) {
                map[s.charAt(l++)]--;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }
        return ans;
    }
}
