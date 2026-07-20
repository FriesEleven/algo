package main

func lengthOfLongestSubstring(s string) int {
	ans, l, r := 0, 0, 0
	table := make([]int, 256)
	for r < len(s) {
		table[s[r]]++
		for table[s[l]] > 1 {
			table[s[l]]--
			l++
		}
		ans = max(ans, r-l+1)
		r++
	}
	return ans
}
