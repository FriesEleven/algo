/*
 * 560. 和为 K 的子数组
 *
 * 给定一个整数数组 nums 和整数 k，统计并返回数组中元素总和恰好为 k 的连续、
 * 非空子数组个数。数组元素可以为负数，因此不能使用只向前收缩的普通滑动窗口。
 *
 * 算法实现说明：
 * 1. prefixFrequency 记录每种历史前缀和出现的次数，并先放入前缀和 0 的一次记录。
 * 2. 扫描元素并更新 prefixSum；若历史前缀和等于 prefixSum - k，两段前缀之差就是和为 k 的子数组。
 * 3. 把该历史前缀和的出现次数累加到答案，再记录当前前缀和，便能统计所有不同终点的子数组。
 *
 * 时间复杂度：O(n)；空间复杂度：O(n)。
 */
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixFrequency = new HashMap<>();
        prefixFrequency.put(0, 1);
        int prefixSum = 0;
        int count = 0;
        for (int num : nums) {
            prefixSum += num;
            count += prefixFrequency.getOrDefault(prefixSum - k, 0);
            prefixFrequency.merge(prefixSum, 1, Integer::sum);
        }
        return count;
    }
}
