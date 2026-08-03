import java.util.ArrayList;
import java.util.List;

/*
分割回文串

给定一个字符串 s，将它分割成若干非空子串，使分割后的每个子串都是回文串。返回所有可能的
分割方案，方案顺序不作要求。

算法实现说明：
1. palindrome[left][right] 表示 s[left..right] 是否回文。从较大的 left 向前填写：两端字符相同，
   且中间长度不足 2 或中间部分已是回文时，当前子串就是回文。
2. 回溯参数 start 表示下一段从哪里开始，path 保存当前已经选定的回文片段；预处理表让每次
   判断候选片段只需 O(1)。
3. 枚举 end 从 start 到字符串末尾，仅当 palindrome[start][end] 为 true 时，把该子串加入
   path，并从 end + 1 继续分割；返回后删除末尾片段。
4. start 到达字符串末尾时，path 已连续覆盖整个 s 且每段都经过回文表验证，将其复制为答案；
   枚举每段的所有可能终点保证不会漏掉任何合法分割。

时间复杂度：O(n^2 + n * 2^n)，前一项用于预处理，后一项是最坏情况下生成并复制所有方案。
空间复杂度：O(n^2 + n) 辅助空间，分别用于回文表和递归路径；返回结果空间另计。
*/
class Solution {
    public List<List<String>> partition(String s) {
        int length = s.length();
        boolean[][] palindrome = new boolean[length][length];
        for (int left = length - 1; left >= 0; left--) {
            for (int right = left; right < length; right++) {
                palindrome[left][right] = s.charAt(left) == s.charAt(right)
                        && (right - left < 2 || palindrome[left + 1][right - 1]);
            }
        }

        List<List<String>> result = new ArrayList<>();
        split(s, 0, palindrome, new ArrayList<>(), result);
        return result;
    }

    private void split(
            String s,
            int start,
            boolean[][] palindrome,
            List<String> path,
            List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (!palindrome[start][end]) {
                continue;
            }
            path.add(s.substring(start, end + 1));
            split(s, end + 1, palindrome, path, result);
            path.remove(path.size() - 1);
        }
    }
}
