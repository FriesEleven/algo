/*
 * 283. 移动零
 *
 * 给定一个整数数组 nums，把其中所有 0 移动到数组末尾，同时保持所有非零元素
 * 的相对顺序。必须直接修改输入数组，不能复制出另一个数组。
 *
 * 算法实现说明：
 * 1. write 指向下一个非零元素应写入的位置，read 从左到右检查每个元素。
 * 2. 遇到非零元素时把它写到 write；若发生前移，再把它原来的 read 位置置为 0。
 * 3. write 只按非零元素原有顺序递增，所以相对顺序不变，扫描结束后所有零自然集中在末尾。
 *
 * 时间复杂度：O(n)；空间复杂度：O(1)。
 */
class Solution {
    public void moveZeroes(int[] nums) {
        int write = 0;
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != 0) {
                if (write != read) {
                    nums[write] = nums[read];
                    nums[read] = 0;
                }
                write++;
            }
        }
    }
}
