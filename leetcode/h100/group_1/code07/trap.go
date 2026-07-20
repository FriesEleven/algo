package main

/*给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
func trap(height []int) int {
	ans, n := 0, len(height)
	l, lmax, r, rmax := 1, height[0], n-2, height[n-1]
	for l <= r {
		if lmax <= rmax {
			ans += max(0, lmax-height[l])
			lmax = max(lmax, height[l])
			l++
		} else {
			ans += max(0, rmax-height[r])
			rmax = max(rmax, height[r])
			r--
		}
	}
	return ans
}
