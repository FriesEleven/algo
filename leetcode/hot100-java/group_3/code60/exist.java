/*
单词搜索

给定一个 m × n 的字符网格 board 和字符串 word，判断 word 是否存在于网格中。单词必须按
顺序由水平或竖直方向相邻单元格中的字母组成，同一个单元格在一条路径中不能重复使用。

算法实现说明：
1. 先统计 board 中每种字符的 frequency；若 word 对某字符的需求超过棋盘拥有量，或单词长度
   大于单元格总数，可立即返回 false。
2. 比较 word 首尾字符在棋盘中的频率，必要时反转待搜索字符数组，使深度优先搜索从更少见的
   一端开始，减少无效起点；路径可反向行走，所以不会改变答案。
3. 枚举每个与首字符相同的单元格作为起点。search 用 index 表示下一位要匹配的字符，并向
   上下左右四个方向递归。
4. 进入匹配单元格后暂时写入 '\0'，阻止当前路径再次使用它；无论分支成功或失败，返回前都
   恢复原字符，因此不同搜索路径互不影响。
5. 当 index 到达最后一个字符且当前格匹配时，完整相邻路径已经找到；若所有起点均失败，说明
   棋盘中不存在该单词。

时间复杂度：O(m * n * 3^(L - 1))，L 为 word 长度；首步之后每步最多继续三个方向。
空间复杂度：O(L)，用于递归调用栈；字符计数数组大小为常数。
*/
class Solution {
    private static final int[] ROW_CHANGE = {-1, 0, 1, 0};
    private static final int[] COL_CHANGE = {0, 1, 0, -1};

    public boolean exist(char[][] board, String word) {
        if (word.length() == 0) {
            return true;
        }
        if (board.length == 0 || board[0].length == 0
                || word.length() > board.length * board[0].length) {
            return false;
        }

        int[] frequency = new int[128];
        for (char[] row : board) {
            for (char character : row) {
                frequency[character]++;
            }
        }

        char[] letters = word.toCharArray();
        int[] needed = new int[128];
        for (char letter : letters) {
            if (++needed[letter] > frequency[letter]) {
                return false;
            }
        }
        if (frequency[letters[0]] > frequency[letters[letters.length - 1]]) {
            reverse(letters);
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == letters[0] && search(board, letters, row, col, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search(char[][] board, char[] word, int row, int col, int index) {
        if (board[row][col] != word[index]) {
            return false;
        }
        if (index == word.length - 1) {
            return true;
        }

        char original = board[row][col];
        board[row][col] = '\0';
        for (int direction = 0; direction < 4; direction++) {
            int nextRow = row + ROW_CHANGE[direction];
            int nextCol = col + COL_CHANGE[direction];
            if (nextRow >= 0 && nextRow < board.length
                    && nextCol >= 0 && nextCol < board[0].length
                    && search(board, word, nextRow, nextCol, index + 1)) {
                board[row][col] = original;
                return true;
            }
        }
        board[row][col] = original;
        return false;
    }

    private void reverse(char[] letters) {
        int left = 0;
        int right = letters.length - 1;
        while (left < right) {
            char temporary = letters[left];
            letters[left++] = letters[right];
            letters[right--] = temporary;
        }
    }
}
