import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
N 皇后

在 n × n 棋盘上放置 n 个皇后，使任意两个皇后都不处于同一行、同一列或同一条对角线上。
返回所有不同的合法棋盘，其中 'Q' 表示皇后，'.' 表示空位。

算法实现说明：
1. fullMask 的低 n 位代表棋盘的 n 列；columns、leftDiagonals 和 rightDiagonals 分别用位 1
   标记下一行不能放皇后的列，queenColumn 记录每一行最终选择的列号。
2. 用 fullMask & ~(columns | leftDiagonals | rightDiagonals) 得到当前行全部可用位置，再用
   available & -available 每次取出最低的一位 1，直接枚举合法列而不扫描冲突位置。
3. 选定 position 后更新列掩码；左右对角线掩码分别左移、右移一位，恰好对应它们在下一行会
   攻击的位置，然后递归放置下一行皇后。
4. row 等于 n 时，说明每行都有一个皇后且三类冲突均已排除，根据 queenColumn 构造棋盘；
   回溯会尝试每行的所有可用位，所以所有且仅有合法方案会进入结果。

时间复杂度：搜索最坏上界为 O(n!)，另需 O(S * n^2) 构造 S 个合法棋盘。
空间复杂度：O(n) 辅助递归与位置数组空间；返回的 S 个棋盘占 O(S * n^2) 空间。
*/
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queenColumn = new int[n];
        place(0, n, (1 << n) - 1, 0, 0, 0, queenColumn, result);
        return result;
    }

    private void place(
            int row,
            int n,
            int fullMask,
            int columns,
            int leftDiagonals,
            int rightDiagonals,
            int[] queenColumn,
            List<List<String>> result) {
        if (row == n) {
            result.add(buildBoard(queenColumn, n));
            return;
        }

        int available = fullMask & ~(columns | leftDiagonals | rightDiagonals);
        while (available != 0) {
            int position = available & -available;
            available -= position;
            queenColumn[row] = Integer.numberOfTrailingZeros(position);
            place(
                    row + 1,
                    n,
                    fullMask,
                    columns | position,
                    ((leftDiagonals | position) << 1) & fullMask,
                    (rightDiagonals | position) >>> 1,
                    queenColumn,
                    result);
        }
    }

    private List<String> buildBoard(int[] queenColumn, int n) {
        List<String> board = new ArrayList<>(n);
        for (int row = 0; row < n; row++) {
            char[] cells = new char[n];
            Arrays.fill(cells, '.');
            cells[queenColumn[row]] = 'Q';
            board.add(new String(cells));
        }
        return board;
    }
}
