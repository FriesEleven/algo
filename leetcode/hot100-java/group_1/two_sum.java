/*
 * 1. 两数之和
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请在数组中找出和为 target
 * 的两个整数，并返回它们的下标。每个输入只会有一个有效答案，且同一个元素
 * 不能重复使用；两个下标可以按任意顺序返回。
 *
 * 算法实现说明：
 * 1. 用哈希表 indexByValue 保存已经扫描过的“数值 -> 下标”。
 * 2. 扫描到 nums[i] 时先计算差值 target - nums[i]，再去表中寻找它，先查后存可避免重复使用当前元素。
 * 3. 一旦找到差值，就返回差值的旧下标和当前下标；题目保证答案唯一，因此无需继续扫描。
 *
 * 时间复杂度：O(n)；空间复杂度：O(n)。
 */
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByValue = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            Integer complementIndex = indexByValue.get(complement);
            if (complementIndex != null) {
                return new int[] {complementIndex, i};
            }
            indexByValue.put(nums[i], i);
        }
        return new int[0];
    }
}
