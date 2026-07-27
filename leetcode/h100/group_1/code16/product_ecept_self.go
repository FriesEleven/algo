package code16

func productExceptSelf(nums []int) []int {
	pre := make([]int, len(nums)+1)
	suf := make([]int, len(nums)+1)
	pre[0] = 1
	for i := 1; i < len(nums); i++ {
		pre[i] = pre[i-1] * nums[i-1]
	}
	suf[len(nums)-1] = 1
	for i := len(nums) - 2; i >= 0; i-- {
		suf[i] = suf[i+1] * nums[i+1]
	}
	ans := make([]int, len(nums))
	for i, _ := range ans {
		ans[i] = pre[i] * suf[i]
	}
	return ans
}
