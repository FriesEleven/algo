/*
打家劫舍

一排房屋中每间存放一定金额，不能在同一晚偷窃相邻的两间房屋。给定非负整数数组 nums，
返回在不触发警报的前提下能够偷到的最高金额。

算法实现说明：
1. 处理当前房屋时只有两种选择：不偷它，金额仍是处理到前一间房时的最优值；偷它，则
   不能偷前一间，金额是“前前间的最优值 + 当前金额”。
2. `oneBack` 保存处理到前一间房的最优结果，`twoBack` 保存处理到前前间房的最优结果。
3. 两种选择取较大值就是当前最优值，随后把两个变量向前滚动一格。
4. 每一步都只依赖前两个状态，因此无需保存整张动态规划表；循环结束时 `oneBack` 就是答案。

时间复杂度 O(n)，空间复杂度 O(1)。
*/
class Solution {
    public int rob(int[] nums) {
        int twoBack = 0;
        int oneBack = 0;
        for (int money : nums) {
            int current = Math.max(oneBack, twoBack + money);
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }
}
