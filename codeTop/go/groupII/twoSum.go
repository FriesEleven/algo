package groupII

/*
题目：1. 两数之和
链接：https://leetcode.cn/problems/two-sum/

题目内容：
给定整数数组 nums 和目标值 target，找出数组中和为 target 的两个元素并返回它们的下标。
同一个元素不能重复使用；题目保证只有一个有效答案，下标顺序不限。
*/

func twoSum(nums []int, target int) []int {
	indexMap := map[int]int{}
	for i, num := range nums {
		if preIndex, ok := indexMap[target-num]; ok {
			return []int{i, preIndex}
		}
		indexMap[num] = i
	}
	return nil
}
