/*
 * 239. 滑动窗口最大值
 *
 * 给定整数数组 nums 和窗口大小 k，一个长度为 k 的窗口从数组最左端逐次向右
 * 移动一位。返回每个窗口中的最大值。单调递减队列中保存仍在窗口内的元素下标，
 * 队首始终对应当前窗口最大值。
 *
 * 算法实现说明：
 * 1. deque 用数组保存元素下标，head、tail 标出有效区间，并让对应数值从队首到队尾单调递减。
 * 2. 扫描新下标 i 时，先从队首移除已滑出窗口的下标，再从队尾移除所有不大于 nums[i] 的元素。
 * 3. 将 i 入队后，队首就是当前窗口最大值；窗口达到 k 个元素时把该值写入结果。
 * 4. 每个下标最多入队、出队一次，所以无需为每个窗口重新寻找最大值。
 *
 * 时间复杂度：O(n)，每个下标至多入队和出队一次；空间复杂度：O(k)。
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int[] deque = new int[nums.length];
        int head = 0;
        int tail = -1;

        for (int i = 0; i < nums.length; i++) {
            while (head <= tail && deque[head] <= i - k) {
                head++;
            }
            while (head <= tail && nums[deque[tail]] <= nums[i]) {
                tail--;
            }
            deque[++tail] = i;
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque[head]];
            }
        }
        return result;
    }
}
