/*
跳跃游戏

给定非负整数数组 nums，最初位于下标 0；nums[i] 表示从位置 i 最多能向前跳多少步。
判断是否能够到达最后一个下标。

算法实现说明：
1. `farthest` 表示利用已经检查过的所有可达位置，最远能够到达哪个下标。
2. 从左向右检查位置 `i`。如果 `i > farthest`，说明连当前位置都到不了，后面自然也无法
   到达，此时直接返回 `false`。
3. 如果当前位置可达，就用 `i + nums[i]` 更新最远覆盖范围。这里不需要真的选择某一次跳法，
   只要保留所有选择中能到达的最远位置即可。
4. 一旦覆盖范围到达最后一个下标就返回 `true`。这种贪心不会漏掉答案，因为更远的覆盖范围
   总不会比更近的覆盖范围更差。

时间复杂度 O(n)，空间复杂度 O(1)。
*/
class Solution {
    public boolean canJump(int[] nums) {
        int farthest = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > farthest) {
                return false;
            }
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest >= nums.length - 1) {
                return true;
            }
        }
        return true;
    }
}
