/*
搜索插入位置

给定一个无重复元素、按升序排列的整数数组 nums 和目标值 target。如果 target 已存在，返回其
下标；否则返回将 target 按升序插入数组后所在的位置。算法必须达到 O(log n) 时间复杂度。

算法实现说明：
1. 用半开区间 [left, right) 保存答案可能出现的位置，初始覆盖 0 到 nums.length；要找的是
   第一个满足 nums[index] >= target 的下标。
2. 若 nums[middle] 小于 target，则 middle 及其左侧都不可能是答案，把 left 移到 middle + 1；
   否则 middle 仍可能是第一个合格位置，把 right 收缩到 middle。
3. 当 left 与 right 相等时，比它更靠左的位置都小于 target，而该位置是数组末尾或值不小于
   target，因此它既是目标存在时的下标，也是目标不存在时正确的插入位置。

时间复杂度：O(log n)。
空间复杂度：O(1)。
*/
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }
}
