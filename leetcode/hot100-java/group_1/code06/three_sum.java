/*
 * 15. 三数之和
 *
 * 给定一个整数数组 nums，返回所有由三个不同下标上的元素组成、且元素之和为 0
 * 的不重复三元组。答案中不能包含重复的三元组，返回顺序不限。
 *
 * 算法实现说明：
 * 1. 先将数组排序，依次固定三元组的第一个数，并跳过与前一个相同的固定值。
 * 2. 在固定值右侧放置 second、third 双指针；和偏小时右移 second，和偏大时左移 third。
 * 3. 和为 0 时记录三元组，并跨过两侧所有相同值，从而消除重复答案。
 * 4. 排序后的单调性保证双指针每次移动都排除不可能区间，因此能找全所有组合。
 *
 * 时间复杂度：O(n^2)；除排序和返回结果外，空间复杂度：O(log n)。
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int first = 0; first < nums.length - 2; first++) {
            if (nums[first] > 0) {
                break;
            }
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            int second = first + 1;
            int third = nums.length - 1;
            while (second < third) {
                int sum = nums[first] + nums[second] + nums[third];
                if (sum < 0) {
                    second++;
                } else if (sum > 0) {
                    third--;
                } else {
                    result.add(Arrays.asList(nums[first], nums[second], nums[third]));
                    int secondValue = nums[second];
                    int thirdValue = nums[third];
                    while (second < third && nums[second] == secondValue) {
                        second++;
                    }
                    while (second < third && nums[third] == thirdValue) {
                        third--;
                    }
                }
            }
        }
        return result;
    }
}
