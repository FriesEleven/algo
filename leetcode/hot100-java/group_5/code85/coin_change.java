import java.util.Arrays;

/*
零钱兑换

给定不同面额的硬币数组 coins 和总金额 amount。每种硬币可以使用任意次，返回凑成该金额
所需的最少硬币数；如果无法凑成，返回 -1。金额为 0 时答案为 0。

算法实现说明：
1. `dp[x]` 表示凑出金额 `x` 最少需要多少枚硬币；`dp[0] = 0`，因为凑出 0 元无需硬币。
2. 其余位置先填成 `amount + 1`，这个值比任何可能答案都大，用来表示“目前无法凑出”。
3. 依次计算每个金额 `value`。对每种不大于它的硬币 `coin`，如果最后一枚使用该硬币，
   候选答案就是 `dp[value - coin] + 1`，从所有候选中取最小值。
4. 同一面额会在不同金额的状态中反复参与转移，因此自然允许无限次使用硬币。
5. 最后若 `dp[amount]` 仍是哨兵值就返回 -1，否则返回保存的最少数量。

时间复杂度 O(amount * coins.length)，空间复杂度 O(amount)。
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        int unreachable = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, unreachable);
        dp[0] = 0;

        for (int value = 1; value <= amount; value++) {
            for (int coin : coins) {
                if (coin <= value) {
                    dp[value] = Math.min(dp[value], dp[value - coin] + 1);
                }
            }
        }
        return dp[amount] == unreachable ? -1 : dp[amount];
    }
}
