/*
跳跃游戏 II

给定非负整数数组 nums，nums[i] 表示从下标 i 最多能向前跳多少步。从下标 0 出发，
题目保证能够到达最后一个下标；返回到达末尾所需的最少跳跃次数。

算法实现说明：
1. `currentEnd` 是使用当前跳跃次数能够到达的最右边界，`farthest` 是再跳一步可能到达的
   最右位置。
2. 依次检查当前边界内的每个位置，用 `i + nums[i]` 扩大 `farthest`，相当于比较这一跳
   可以从哪些落脚点继续出发。
3. 当扫描到 `currentEnd` 时，当前这一层的选择已经全部比较完；此时跳跃次数加一，并把
   `currentEnd` 更新为 `farthest`。
4. 每一层代表恰好多跳一次，因此第一次把边界推进到终点时使用的跳数一定最少。

时间复杂度 O(n)，空间复杂度 O(1)。
*/
class Solution {
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        return jumps;
    }
}
