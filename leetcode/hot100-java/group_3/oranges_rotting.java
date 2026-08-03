import java.util.ArrayDeque;

/*
腐烂的橘子

在 m × n 网格中，0 表示空单元格，1 表示新鲜橘子，2 表示腐烂橘子。每过一分钟，
腐烂橘子会使其上下左右相邻的新鲜橘子腐烂。返回使所有新鲜橘子腐烂所需的最少分钟数；
如果无法使所有新鲜橘子腐烂，则返回 -1。

算法实现说明：
1. 首次扫描网格，把所有初始腐烂橘子的位置加入 queue，并用 fresh 统计新鲜橘子数量；多个
   腐烂源必须同时开始扩散，所以它们共享同一个队列。
2. 当 fresh 大于零且队列非空时，只处理本轮开始时的 levelSize 个位置，这一整层代表同一分钟
   内已经腐烂的橘子。
3. 对每个位置检查四个方向，遇到新鲜橘子就立刻改为 2、将 fresh 减一并入队；本层结束后
   minutes 加一，下一层正好表示下一分钟的扩散。
4. 搜索结束时 fresh 为零，说明最后一次分层扩散所用的 minutes 就是最短时间；否则仍有新鲜
   橘子无法被任何腐烂源到达，应返回 -1。

时间复杂度：O(m * n)，每个单元格至多入队一次。
空间复杂度：O(m * n)，用于多源广度优先搜索队列。
*/
class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int fresh = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    fresh++;
                } else if (grid[row][col] == 2) {
                    queue.offer(row * cols + col);
                }
            }
        }

        int minutes = 0;
        int[] directions = {-1, 0, 1, 0, -1};
        while (fresh > 0 && !queue.isEmpty()) {
            int levelSize = queue.size();
            minutes++;
            for (int i = 0; i < levelSize; i++) {
                int position = queue.poll();
                int row = position / cols;
                int col = position % cols;
                for (int direction = 0; direction < 4; direction++) {
                    int nextRow = row + directions[direction];
                    int nextCol = col + directions[direction + 1];
                    if (nextRow >= 0 && nextRow < rows
                            && nextCol >= 0 && nextCol < cols
                            && grid[nextRow][nextCol] == 1) {
                        grid[nextRow][nextCol] = 2;
                        fresh--;
                        queue.offer(nextRow * cols + nextCol);
                    }
                }
            }
        }
        return fresh == 0 ? minutes : -1;
    }
}
