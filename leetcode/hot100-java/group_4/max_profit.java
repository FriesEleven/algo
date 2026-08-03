/*
买卖股票的最佳时机

给定数组 prices，prices[i] 表示第 i 天的股票价格。只能选择某一天买入，并在之后的
某一天卖出；返回这一次交易能够获得的最大利润。如果无法获利，返回 0。

算法实现说明：
1. `minPrice` 记录扫描到当前日期为止见过的最低价格，可以把它理解为最合适的买入价。
2. 每到一天，都用“今天价格减去此前最低价格”算出今天卖出能够得到的利润。
3. `best` 始终保存所有卖出方案中的最大利润，然后再继续向后扫描。
4. 因为最低价格只来自今天或更早的日期，所以天然保证了先买入、后卖出；若始终不能赚钱，
   `best` 保持为 0。

时间复杂度 O(n)，空间复杂度 O(1)。
*/
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int best = 0;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            best = Math.max(best, price - minPrice);
        }
        return best;
    }
}
