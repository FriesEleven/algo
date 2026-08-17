package groupI

/*
题目：912. 排序数组
链接：https://leetcode.cn/problems/sort-an-array/

题目内容：
给定整数数组 nums，将数组中的元素按升序排列并返回排序后的数组。
需要正确处理负数、重复数字以及已经有序或逆序的输入。
*/

func sortArray(nums []int) []int {
	quickSort(nums)
	return nums
}

func quickSort(nums []int) {
	if len(nums) <= 1 {
		return
	}
	pivot := nums[len(nums)/2]
	left, right := 0, len(nums)-1
	for left <= right {
		for nums[left] < pivot {
			left++
		}
		for nums[right] > pivot {
			right--
		}
		if left <= right {
			nums[left], nums[right] = nums[right], nums[left]
			left++
			right--
		}
	}
	quickSort(nums[:right+1])
	quickSort(nums[left:])
}
