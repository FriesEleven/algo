/*
 * 238. 除自身以外数组的乘积
 *
 * 给定整数数组 nums，返回数组 answer，其中 answer[i] 等于 nums 中除 nums[i]
 * 之外其余所有元素的乘积。题目保证任意前缀或后缀乘积在 32 位整数范围内；
 * 不能使用除法，并要求线性时间完成。
 *
 * 算法实现说明：
 * 1. 从左向右填写 answer，使 answer[i] 先保存 nums[i] 左侧所有元素的乘积。
 * 2. 再从右向左扫描，用 suffixProduct 累积 nums[i] 右侧所有元素的乘积。
 * 3. 将左右乘积相乘写回 answer[i]，恰好排除了 nums[i] 自身，也能自然处理数组中的零。
 *
 * 时间复杂度：O(n)；除返回数组外，空间复杂度：O(1)。
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        answer[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        int suffixProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            answer[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        return answer;
    }
}
