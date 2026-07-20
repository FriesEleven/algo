package main

func findAnagrams(s string, p string) []int {
	ans := []int{}
	if len(s) < len(p) {
		return ans
	}
	stable := [26]int{}
	ptable := [26]int{}
	for i := range len(p) {
		stable[s[i]-'a']++
		ptable[p[i]-'a']++
	}
	if stable == ptable {
		ans = append(ans, 0)
	}
	for i := range len(s) - len(p) {
		stable[s[i]-'a']--
		stable[s[i+len(p)]-'a']++
		if stable == ptable {
			ans = append(ans, i+1)
		}
	}
	return ans
}
