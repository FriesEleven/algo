package code13


/*给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组是数组中的一个连续部分。*/
func maxSubArray(nums []int) int {
	ans, pre := nums[0], nums[0]
	for i := 1; i < len(nums); i++ {
		pre = max(pre+nums[i], nums[i])
		ans = max(pre, ans)
	}
	return ans
}
