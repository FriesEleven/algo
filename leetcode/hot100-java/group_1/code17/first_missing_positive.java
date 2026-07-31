/*
 * 41. 缺失的第一个正数
 *
 * 给定一个未排序的整数数组 nums，返回其中没有出现的最小正整数。算法必须在
 * O(n) 时间内完成，并且只能使用常数级额外空间。将范围 [1, n] 内的值交换到
 * 下标 value - 1 处后，第一个不匹配的位置就是答案。
 *
 * 算法实现说明：
 * 1. 长度为 n 的数组若包含最小缺失正数，它只可能落在 [1, n + 1]；值 x 的目标下标是 x - 1。
 * 2. 对每个位置反复交换，把范围 [1, n] 内且尚未就位的值放到目标下标；越界值和重复值无需处理。
 * 3. 交换结束后从左扫描，第一个不满足 nums[i] == i + 1 的位置就缺少 i + 1。
 * 4. 若所有位置都匹配，说明 1 到 n 全部存在，答案为 n + 1。
 *
 * 时间复杂度：O(n)；空间复杂度：O(1)。
 */
class Solution {
    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            while (nums[i] >= 1
                    && nums[i] <= length
                    && nums[nums[i] - 1] != nums[i]) {
                int targetIndex = nums[i] - 1;
                int temporary = nums[i];
                nums[i] = nums[targetIndex];
                nums[targetIndex] = temporary;
            }
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return length + 1;
    }
}
