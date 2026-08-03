/*
柱状图中最大的矩形

给定整数数组 heights，heights[i] 是柱状图第 i 根柱子的高度，每根柱子的宽度均为 1。返回能
在该柱状图中勾勒出的轴对齐矩形的最大面积。

算法实现说明：
1. stack 保存柱子下标，并使对应高度从栈底到栈顶单调不减；maximum 记录目前找到的最大面积。
2. 扫描到更矮的 currentHeight 时，持续弹出更高的柱子。对被弹出的 height，当前 index 是它
   右侧第一个更矮位置，弹栈后的新栈顶是它左侧第一个更矮位置。
3. 因此该高度能向左右延伸的最大宽度为 index - leftBoundary - 1，用 height * width 更新答案；
   这个矩形是所有以该柱为最低柱的矩形中最大的。
4. 正常下标处理完后再读取一个虚拟高度 0，迫使栈中所有正高度柱子出栈并计算。每根柱子都在
   自己的最大延伸范围确定时结算一次，所以全局最优矩形不会遗漏。

时间复杂度：O(n)，每个柱子至多入栈、出栈各一次。
空间复杂度：O(n)。
*/
class Solution {
    public int largestRectangleArea(int[] heights) {
        int[] stack = new int[heights.length];
        int top = -1;
        int maximum = 0;

        for (int index = 0; index <= heights.length; index++) {
            int currentHeight = index == heights.length ? 0 : heights[index];
            while (top >= 0 && heights[stack[top]] > currentHeight) {
                int height = heights[stack[top--]];
                int leftBoundary = top < 0 ? -1 : stack[top];
                int width = index - leftBoundary - 1;
                maximum = Math.max(maximum, height * width);
            }
            if (index < heights.length) {
                stack[++top] = index;
            }
        }
        return maximum;
    }
}
