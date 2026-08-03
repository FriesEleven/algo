import java.util.ArrayList;
import java.util.List;

/*
杨辉三角

给定正整数 numRows，生成杨辉三角的前 numRows 行。每行的首尾元素为 1，其余元素等于
上一行中左上方与右上方两个元素之和。

算法实现说明：
1. 外层循环从第 0 行开始逐行创建列表，每一行先放入左边界的 1。
2. 当前行的内部位置 `column`，等于上一行的 `column - 1` 与 `column` 两个位置之和。
3. 除第一行外，每行最后再补上右边界的 1，然后把完整的一行加入 `triangle`。
4. 因为计算某一行时上一行已经完成，所以可以直接读取，不需要额外的二维辅助数组。

必须生成约 numRows²/2 个结果，时间复杂度 O(numRows²)，额外空间复杂度 O(1)（不计输出）。
*/
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>(numRows);
        for (int row = 0; row < numRows; row++) {
            List<Integer> current = new ArrayList<>(row + 1);
            current.add(1);
            for (int column = 1; column < row; column++) {
                current.add(triangle.get(row - 1).get(column - 1)
                        + triangle.get(row - 1).get(column));
            }
            if (row > 0) {
                current.add(1);
            }
            triangle.add(current);
        }
        return triangle;
    }
}
