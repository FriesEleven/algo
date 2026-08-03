/*
 * 42. 接雨水
 *
 * 给定 n 个非负整数表示宽度均为 1 的柱子高度图，计算这些柱子在下雨后总共
 * 能够接住多少单位的雨水。
 *
 * 算法实现说明：
 * 1. left、right 从两端向内移动，leftMax、rightMax 分别记录两侧已经见过的最高柱子。
 * 2. 高度较低的一侧，其可接水上界已由该侧最大值确定；用“侧边最大值 - 当前高度”累加当前位置雨水。
 * 3. 处理完较低侧后移动对应指针，直到两指针相遇，所有位置的雨水量便都已计算。
 *
 * 时间复杂度：O(n)；空间复杂度：O(1)。
 */
class Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                water += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }
}
