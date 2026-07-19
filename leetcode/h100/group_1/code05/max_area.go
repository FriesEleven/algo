package main

import "fmt"

func main() {
	height := []int{1, 8, 6, 2, 5, 4, 8, 3, 7}
	ans := maxArea(height)
	fmt.Println(ans)
}

func maxArea(height []int) int {
	l, r := 0, len(height)-1
	ans := 0
	for l < r {
		if height[l] <= height[r] {
			ans = max(ans, height[l]*(r-l))
			l++
		} else {
			ans = max(ans, height[r]*(r-l))
			r--
		}
	}
	return ans
}
