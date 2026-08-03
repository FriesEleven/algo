/*
数组中的第 K 个最大元素

给定整数数组 nums 和整数 k，返回数组按降序排列后的第 k 个元素。需要的是排序意义上的第 k
大元素，而不是第 k 个不同元素。允许原地调整 nums。

算法实现说明：
1. 升序排列后的第 k 大位于下标 nums.length - k，select 只需让这个 target 下标落到正确元素，
   不必把整个数组排序。
2. medianOfMedians 每五个元素做一次小规模插入排序，把各组中位数集中到区间前部，再递归选择
   这些中位数的中位数作为 pivot；该枢轴能保证两侧都有固定比例的元素被淘汰。
3. partition 用 smaller、current、greater 做原地三路划分，得到“小于 pivot”“等于 pivot”
   和“大于 pivot”三个连续区间，重复的枢轴值会被一次归入中间区间。
4. target 在等值区间左侧就继续选择左区间，在右侧就选择右区间，落在等值区间则可直接返回；
   每轮只保留必然含目标秩的部分，因此返回值与完整排序后 target 位置的元素相同。
5. 五个一组和中位数的中位数保证最坏情况下也会丢弃常数比例元素，从而避免普通快速选择在
   极端输入上退化为平方时间。

时间复杂度：最坏 O(n)，BFPRT 枢轴保证每轮都淘汰固定比例的元素。
空间复杂度：O(log n)，用于递归选择中位数的中位数；划分过程原地完成。
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return select(nums, 0, nums.length - 1, nums.length - k);
    }

    private int select(int[] nums, int left, int right, int target) {
        while (left < right) {
            int pivot = medianOfMedians(nums, left, right);
            int[] equalRange = partition(nums, left, right, pivot);
            if (target < equalRange[0]) {
                right = equalRange[0] - 1;
            } else if (target > equalRange[1]) {
                left = equalRange[1] + 1;
            } else {
                return nums[target];
            }
        }
        return nums[left];
    }

    private int medianOfMedians(int[] nums, int left, int right) {
        int length = right - left + 1;
        if (length <= 5) {
            insertionSort(nums, left, right);
            return nums[left + length / 2];
        }

        int write = left;
        for (int groupStart = left; groupStart <= right; groupStart += 5) {
            int groupEnd = Math.min(groupStart + 4, right);
            insertionSort(nums, groupStart, groupEnd);
            int medianIndex = groupStart + (groupEnd - groupStart) / 2;
            swap(nums, write++, medianIndex);
        }
        int medianTarget = left + (write - left) / 2;
        return select(nums, left, write - 1, medianTarget);
    }

    private int[] partition(int[] nums, int left, int right, int pivot) {
        int smaller = left;
        int current = left;
        int greater = right;
        while (current <= greater) {
            if (nums[current] < pivot) {
                swap(nums, smaller++, current++);
            } else if (nums[current] > pivot) {
                swap(nums, current, greater--);
            } else {
                current++;
            }
        }
        return new int[] {smaller, greater};
    }

    private void insertionSort(int[] nums, int left, int right) {
        for (int index = left + 1; index <= right; index++) {
            int value = nums[index];
            int position = index - 1;
            while (position >= left && nums[position] > value) {
                nums[position + 1] = nums[position--];
            }
            nums[position + 1] = value;
        }
    }

    private void swap(int[] nums, int first, int second) {
        int temporary = nums[first];
        nums[first] = nums[second];
        nums[second] = temporary;
    }
}
