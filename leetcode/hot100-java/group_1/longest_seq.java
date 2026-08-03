/*
 * 128. 最长连续序列
 *
 * 给定一个未排序的整数数组 nums，找出数字连续的最长序列长度；序列中的元素
 * 不要求在原数组中相邻。算法需要达到线性时间复杂度。
 *
 * 算法实现说明：
 * 1. 先把所有数字放入哈希集合，既能去重，也能在常数平均时间内判断某个数字是否存在。
 * 2. 只有当 num - 1 不在集合中时，才把 num 当作一段连续序列的起点，避免从序列中间重复计数。
 * 3. 从起点不断查询下一个整数并累计长度，最后用所有序列长度更新 longest。
 *
 * 时间复杂度：O(n)，每个不同元素至多被连续扫描一次；空间复杂度：O(n)。
 */
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> values = new HashSet<>();
        for (int num : nums) {
            values.add(num);
        }

        int longest = 0;
        for (int num : values) {
            if (!values.contains(num - 1)) {
                int current = num;
                int length = 1;
                while (values.contains(current + 1)) {
                    current++;
                    length++;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }
}
