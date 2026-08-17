package groupII

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
