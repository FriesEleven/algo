package main

import (
	"fmt"
	"slices"
)

func main() {
	strs := []string{"eat","tea","tan","ate","nat","bat"}
	res := groupAnagrams(strs);
	fmt.Println(res)
}

// 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
func groupAnagrams(strs []string) [][]string {
	mp := map[string][]string{}
	for _, str := range strs {
		s := []byte(str)
		slices.Sort(s)
		tag := string(s)
		mp[tag] = append(mp[tag], str)
	}
	ans := make([][]string, 0, len(mp))
	for _, val := range mp {
		ans = append(ans, val)
	}
    return ans;
}