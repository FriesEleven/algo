package code14

/*以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。*/

func merge(intervals [][]int) [][]int {
	if len(intervals) == 0 {
		return [][]int{}
	}
	ans := [][]int{}
	maxEnd := 0
	for _, interval := range intervals {
		maxEnd = max(maxEnd, interval[1])
	}
	diff := make([]int, maxEnd*2+2)
	for _, interval := range intervals {
		diff[interval[0]*2]++
		diff[interval[1]*2+1]--
	}
	presum := make([]int, len(diff))
	presum[0] = diff[0]
	for i := 1; i < len(diff); i++ {
		presum[i] = presum[i-1] + diff[i]
	}
	start := -1
	for i, cnt := range presum {
		if cnt > 0 {
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
