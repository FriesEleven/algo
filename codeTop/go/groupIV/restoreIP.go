package groupiv

import "strings"

func restoreIpAddresses(s string) []string {
	ans := make([]string, 0)
	if len(s) < 4 || len(s) > 12 {
		return ans
	}
	parts := make([]string, 4)
	return dfs(s, 0, 0, parts, ans)
}

func dfs(s string, index, part int, parts, ans []string) []string {
	leftChars, leftParts := len(s)-index, 4-part
	if leftChars < leftParts || leftChars > leftParts*3 {
		return ans
	}
	if part == 4 {
		return append(ans, strings.Join(parts, "."))
	}
	val := 0
	for end := index; end < len(s) && end < index+3; end++ {
		if end > index && s[index] == '0' {
			break
		}
		val = val*10 + int(s[end]-'0')
		if val > 255 {
			break
		}
		parts[part] = s[index : end+1]
		ans = dfs(s, end+1, part+1, parts, ans)
	}
	return ans
}
