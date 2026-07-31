/*
寻找两个正序数组的中位数

给定两个大小分别为 m 和 n、按非递减顺序排列的整数数组 nums1 和 nums2，返回合并后数据集的
中位数。两个数组不会同时为空，要求算法时间复杂度为 O(log(m + n))。

算法实现说明：
1. 先保证 nums1 是较短数组，只在它的 0..m 个分割位置上二分；leftSize 规定两个数组左半部分
   合计应有 (m + n + 1) / 2 个元素。
2. 选定 firstCut 后，用 leftSize - firstCut 得到 secondCut；四个边界变量分别表示两数组分割线
   左右紧邻的值，分割落在数组端点时使用整数极值作为哨兵。
3. 若 firstLeft <= secondRight 且 secondLeft <= firstRight，说明左半所有值都不大于右半所有值，
   已找到正确分割；总长度为奇数时取左侧最大值，偶数时取左右边界中间值的平均数。
4. 若 firstLeft 太大，nums1 的分割线应左移；否则应右移。每次排除一半候选分割位置，并且在
   有序数组条件下必能找到唯一满足交叉边界关系的分割。

时间复杂度：O(log(min(m, n)))。
空间复杂度：O(1)。
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int firstLength = nums1.length;
        int secondLength = nums2.length;
        int leftSize = (firstLength + secondLength + 1) / 2;
        int low = 0;
        int high = firstLength;

        while (low <= high) {
            int firstCut = low + (high - low) / 2;
            int secondCut = leftSize - firstCut;

            int firstLeft = firstCut == 0 ? Integer.MIN_VALUE : nums1[firstCut - 1];
            int firstRight = firstCut == firstLength ? Integer.MAX_VALUE : nums1[firstCut];
            int secondLeft = secondCut == 0 ? Integer.MIN_VALUE : nums2[secondCut - 1];
            int secondRight = secondCut == secondLength ? Integer.MAX_VALUE : nums2[secondCut];

            if (firstLeft <= secondRight && secondLeft <= firstRight) {
                int leftMaximum = Math.max(firstLeft, secondLeft);
                if ((firstLength + secondLength) % 2 == 1) {
                    return leftMaximum;
                }
                int rightMinimum = Math.min(firstRight, secondRight);
                return ((long) leftMaximum + rightMinimum) / 2.0;
            }

            if (firstLeft > secondRight) {
                high = firstCut - 1;
            } else {
                low = firstCut + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays must be sorted");
    }
}
