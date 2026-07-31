/*
最长递增子序列

给定整数数组 nums，返回其中最长严格递增子序列的长度。子序列通过删除若干元素（也可以
不删除）得到，剩余元素的相对顺序不能改变，但不要求在原数组中连续。

算法实现说明：
1. `tails[i]` 保存“长度为 `i + 1` 的递增子序列中，能够得到的最小末尾值”。末尾越小，
   后面越容易接上更大的数字。
2. 对每个 `number`，在已经有效的 `tails[0..size)` 中二分寻找第一个大于或等于它的位置。
3. 用 `number` 替换这个位置，相当于在不缩短序列长度的情况下换成更有潜力的末尾；如果
   `number` 比所有末尾都大，它会放在最右侧，并让 `size` 增加 1。
4. `tails` 中保存的不一定是一条真实的最终子序列，但 `size` 始终等于目前能构造出的最长
   递增子序列长度，因此扫描结束后直接返回 `size`。

时间复杂度 O(n log n)，空间复杂度 O(n)。
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int number : nums) {
            int left = 0;
            int right = size;
            while (left < right) {
                int middle = left + (right - left) / 2;
                if (tails[middle] < number) {
                    left = middle + 1;
                } else {
                    right = middle;
                }
            }
            tails[left] = number;
            if (left == size) {
                size++;
            }
        }
        return size;
    }
}
