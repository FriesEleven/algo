/*
搜索二维矩阵

给定 m × n 整数矩阵 matrix，每行从左到右按非递减顺序排列，并且每行第一个整数大于上一行
最后一个整数。判断目标值 target 是否存在于矩阵中，要求 O(log(m * n)) 时间复杂度。

算法实现说明：
1. 由于每行有序且下一行首元素大于上一行尾元素，把矩阵按行连接后就是一个整体有序数组，
   其虚拟下标范围为 0 到 rows * cols - 1。
2. 对虚拟下标做普通二分；middle 对应的真实元素是 matrix[middle / cols][middle % cols]，
   因而不需要复制或展开矩阵。
3. 中间值小于 target 时舍弃左半区间，大于 target 时舍弃右半区间，相等则立即返回 true。
4. left 越过 right 说明所有可能位置都已排除，矩阵中不存在 target，返回 false。

时间复杂度：O(log(m * n))。
空间复杂度：O(1)。
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            int value = matrix[middle / cols][middle % cols];
            if (value == target) {
                return true;
            }
            if (value < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return false;
    }
}
