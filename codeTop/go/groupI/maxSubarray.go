package groupI

/*
题目：53. 最大子数组和
链接：https://leetcode.cn/problems/maximum-subarray/

题目内容：
给定整数数组 nums，找出具有最大元素和的连续非空子数组，并返回它的元素和。
子数组必须是原数组中连续的一段。
*/

func maxSubArray(nums []int) int {
	ans, pre := nums[0], nums[0]
	for i := 1; i < len(nums); i++ {
		pre = max(pre+nums[i], nums[i])
		ans = max(pre, ans)
	}
	return ans
}
