package groupII

func lengthOfLIS(nums []int) int {
	list := make([]int, 0, len(nums))
	for _, num := range nums {
		index := indexInLIS(list, num)
		if index == -1 {
			list = append(list, num)
		} else {
			list[index] = num
		}
	}
	return len(list)
}

func indexInLIS(list []int, num int) (ans int) {
	l, r, ans := 0, len(list)-1, -1
	for l <= r {
		mid := l + (r-l)/2
		if list[mid] >= num {
			ans = mid
			r = mid - 1
		} else {
			l = mid + 1
		}
	}
	return
}
