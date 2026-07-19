package main

import "fmt"

func main() {
	nums := []int{100, 4, 200, 1, 3, 2}
	ans := longestConsecutive(nums)
	fmt.Println(ans)
}

/*给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

 */
func longestConsecutive(nums []int) int {
	ans := 0
	set := map[int]bool{}
	for _, num := range nums {
		set[num] = true
	}
	for num := range set {
		if !set[num-1] {
			cur := num
			len := 1
			for set[cur+1] {
				cur++
				len++
			}
			if ans < len {
				ans = len
			}
		}
	}
	return ans
}
