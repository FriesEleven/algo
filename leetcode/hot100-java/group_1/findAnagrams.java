/*
 * 438. 找到字符串中所有字母异位词
 *
 * 给定两个只包含小写英文字母的字符串 s 和 p，找出 s 中所有与 p 互为字母
 * 异位词的连续子串，并返回这些子串的起始下标；答案顺序不限。
 *
 * 算法实现说明：
 * 1. targetCounts 统计 p 的字母次数，windowCounts 统计 s 中第一个同长度窗口的字母次数。
 * 2. 两个计数数组相等时，窗口与 p 包含完全相同的字母，把窗口起点加入结果。
 * 3. 窗口每次右移一格：减去离开的字符、加上新进入的字符，再比较计数，直到扫描完整个 s。
 *
 * 时间复杂度：O(|s| + |p|)；除返回结果外，空间复杂度：O(1)。
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }

        int[] windowCounts = new int[26];
        int[] targetCounts = new int[26];
        for (int i = 0; i < p.length(); i++) {
            windowCounts[s.charAt(i) - 'a']++;
            targetCounts[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(windowCounts, targetCounts)) {
            result.add(0);
        }

        for (int right = p.length(); right < s.length(); right++) {
            windowCounts[s.charAt(right - p.length()) - 'a']--;
            windowCounts[s.charAt(right) - 'a']++;
            if (Arrays.equals(windowCounts, targetCounts)) {
                result.add(right - p.length() + 1);
            }
        }
        return result;
    }
}
