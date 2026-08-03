/*
 * 48. 旋转图像
 *
 * 给定一个 n x n 的二维矩阵 matrix 表示图像，将图像顺时针旋转 90 度。
 * 必须直接修改输入矩阵，不能使用另一个矩阵。先沿主对角线转置，再水平反转
 * 每一行，即可原地完成旋转。
 *
 * 算法实现说明：
 * 1. 先遍历主对角线上方的元素，与其对称位置交换，完成矩阵转置。
 * 2. 再对每一行使用左右指针原地反转，使转置后的列顺序水平翻转。
 * 3. 两步合起来把原位置 (row, column) 映射到 (column, n - 1 - row)，正是顺时针旋转 90 度。
 *
 * 时间复杂度：O(n^2)；空间复杂度：O(1)。
 */
class Solution {
    public void rotate(int[][] matrix) {
        int size = matrix.length;
        for (int row = 0; row < size; row++) {
            for (int column = row + 1; column < size; column++) {
                int temporary = matrix[row][column];
                matrix[row][column] = matrix[column][row];
                matrix[column][row] = temporary;
            }
        }

        for (int[] row : matrix) {
            for (int left = 0, right = size - 1; left < right; left++, right--) {
                int temporary = row[left];
                row[left] = row[right];
                row[right] = temporary;
            }
        }
    }
}
