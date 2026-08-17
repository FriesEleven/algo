package groupII

func permute(nums []int) [][]int {
	ans := make([][]int, 0)
	iterate(nums, 0, &ans)
	return ans
}

func iterate(nums []int, i int, ans *[][]int) {
	if i == len(nums) {
		permutation := append([]int{}, nums...)
		*ans = append(*ans, permutation)
		return
	}
	for j := i; j < len(nums); j++ {
		nums[i], nums[j] = nums[j], nums[i]
		iterate(nums, i+1, ans)
		nums[i], nums[j] = nums[j], nums[i]
	}
}
