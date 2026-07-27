package code15

/*给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。*/
func rotate(nums []int, k int) {
	k = k % len(nums)
	reverse(nums, 0, len(nums)-1)
	reverse(nums, 0, k- 1)
	reverse(nums, k, len(nums)-1)
}

func reverse(nums []int, l, r int) {
	for l < r {
		nums[l], nums[r] = nums[r], nums[l]
		l++
		r--
	}
}
