/*
在排序数组中查找元素的第一个和最后一个位置

给定一个按非递减顺序排列的整数数组 nums 和目标值 target，返回 target 在数组中的起始下标
和结束下标；如果 target 不存在，返回 [-1, -1]。算法必须达到 O(log n) 时间复杂度。

算法实现说明：
1. lowerBound 在半开区间中寻找第一个大于或等于 target 的位置 first，它是 target 可能出现的
   最左下标。
2. 若 first 已到数组末尾，或 nums[first] 不等于 target，说明数组中没有目标值，直接返回
   [-1, -1]。
3. upperBound 用相同的二分框架寻找第一个严格大于 target 的位置：中间值小于或等于 target 时
   都向右移动 left。
4. 目标值连续出现，因此 upperBound 的前一位正是最后一个 target；返回 first 和
   upperBound - 1 即得到完整区间。

时间复杂度：O(log n)。
空间复杂度：O(1)。
*/
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = lowerBound(nums, target);
        if (first == nums.length || nums[first] != target) {
            return new int[] {-1, -1};
        }
        return new int[] {first, upperBound(nums, target) - 1};
    }

    private int lowerBound(int[] nums, int target) {
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

    private int upperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] <= target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }
}
