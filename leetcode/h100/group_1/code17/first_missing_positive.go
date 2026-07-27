package code17

/*给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。*/

func firstMissingPositive(nums []int) int {
	l, r := 0, len(nums)
	for l < r {
		if nums[l] == l+1 {
			l++
		} else if nums[l] > r || nums[l] <= l || nums[nums[l]-1] == nums[l] {
			r--
			nums[l], nums[r] = nums[r], nums[l]
		} else {
			nums[nums[l]-1], nums[l] = nums[l], nums[nums[l]-1]
		}
	}
	return l + 1
}
