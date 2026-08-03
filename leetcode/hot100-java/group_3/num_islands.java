import java.util.ArrayDeque;

/*
岛屿数量

给定一个由字符 '1'（陆地）和 '0'（水）组成的二维网格 grid。水平或竖直方向相邻的陆地
属于同一座岛屿，网格边界外均视为水。返回网格中互不相连的岛屿数量。

算法实现说明：
1. 用 islands 记录岛屿数量，用队列保存当前岛屿中等待扩展的单元格；二维坐标编码为
   row * cols + col，取出时再还原行列，避免额外创建坐标对象。
2. 逐格扫描 grid。遇到字符 '1'，说明发现了一座尚未统计的新岛屿，将 islands 加一，立即把
   该格改成 '0' 并加入队列。
3. 从队列取出陆地，检查上下左右四个相邻位置；每发现一块陆地，就在入队前把它标记为 '0'，
   防止同一格被不同邻居重复加入队列。
4. 一次广度优先搜索会恰好清除与起点连通的整座岛屿，因此后续扫描不会重复计数，最终
   islands 就是互不连通的岛屿总数。

时间复杂度：O(m * n)，m、n 分别为网格的行数和列数。
空间复杂度：O(m * n)，最坏情况下队列会容纳同一座岛屿中的大量单元格。
*/
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int islands = 0;
        int[] directions = {-1, 0, 1, 0, -1};
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] != '1') {
                    continue;
                }

                islands++;
                grid[row][col] = '0';
                queue.offer(row * cols + col);
                while (!queue.isEmpty()) {
                    int position = queue.poll();
                    int currentRow = position / cols;
                    int currentCol = position % cols;
                    for (int direction = 0; direction < 4; direction++) {
                        int nextRow = currentRow + directions[direction];
                        int nextCol = currentCol + directions[direction + 1];
                        if (nextRow >= 0 && nextRow < rows
                                && nextCol >= 0 && nextCol < cols
                                && grid[nextRow][nextCol] == '1') {
                            grid[nextRow][nextCol] = '0';
                            queue.offer(nextRow * cols + nextCol);
                        }
                    }
                }
            }
        }
        return islands;
    }
}
