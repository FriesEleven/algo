import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
前 K 个高频元素

给定整数数组 nums 和整数 k，返回其中出现频率最高的 k 个元素。题目保证答案唯一，结果可以
按任意顺序返回，并要求算法时间复杂度严格优于 O(n log n)。

算法实现说明：
1. 第一次遍历 nums，用 frequency 哈希表记录每个不同数字出现的次数，查找和更新平均为常数
   时间。
2. 创建下标范围 0..n 的 buckets，桶下标就是出现次数；把每个数字放进 buckets[count]，因为
   任一数字的频率都不会超过数组长度 n。
3. 从频率 n 向 1 逆序扫描桶，把非空桶中的数字依次写入 result，写满 k 个元素后立即停止。
4. 逆序扫描保证先取到的数字频率不低于后取到的数字；题目保证答案集合唯一，因此前 k 个正好
   是所求元素，而且整个过程不需要对不同元素排序。

时间复杂度：O(n)。
空间复杂度：O(n)。
*/
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int number : nums) {
            frequency.put(number, frequency.getOrDefault(number, 0) + 1);
        }

        List<List<Integer>> buckets = new ArrayList<>(nums.length + 1);
        for (int count = 0; count <= nums.length; count++) {
            buckets.add(null);
        }
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            int count = entry.getValue();
            if (buckets.get(count) == null) {
                buckets.set(count, new ArrayList<>());
            }
            buckets.get(count).add(entry.getKey());
        }

        int[] result = new int[k];
        int size = 0;
        for (int count = nums.length; count > 0 && size < k; count--) {
            List<Integer> values = buckets.get(count);
            if (values == null) {
                continue;
            }
            for (int value : values) {
                result[size++] = value;
                if (size == k) {
                    break;
                }
            }
        }
        return result;
    }
}
