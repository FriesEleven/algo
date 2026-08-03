/*
分割等和子集

给定只含正整数的数组 nums，判断能否将它分成两个子集，使两个子集的元素和相等。每个
数组元素必须且只能属于其中一个子集。

算法实现说明：
1. 两个子集的和相等，等价于从数组中挑出一些数字，使它们的和正好是总和的一半。总和为
   奇数时一半不是整数，可以直接返回 `false`。
2. `reachable[value]` 表示已经处理过的数字能否组成 `value`，其中 0 不选任何数字即可组成。
3. 加入数字 `number` 时，从目标值向下枚举 `value`；若此前能组成 `value - number`，现在
   就能组成 `value`。
4. 必须倒序更新：这样本轮新写入的状态不会在同一轮再次被读取，从而保证每个数组元素只用
   一次。目标值一旦可达即可提前返回。

设目标和为 T，时间复杂度 O(nT)，空间复杂度 O(T)。
*/
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int number : nums) {
            sum += number;
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[] reachable = new boolean[target + 1];
        reachable[0] = true;
        for (int number : nums) {
            if (number > target) {
                return false;
            }
            for (int value = target; value >= number; value--) {
                reachable[value] |= reachable[value - number];
            }
            if (reachable[target]) {
                return true;
            }
        }
        return reachable[target];
    }
}
