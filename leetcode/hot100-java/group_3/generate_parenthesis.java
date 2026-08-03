import java.util.ArrayList;
import java.util.List;

/*
括号生成

给定整数 n，生成并返回所有由 n 对圆括号组成的有效括号字符串，答案顺序不限。有效字符串中
任意前缀的右括号数量都不能超过左括号数量，且最终两者数量均为 n。

算法实现说明：
1. open 和 close 分别记录 path 中已经使用的左、右括号数量，StringBuilder path 保存当前前缀。
2. 当 open 小于 n 时可以添加左括号；递归返回后删除它，继续尝试另一条分支。
3. 只有 close 小于 open 时才能添加右括号，这保证任意前缀都不会出现右括号多于左括号的无效
   情况；左右括号的使用总数也都不会超过 n。
4. path 长度达到 2n 时，左右括号必然各有 n 个且前缀始终合法，因此可直接加入结果；反过来，
   每个有效括号串的每一步都满足上述选择条件，所以不会遗漏。

时间复杂度：O(n * Cn)，Cn 为第 n 个卡特兰数，复制每个长度 2n 的答案需要 O(n) 时间。
空间复杂度：O(n) 辅助递归和路径空间；若计入输出，则为 O(n * Cn)。
*/
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(n, 0, 0, new StringBuilder(2 * n), result);
        return result;
    }

    private void generate(
            int n, int open, int close, StringBuilder path, List<String> result) {
        if (path.length() == 2 * n) {
            result.add(path.toString());
            return;
        }

        if (open < n) {
            path.append('(');
            generate(n, open + 1, close, path, result);
            path.deleteCharAt(path.length() - 1);
        }
        if (close < open) {
            path.append(')');
            generate(n, open, close + 1, path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
