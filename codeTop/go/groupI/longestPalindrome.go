package groupI

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
