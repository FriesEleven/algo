/*
 * 76. 最小覆盖子串
 *
 * 给定字符串 s 和 t，在 s 中寻找一个长度最短的连续子串，使它包含 t 中的每个
 * 字符及其所需出现次数；若不存在则返回空字符串。测试数据保证最短答案唯一。
 * 字符串由英文字母组成。
 *
 * 算法实现说明：
 * 1. need[c] 记录窗口还需要字符 c 的数量，remaining 记录尚未覆盖的字符总数。
 * 2. right 向右扩展窗口；若新字符仍有欠缺就减少 remaining，随后统一减少它在 need 中的计数。
 * 3. remaining 为 0 时，窗口已经覆盖 t；不断右移 left、更新最短答案，直到移走一个必需字符使窗口失效。
 * 4. 扫描结束后按 bestStart、bestLength 截取最短窗口；从未形成有效窗口则返回空字符串。
 *
 * 时间复杂度：O(|s| + |t|)；空间复杂度：O(|Σ|)，字符集大小为常数。
 */
class Solution {
    public String minWindow(String s, String t) {
        if (t.isEmpty() || s.length() < t.length()) {
            return "";
        }

        int[] need = new int[128];
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }

        int remaining = t.length();
        int left = 0;
        int bestStart = 0;
        int bestLength = Integer.MAX_VALUE;
        for (int right = 0; right < s.length(); right++) {
            char added = s.charAt(right);
            if (need[added] > 0) {
                remaining--;
            }
            need[added]--;

            while (remaining == 0) {
                int windowLength = right - left + 1;
                if (windowLength < bestLength) {
                    bestLength = windowLength;
                    bestStart = left;
                }
                char removed = s.charAt(left++);
                need[removed]++;
                if (need[removed] > 0) {
                    remaining++;
                }
            }
        }
        return bestLength == Integer.MAX_VALUE
                ? ""
                : s.substring(bestStart, bestStart + bestLength);
    }
}
