/*
编辑距离

给定字符串 word1 和 word2，允许对 word1 执行插入一个字符、删除一个字符或替换一个
字符，返回将 word1 转换成 word2 所需的最少操作数。

算法实现说明：
1. 让较短字符串作为列来节省空间。`dp[j]` 表示把当前行对应的前缀转换成列字符串前 `j`
   个字符所需的最少操作数；初始行表示把空串变成目标前缀，只能连续插入。
2. 开始新的一行时，`dp[0] = i`，表示把长度为 `i` 的前缀变为空串需要删除 `i` 次。
3. 当前两个字符相同时不需要新操作，直接使用左上角 `diagonal`；不同时，在左边（插入）、
   上边（删除）、左上角（替换）三个旧状态中取最小值，再加 1。
4. `oldAbove` 在覆盖 `dp[j]` 前保存上方状态，并在下一列成为新的 `diagonal`，因此一行数组
   就能完成整张二维表的计算。

时间复杂度 O(mn)，空间复杂度 O(min(m, n))。
*/
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.length() < word2.length()) {
            String temporary = word1;
            word1 = word2;
            word2 = temporary;
        }

        int[] dp = new int[word2.length() + 1];
        for (int j = 0; j <= word2.length(); j++) {
            dp[j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            int diagonal = dp[0];
            dp[0] = i;
            for (int j = 1; j <= word2.length(); j++) {
                int oldAbove = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = diagonal;
                } else {
                    dp[j] = 1 + Math.min(diagonal, Math.min(dp[j], dp[j - 1]));
                }
                diagonal = oldAbove;
            }
        }
        return dp[word2.length()];
    }
}
