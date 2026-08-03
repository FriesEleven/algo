/*
寻找重复数

给定含 n + 1 个整数的数组 nums，每个整数都位于 [1, n]，其中只有一个整数重复（可能
出现多次）。在不修改数组且只使用常量额外空间的条件下返回该重复数。

算法实现说明：
1. 把数组下标看成节点、把 `nums[i]` 看成从节点 `i` 指向下一个节点的指针。因为数值都在
   `[1, n]`，从下标 0 不断跳转一定会进入某个环。
2. 两个不同位置指向同一个重复值，就像两条路径汇入同一个节点；这个节点正是环的入口，
   它代表要寻找的重复数字。
3. 第一阶段让 `slow` 每次走一步、`fast` 每次走两步。有环时快指针最终一定会追上慢指针，
   得到环内的相遇点。
4. 第二阶段让 `finder` 回到第一次跳转的位置，然后它与 `slow` 都每次走一步。Floyd 判圈的
   距离关系保证二者再次相遇的位置就是环入口，返回该位置对应的数字即可。
5. 指针只是在数组中读取下标，没有修改数组，也没有使用随 n 增长的额外空间。

时间复杂度 O(n)，空间复杂度 O(1)。
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        int finder = nums[0];
        while (finder != slow) {
            finder = nums[finder];
            slow = nums[slow];
        }
        return finder;
    }
}
