/*
 * 240. 搜索二维矩阵 II
 *
 * 给定一个 m x n 整数矩阵 matrix，其中每行从左到右按非递减顺序排列，
 * 每列从上到下也按非递减顺序排列。判断整数 target 是否存在于矩阵中。
 * 从右上角出发，每次比较都能排除一整行或一整列。
 *
 * 算法实现说明：
 * 1. 从右上角开始，该位置左侧元素不大于它、下方元素不小于它，便于单向排除范围。
 * 2. 当前值大于 target 时左移一列，因为当前值下方只会更大；当前值较小时下移一行，因为左侧只会更小。
 * 3. 遇到 target 立即返回 true；越过左边界或下边界仍未找到，说明所有可能位置均已排除。
 *
 * 时间复杂度：O(m + n)；空间复杂度：O(1)。
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int column = matrix[0].length - 1;
        while (row < matrix.length && column >= 0) {
            if (matrix[row][column] == target) {
                return true;
            }
            if (matrix[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }
}
