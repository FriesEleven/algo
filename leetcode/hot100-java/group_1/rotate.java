/*
 * 189. 轮转数组
 *
 * 给定整数数组 nums 和非负整数 k，将数组中的元素整体向右轮转 k 个位置。
 * 必须原地完成；先反转整个数组，再分别反转轮转后的前后两段即可得到目标顺序。
 *
 * 算法实现说明：
 * 1. 先用 k % nums.length 去掉完整轮转周期，只保留真正需要右移的位置数。
 * 2. 反转整个数组，使原数组末尾的 k 个元素来到最前面，但前后两段内部次序都被颠倒。
 * 3. 分别反转前 k 个元素和剩余元素，恢复两段各自的相对顺序，最终得到右轮转结果。
 *
 * 时间复杂度：O(n)；空间复杂度：O(1)。
 */
class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temporary = nums[left];
            nums[left++] = nums[right];
            nums[right--] = temporary;
        }
    }
}
