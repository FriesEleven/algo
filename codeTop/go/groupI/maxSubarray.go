package groupI

func maxSubArray(nums []int) int {
	ans, pre := nums[0], nums[0]
	for i := 1; i < len(nums); i++ {
		pre = max(pre+nums[i], nums[i])
		ans = max(pre, ans)
	}
	return ans
}
