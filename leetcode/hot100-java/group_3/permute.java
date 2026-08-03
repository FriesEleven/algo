import java.util.ArrayList;
import java.util.List;

/*
全排列

给定一个不含重复数字的整数数组 nums，返回其中所有可能的全排列，答案可以按任意顺序返回。

算法实现说明：
1. 递归参数 first 表示下一个需要确定的位置；nums[0..first-1] 是当前排列已经固定的前缀，
   后面的元素仍可选择。
2. 枚举 index 从 first 到数组末尾，把 nums[index] 与 nums[first] 交换，相当于选择该数字放在
   当前位，然后递归确定下一位。
3. 当 first 等于数组长度时，一个完整排列已经形成，把数组逐项复制为 List 加入 result。
4. 子递归返回后交换回来，恢复本层选择前的数组，使其他候选数字能从相同状态出发；由于原数组
   元素互不相同，每条选择路径对应且只对应一个排列。

时间复杂度：O(n * n!)，共有 n! 个排列，复制每个排列需要 O(n) 时间。
空间复杂度：O(n) 辅助栈空间；若计入返回结果，则为 O(n * n!)。
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, result);
        return result;
    }

    private void backtrack(int[] nums, int first, List<List<Integer>> result) {
        if (first == nums.length) {
            List<Integer> permutation = new ArrayList<>(nums.length);
            for (int number : nums) {
                permutation.add(number);
            }
            result.add(permutation);
            return;
        }

        for (int index = first; index < nums.length; index++) {
            swap(nums, first, index);
            backtrack(nums, first + 1, result);
            swap(nums, first, index);
        }
    }

    private void swap(int[] nums, int first, int second) {
        int temporary = nums[first];
        nums[first] = nums[second];
        nums[second] = temporary;
    }
}
