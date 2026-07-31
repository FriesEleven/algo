/*
最长有效括号

给定只包含 '(' 和 ')' 的字符串 s，返回其中格式正确且连续的最长括号子串长度。有效括号
要求每个左括号都由之后的右括号配对，且任意前缀中右括号不能多于左括号。

算法实现说明：
1. 先从左向右扫描，用 `left`、`right` 统计当前这一段中左右括号的数量。
2. 两者相等时，当前这一段恰好配对，长度是 `2 * right`；若右括号更多，当前段不可能再由
   后面的字符修复，因此把两个计数清零，从下一位置重新开始。
3. 只做正向扫描会漏掉左括号过多的情况，例如 `(()`。所以再从右向左扫描一次，规则对称：
   左括号过多时重置，数量相等时更新答案。
4. 两个方向合起来覆盖了有效子串两侧所有失衡情况，且只保存计数器，不需要额外栈空间。

时间复杂度 O(n)，空间复杂度 O(1)。
*/
class Solution {
    public int longestValidParentheses(String s) {
        int best = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                best = Math.max(best, 2 * right);
            } else if (right > left) {
                left = 0;
                right = 0;
            }
        }

        left = 0;
        right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                best = Math.max(best, 2 * left);
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }
        return best;
    }
}
