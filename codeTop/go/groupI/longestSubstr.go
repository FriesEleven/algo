package groupI

/*
题目：3. 无重复字符的最长子串
链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters/

题目内容：
给定字符串 s，找出其中不包含重复字符的最长连续子串，并返回该子串的长度。
子串必须是原字符串中连续的一段。
*/

func lengthOfLongestSubstring(s string) int {
	ans, l, r := 0, 0, 0
	table := make([]int, 256)
	for r < len(s) {
		table[s[r]]++
		for table[s[r]] > 1 {
			table[s[l]]--
			l++
		}
		ans = max(ans, r-l+1)
		r++
	}
	return ans
}
