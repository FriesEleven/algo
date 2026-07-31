/*
最长回文子串

给定字符串 s，返回其中最长的回文子串。回文串从左向右与从右向左读取完全相同；当存在
多个最长答案时，返回任意一个即可。

算法实现说明：
1. 先在原字符之间和两端插入 `#`，这样奇数长度与偶数长度的回文都能统一表示成“有一个
   中心”的形式。
2. `radius[i]` 表示以位置 `i` 为中心能够向两边扩展多少格；`center` 和 `rightBoundary`
   记录目前向右延伸最远的回文区间。
3. 当 `i` 位于这个区间内时，它关于 `center` 的镜像位置已经计算过，可以先复用镜像半径，
   跳过确定相同的部分，再从边界外继续比较。
4. 每次扩展后更新最右边界，并记录半径最大的中心。最右边界只会不断向右移动，所以全部
   扩展加起来仍是线性工作量。
5. 最后把变换后字符串的中心和半径换算回原字符串的起点、长度，再截取答案。

时间复杂度 O(n)，空间复杂度 O(n)。
*/
class Solution {
    public String longestPalindrome(String s) {
        char[] transformed = new char[s.length() * 2 + 1];
        for (int i = 0; i < transformed.length; i++) {
            transformed[i] = (i & 1) == 0 ? '#' : s.charAt(i / 2);
        }

        int[] radius = new int[transformed.length];
        int center = 0;
        int rightBoundary = 0;
        int bestCenter = 0;
        int bestRadius = 0;
        for (int i = 0; i < transformed.length; i++) {
            if (i < rightBoundary) {
                int mirror = 2 * center - i;
                radius[i] = Math.min(rightBoundary - i, radius[mirror]);
            }
            while (i - radius[i] - 1 >= 0
                    && i + radius[i] + 1 < transformed.length
                    && transformed[i - radius[i] - 1]
                    == transformed[i + radius[i] + 1]) {
                radius[i]++;
            }
            if (i + radius[i] > rightBoundary) {
                center = i;
                rightBoundary = i + radius[i];
            }
            if (radius[i] > bestRadius) {
                bestCenter = i;
                bestRadius = radius[i];
            }
        }

        int start = (bestCenter - bestRadius) / 2;
        return s.substring(start, start + bestRadius);
    }
}
