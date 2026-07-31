/*
 * 54. 螺旋矩阵
 *
 * 给定一个 m 行 n 列的矩阵 matrix，按照从左上角开始的顺时针螺旋顺序，
 * 返回矩阵中的所有元素。每轮依次遍历上边、右边、下边和左边，并收缩边界。
 *
 * 算法实现说明：
 * 1. top、bottom、left、right 表示当前尚未访问矩形的四条边界。
 * 2. 每轮先从左到右读取上边，再从上到下读取右边，并收缩对应边界。
 * 3. 若收缩后仍有剩余行或列，再从右到左读取下边、从下到上读取左边，避免单行或单列被重复加入。
 * 4. 四条边界持续向内收缩，直到矩形为空，此时每个元素恰好访问一次。
 *
 * 时间复杂度：O(mn)；除返回结果外，空间复杂度：O(1)。
 */
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>(matrix.length * matrix[0].length);
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int column = left; column <= right; column++) {
                result.add(matrix[top][column]);
            }
            top++;

            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;

            if (top <= bottom) {
                for (int column = right; column >= left; column--) {
                    result.add(matrix[bottom][column]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }
        return result;
    }
}
