/*
最小路径和

给定一个元素非负的 m × n 网格 grid，从左上角出发，每次只能向右或向下移动一格，
返回到达右下角的路径中数字总和最小的值。

算法实现说明：
1. 到达一个格子只能从上方或左方进入，所以它的最小路径和等于自身数字加上这两个来源中
   较小的路径和。
2. 第一列只能从上往下走，先逐格累加；第一行只能从左往右走，也先逐格累加。
3. 对其余格子按从上到下、从左到右的顺序计算。此时上方和左方已经保存了各自的最优结果，
   可以直接用来更新当前 `grid[row][column]`。
4. 原网格被当作动态规划表使用，因此不需要额外数组；右下角最后保存的就是完整答案。

时间复杂度 O(mn)，额外空间复杂度 O(1)。
*/
class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        for (int row = 1; row < rows; row++) {
            grid[row][0] += grid[row - 1][0];
        }
        for (int column = 1; column < columns; column++) {
            grid[0][column] += grid[0][column - 1];
        }
        for (int row = 1; row < rows; row++) {
            for (int column = 1; column < columns; column++) {
                grid[row][column] += Math.min(
                        grid[row - 1][column], grid[row][column - 1]);
            }
        }
        return grid[rows - 1][columns - 1];
    }
}
