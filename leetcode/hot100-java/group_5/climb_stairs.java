/*
爬楼梯

共有 n 级台阶，每次只能向上走 1 级或 2 级，求到达第 n 级共有多少种互不相同的走法。
答案满足 32 位有符号整数范围。

算法实现说明：
1. 到达第 `k` 级的最后一步只可能来自第 `k - 1` 级或第 `k - 2` 级，所以方案数满足
   斐波那契递推，答案就是第 `n + 1` 个斐波那契数。
2. 矩阵 `{{1, 1}, {1, 0}}` 可以一次完成这组递推；把它乘方 `n` 次后，左上角正好是答案。
3. 代码没有真的连续乘 `n` 次，而是使用快速幂：指数当前位为 1 时把 `base` 乘进
   `result`，每轮再让 `base` 自乘、指数右移一位。
4. 这样每轮都把需要处理的指数减半；`result` 从单位矩阵开始，保证尚未选择任何乘方时
   不会改变结果。

时间复杂度 O(log n)，空间复杂度 O(1)。
*/
class Solution {
    public int climbStairs(int n) {
        long[][] result = {{1, 0}, {0, 1}};
        long[][] base = {{1, 1}, {1, 0}};
        int exponent = n;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = multiply(result, base);
            }
            base = multiply(base, base);
            exponent >>= 1;
        }
        return (int) result[0][0];
    }

    private long[][] multiply(long[][] first, long[][] second) {
        return new long[][] {
            {
                first[0][0] * second[0][0] + first[0][1] * second[1][0],
                first[0][0] * second[0][1] + first[0][1] * second[1][1]
            },
            {
                first[1][0] * second[0][0] + first[1][1] * second[1][0],
                first[1][0] * second[0][1] + first[1][1] * second[1][1]
            }
        };
    }
}
