/*
 * 73. 矩阵置零
 *
 * 给定一个 m x n 整数矩阵 matrix，如果某个元素为 0，就把该元素所在的整行
 * 和整列全部设为 0。要求直接在原矩阵上修改；使用首行和首列充当其余行列的
 * 零标记，并单独记录首列是否需要置零。
 *
 * 算法实现说明：
 * 1. 用首行记录哪些列要置零、首列记录哪些行要置零；firstColumnHasZero 单独保存首列原本是否含零。
 * 2. 正向扫描矩阵，遇到零就在对应的 matrix[row][0] 和 matrix[0][column] 写入标记。
 * 3. 再从右下向左上根据行列标记置零，逆序处理可避免首行首列标记被过早破坏。
 * 4. 每行处理完后依据 firstColumnHasZero 决定首列元素，最终完成全部原地修改。
 *
 * 时间复杂度：O(mn)；空间复杂度：O(1)。
 */
class Solution {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        boolean firstColumnHasZero = false;

        for (int row = 0; row < rows; row++) {
            if (matrix[row][0] == 0) {
                firstColumnHasZero = true;
            }
            for (int column = 1; column < columns; column++) {
                if (matrix[row][column] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][column] = 0;
                }
            }
        }

        for (int row = rows - 1; row >= 0; row--) {
            for (int column = columns - 1; column >= 1; column--) {
                if (matrix[row][0] == 0 || matrix[0][column] == 0) {
                    matrix[row][column] = 0;
                }
            }
            if (firstColumnHasZero) {
                matrix[row][0] = 0;
            }
        }
    }
}
