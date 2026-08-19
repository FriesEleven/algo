package groupIII

func merge(intervals [][]int) [][]int {
	if len(intervals) == 0 {
		return [][]int{}
	}
	ans := [][]int{}
	maxEnd := 0
	for _, interval := range intervals {
		maxEnd = max(maxEnd, interval[1])
	}
	diff := make([]int, 2*maxEnd+2)
	for _, interval := range intervals {
		diff[interval[0]*2]++
		diff[interval[1]*2+1]--
	}
	preSum := make([]int, len(diff))
	preSum[0] = diff[0]
	for i := 1; i < len(preSum); i++ {
		preSum[i] = preSum[i-1] + diff[i]
	}
	start := -1
	for i, sum := range preSum {
		if sum > 0 {
			if start == -1 {
				start = i
			}
		} else if start != -1 {
			ans = append(ans, []int{start / 2, i / 2})
			start = -1
		}
	}
	return ans
}
