/*
 * 49. 字母异位词分组
 *
 * 给定一个字符串数组 strs，请把由相同字母以不同排列组成的字符串归为一组，
 * 并以任意顺序返回所有分组。字符串只包含小写英文字母；使用每个字母的出现
 * 次数作为唯一分组标识，无需对字符串排序。
 *
 * 算法实现说明：
 * 1. 对每个字符串统计 26 个小写字母各自的出现次数，counts 就是该字符串的字母组成特征。
 * 2. 按固定字母顺序把 26 个次数拼成带分隔符的 key；两个字符串异位当且仅当它们的 key 相同。
 * 3. 用哈希表把相同 key 的字符串加入同一列表，最后返回哈希表中的全部分组。
 *
 * 设所有字符串的字符总数为 C。时间复杂度：O(C)；除返回结果外，空间复杂度：O(n)。
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            for (int i = 0; i < str.length(); i++) {
                counts[str.charAt(i) - 'a']++;
            }

            StringBuilder key = new StringBuilder();
            for (int count : counts) {
                key.append('#').append(count);
            }
            groups.computeIfAbsent(key.toString(), ignored -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(groups.values());
    }
}
