package main

/*
给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。

测试用例保证答案唯一。
*/
func minWindow(s string, t string) string {
	debt := [256]int{}
	cnt := len(t)
	for _, ch := range t {
		debt[ch]--
	}
	start, length := 0, len(s)+1
	for l, r := 0, 0; r < len(s); r++ {
		if debt[s[r]] < 0 {
			cnt--
		}
		debt[s[r]]++
		if cnt == 0 {
			for l < r && debt[s[l]] > 0 {
				debt[s[l]]--
				l++
			}
			if r-l+1 < length {
				length = r - l + 1
				start = l
			}
		}
	}
	if length == len(s)+1 {
		return ""
	}
	return s[start : start+length]
}