/*
搜索旋转排序数组

一个元素互不相同的严格升序数组可能在未知下标处旋转。给定旋转后的数组 nums 和目标值
target，若 target 存在则返回其下标，否则返回 -1；算法必须达到 O(log n) 时间复杂度。

算法实现说明：
1. 用 left、right 维护仍可能包含 target 的闭区间，每轮先检查 nums[middle] 是否就是目标。
2. 数组元素互不相同，所以 nums[left] <= nums[middle] 时左半段一定有序，否则右半段一定有序。
3. 对已经确定有序的半段，用端点比较判断 target 是否落在其值域内；若在就保留该半段，否则
   转去另一半搜索。
4. 每轮都能安全排除至少一半元素，因为目标若不在有序半段的值域中，只可能位于另一半；区间
   为空仍未命中时返回 -1。

时间复杂度：O(log n)。
空间复杂度：O(1)。
*/
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                return middle;
            }

            if (nums[left] <= nums[middle]) {
                if (nums[left] <= target && target < nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                if (nums[middle] < target && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }
}
