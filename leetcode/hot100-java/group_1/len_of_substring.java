/*
 * 3. 无重复字符的最长子串
 *
 * 给定一个字符串 s，找出其中不含重复字符的最长连续子串，并返回该子串的长度。
 * 字符串由英文字母、数字、符号和空格等 ASCII 字符组成。
 *
 * 算法实现说明：
 * 1. nextPosition[c] 保存字符 c 上次出现位置的下一位，left 表示当前无重复窗口的左端点。
 * 2. 扫描到字符 current 时，把 left 推进到原位置和 nextPosition[current] 中较大者，排除可能的重复字符。
 * 3. 用当前窗口长度更新 best，再记录 current 的最新下一位置；右端点只需单向扫描一次。
 *
 * 时间复杂度：O(n)；空间复杂度：O(|Σ|)，其中字符集大小 |Σ| 为常数。
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] nextPosition = new int[128];
        int left = 0;
        int best = 0;
        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            left = Math.max(left, nextPosition[current]);
            best = Math.max(best, right - left + 1);
            nextPosition[current] = right + 1;
        }
        return best;
    }
}
