package groupI

/*
题目：5. 最长回文子串
链接：https://leetcode.cn/problems/longest-palindromic-substring/

题目内容：
给定字符串 s，返回其中最长的回文子串。回文串从左向右和从右向左读取完全相同；
存在多个最长答案时，返回任意一个即可。
*/

func longestPalindrome(s string) string {
	str := []rune(s)
	maxLen, strEnd := manacher(str)
	return string(str[strEnd-maxLen : strEnd])
}

func manacher(s []rune) (maxLen, strEnd int) {
	expand := expandString(s)
	radius := make([]int, len(expand))
	center, right := 0, 0
	for i := range expand {
		length := 1
		if right > i {
			length = min(right-i, radius[2*center-i])
		}
		for i-length >= 0 &&
			i+length < len(expand) &&
			expand[i-length] == expand[i+length] {
			length++
		}
		if i+length > right {
			center = i
			right = i + length
		}
		if length > maxLen {
			maxLen = length - 1
			strEnd = (i + length - 1) / 2
		}
		radius[i] = length
	}
	return maxLen, strEnd
}

func expandString(s []rune) []rune {
	expand := make([]rune, 2*len(s)+1)
	for i := range expand {
		if i&1 == 0 {
			expand[i] = '#'
		} else {
			expand[i] = s[i/2]
		}
	}
	return expand
}
