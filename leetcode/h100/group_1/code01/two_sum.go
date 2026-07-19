package main



func main() {
    
}

// 两数之和，返回下标
func twoSum(nums []int, target int) []int {
    indexMap := make(map[int]int);
    for i, num := range nums {
        if val, flag := indexMap[target - num]; flag {
           return []int {i, val} 
        }
        indexMap[num] = i;
    }
    return nil;
}