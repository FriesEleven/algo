/*
完全平方数

给定正整数 n，返回和为 n 的完全平方数（1、4、9、16 等）的最少数量。同一个完全平方数
可以重复使用。

算法实现说明：
1. 数论定理保证任意正整数最多只需 4 个完全平方数，因此答案只可能是 1、2、3、4。
2. 如果 `n` 本身是完全平方数，显然只需 1 个，代码先用 `isSquare` 判断这一情况。
3. 不断除去因子 4 后，若剩余数字除以 8 余 7，三平方定理说明它不可能由 3 个以内的平方数
   组成，因此答案一定是 4。
4. 接着枚举第一个平方数 `first * first`，检查剩余部分是否也是平方数；找到就说明答案为 2。
5. 前面的 1、2、4 三种情况都不是时，根据定理答案只能是 3。这样不用做金额为 n 的动态规划。

时间复杂度 O(sqrt(n))，空间复杂度 O(1)。
*/
class Solution {
    public int numSquares(int n) {
        if (isSquare(n)) {
            return 1;
        }

        int reduced = n;
        while (reduced % 4 == 0) {
            reduced /= 4;
        }
        if (reduced % 8 == 7) {
            return 4;
        }

        for (int first = 1; (long) first * first <= n; first++) {
            if (isSquare(n - first * first)) {
                return 2;
            }
        }
        return 3;
    }

    private boolean isSquare(int value) {
        int root = (int) Math.sqrt(value);
        return root * root == value;
    }
}
