/*
 * 11. 盛最多水的容器
 *
 * 给定长度为 n 的整数数组 height，第 i 条竖线的端点为 (i, 0) 和
 * (i, height[i])。选择两条竖线与 x 轴组成容器，返回容器能够盛放的最大水量；
 * 容器不能倾斜。
 *
 * 算法实现说明：
 * 1. left 和 right 从数组两端开始，当前容量由两端较矮高度乘以两端距离得到。
 * 2. 每轮先用当前容量更新 best，再向内移动较矮一侧的指针。
 * 3. 保留较矮边并缩短宽度不可能得到更大容量，只有更换较矮边才可能提高有效高度，因此不会漏掉最优解。
 *
 * 时间复杂度：O(n)；空间复杂度：O(1)。
 */
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int best = 0;
        while (left < right) {
            int width = right - left;
            best = Math.max(best, Math.min(height[left], height[right]) * width);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return best;
    }
}
