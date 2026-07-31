/*
不同路径

一个机器人位于 m × n 网格的左上角，每次只能向右或向下移动一格，求到达右下角共有
多少条不同路径。题目保证答案不超过 2 × 10^9。

算法实现说明：
1. 从左上角到右下角，一定要走 `m - 1` 次向下和 `n - 1` 次向右，总步数固定为
   `m + n - 2`。
2. 每条路径只由这些步骤的排列顺序决定，因此问题变成：从总步数中选出哪些位置用来向下
   （或向右），答案就是组合数。
3. 选择次数较少的那个方向可以减少循环次数。代码按组合数公式逐项“先乘、再除”，不用计算
   很大的完整阶乘。
4. 中间结果使用 `long` 保存，最后再转回题目要求的 `int`，避免计算过程中发生整数溢出。

时间复杂度 O(min(m, n))，空间复杂度 O(1)。
*/
class Solution {
    public int uniquePaths(int m, int n) {
        int totalMoves = m + n - 2;
        int chosenMoves = Math.min(m - 1, n - 1);
        long combinations = 1;
        for (int i = 1; i <= chosenMoves; i++) {
            combinations = combinations * (totalMoves - chosenMoves + i) / i;
        }
        return (int) combinations;
    }
}
