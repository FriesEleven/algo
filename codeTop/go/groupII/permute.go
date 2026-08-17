package groupII

/*
题目：46. 全排列
链接：https://leetcode.cn/problems/permutations/

题目内容：
给定一个不包含重复数字的整数数组 nums，返回其中所有可能的全排列。
排列和最终答案可以按任意顺序返回。
*/

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
