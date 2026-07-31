/*
寻找旋转排序数组中的最小值

一个元素互不相同、原本严格升序的数组经过若干次旋转后得到 nums。返回 nums 中的最小元素，
并要求算法达到 O(log n) 时间复杂度。

算法实现说明：
1. 用闭区间 [left, right] 保存最小值所在范围，并把 nums[middle] 与当前右端值比较。
2. 若 nums[middle] 大于 nums[right]，middle 位于旋转前较大的那一段，断点和最小值必在其
   右侧，因此令 left = middle + 1。
3. 否则 middle 位于包含最小值的较小段，或者本身就是最小值，令 right = middle 而不能跳过它。
4. 当 left 等于 right 时只剩唯一候选；由于每次收缩都保留了旋转断点，该元素就是全局最小值。

时间复杂度：O(log n)。
空间复杂度：O(1)。
*/
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] > nums[right]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return nums[left];
    }
}
