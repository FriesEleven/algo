/*
多数元素

给定长度为 n 的整数数组 nums，返回其中出现次数严格大于 n / 2 的元素。题目保证这样
的多数元素一定存在。

算法实现说明：
1. `candidate` 表示当前候选多数元素，`votes` 表示它在尚未抵消的元素中领先多少票。
2. 当票数为 0 时，当前数字成为新候选者；遇到与候选者相同的数字就加一票，不同就减一票，
   相当于让两个不同元素互相抵消。
3. 真正的多数元素出现次数超过数组一半，即使它与每个其他元素一一抵消，最后也一定还有
   剩余，所以最终候选者必然是它。
4. 题目保证多数元素存在，因此不需要再进行第二遍计数验证。

时间复杂度 O(n)，空间复杂度 O(1)。
*/
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int votes = 0;
        for (int number : nums) {
            if (votes == 0) {
                candidate = number;
            }
            votes += number == candidate ? 1 : -1;
        }
        return candidate;
    }
}
