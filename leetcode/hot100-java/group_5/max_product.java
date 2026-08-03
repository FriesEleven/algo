/*
乘积最大子数组

给定整数数组 nums，找出乘积最大的非空连续子数组，并返回该乘积。测试数据保证答案在
32 位有符号整数范围内。

算法实现说明：
1. `endingMax` 和 `endingMin` 分别表示“必须以当前位置结尾”的连续子数组最大乘积和最小乘积。
2. 之所以同时保存最小值，是因为遇到负数后，原来的最小负数乘积可能一下变成最大的正数。
3. 当前数字为负时先交换最大、最小状态，再比较“只从当前数字重新开始”和“接在之前子数组
   后面”这两种选择，更新两个状态。
4. `answer` 记录所有位置的 `endingMax` 中最大者。每个连续子数组都会在它的末尾位置被考虑，
   因此不会漏掉最优答案。

时间复杂度 O(n)，空间复杂度 O(1)。
*/
class Solution {
    public int maxProduct(int[] nums) {
        int endingMax = nums[0];
        int endingMin = nums[0];
        int answer = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int number = nums[i];
            if (number < 0) {
                int temporary = endingMax;
                endingMax = endingMin;
                endingMin = temporary;
            }
            endingMax = Math.max(number, endingMax * number);
            endingMin = Math.min(number, endingMin * number);
            answer = Math.max(answer, endingMax);
        }
        return answer;
    }
}
