package groupiv

func trap(height []int) int {
	ans, n := 0, len(height)
	l, r, lmax, rmax := 1, n-2, height[0], height[n-1]
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
