package main

func subarraySum(nums []int, k int) int {
	ans, sum := 0, 0
	mp := map[int]int{}
	mp[0] = 1
	for _, num := range nums {
		sum += num
		ans += mp[sum-k]
		mp[sum]++
	}
	return ans
}
