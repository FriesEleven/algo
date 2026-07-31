/*
颜色分类

给定只包含 0、1、2 的数组 nums，原地将相同数字相邻排列，并按 0、1、2 的顺序排序。
不能调用库排序函数。

算法实现说明：
1. 三个指针把数组分成四段：`left` 左边全是 0，`right` 右边全是 2，`current` 之前且
   不属于 0 区的元素全是 1，中间剩余部分还未检查。
2. 当前元素是 0 时，把它与 `left` 位置交换，然后两个指针一起右移，扩大 0 区。
3. 当前元素是 2 时，把它与 `right` 位置交换并让 `right` 左移；换回来的元素还未检查，
   所以此时不能移动 `current`。
4. 当前元素是 1 时位置已经正确，只需移动 `current`。当 `current > right`，未处理区为空，
   三种颜色便已经按顺序排好。

时间复杂度 O(n)，空间复杂度 O(1)。
*/
class Solution {
    public void sortColors(int[] nums) {
        int left = 0;
        int current = 0;
        int right = nums.length - 1;
        while (current <= right) {
            if (nums[current] == 0) {
                swap(nums, left++, current++);
            } else if (nums[current] == 2) {
                swap(nums, current, right--);
            } else {
                current++;
            }
        }
    }

    private void swap(int[] nums, int first, int second) {
        int temporary = nums[first];
        nums[first] = nums[second];
        nums[second] = temporary;
    }
}
