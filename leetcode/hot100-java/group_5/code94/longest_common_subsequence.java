/*
最长公共子序列

给定两个字符串 text1 和 text2，返回二者最长公共子序列的长度。子序列可以通过删除部分
字符得到但不能改变其余字符的相对顺序；如果不存在公共子序列，返回 0。

算法实现说明：
1. 为节省内存，先让较短字符串作为表格的列，`dp[j]` 表示当前处理范围内与它前 `j` 个
   字符的最长公共子序列长度。
2. 若当前两个字符相同，就能在“都不包含这两个字符”的左上角答案后接上该字符，因此使用
   `diagonal + 1`。
3. 若字符不同，至少要舍弃其中一个字符，取“舍弃当前行字符”的上方状态与“舍弃当前列字符”
   的左方状态中的较大值。
4. 一维数组会覆盖上一行数据，所以用 `diagonal` 暂存左上角、用 `oldAbove` 暂存覆盖前的
   上方值。处理完所有字符后，数组末尾就是答案。

时间复杂度 O(mn)，空间复杂度 O(min(m, n))。
*/
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.length() < text2.length()) {
            String temporary = text1;
            text1 = text2;
            text2 = temporary;
        }

        int[] dp = new int[text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            int diagonal = 0;
            for (int j = 1; j <= text2.length(); j++) {
                int oldAbove = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = diagonal + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                diagonal = oldAbove;
            }
        }
        return dp[text2.length()];
    }
}
