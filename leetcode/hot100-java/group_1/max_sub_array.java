/*
 * 53. 最大子数组和
 *
 * 给定一个整数数组 nums，找出其中元素和最大的连续非空子数组，并返回该子数组
 * 的元素和。当前位置的最优子数组要么从当前位置开始，要么接在前一位置的最优
 * 子数组之后。
 *
 * 算法实现说明：
 * 1. current 表示必须以当前位置结尾的最大子数组和，best 表示到目前为止的全局最大和。
 * 2. 处理 nums[i] 时，比较“只取 nums[i] 重新开始”和“接在上一段 current 后面”，较大者成为新的 current。
 * 3. 每轮用 current 更新 best；所有可能的右端点都被考察后，best 就是最大连续子数组和。
 *
 * 时间复杂度：O(n)；空间复杂度：O(1)。
 */
class Solution {
    public int maxSubArray(int[] nums) {
        int current = nums[0];
        int best = nums[0];
        for (int i = 1; i < nums.length; i++) {
            current = Math.max(nums[i], current + nums[i]);
            best = Math.max(best, current);
        }
        return best;
    }
}
