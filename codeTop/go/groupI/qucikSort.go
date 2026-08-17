package groupI

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
