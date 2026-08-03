/*
下一个排列

整数数组表示一个排列。将其原地变为字典序中紧邻的下一个更大排列；若当前已经是最大
排列，则变为最小排列（升序）。只能使用常数额外空间。

算法实现说明：
1. 从右向左寻找第一个满足 `nums[pivot] < nums[pivot + 1]` 的位置。它右边的后缀已经是
   降序，说明仅调整后缀无法得到更大的排列。
2. 若找到 `pivot`，再从最右边寻找第一个比它大的数字。由于后缀降序，这就是能让排列变大
   的最小替换值；交换后得到的增幅尽可能小。
3. 交换后的后缀仍是降序，把它整体反转为升序，就得到固定前缀下最小的后缀，因此整个排列
   正好是紧邻的下一个排列。
4. 如果找不到转折点，原数组从大到小，已经是最大排列；反转整个数组即可回到最小排列。

时间复杂度 O(n)，空间复杂度 O(1)。
*/
class Solution {
    public void nextPermutation(int[] nums) {
        int pivot = nums.length - 2;
        while (pivot >= 0 && nums[pivot] >= nums[pivot + 1]) {
            pivot--;
        }
        if (pivot >= 0) {
            int successor = nums.length - 1;
            while (nums[successor] <= nums[pivot]) {
                successor--;
            }
            swap(nums, pivot, successor);
        }
        reverse(nums, pivot + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left++, right--);
        }
    }

    private void swap(int[] nums, int first, int second) {
        int temporary = nums[first];
        nums[first] = nums[second];
        nums[second] = temporary;
    }
}
