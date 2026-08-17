package groupII

/*
题目：33. 搜索旋转排序数组
链接：https://leetcode.cn/problems/search-in-rotated-sorted-array/

题目内容：
一个元素互不相同的升序数组在未知位置经过旋转。给定旋转后的数组 nums 和目标值 target，
若目标值存在则返回其下标，否则返回 -1；算法时间复杂度应为 O(log n)。
*/

func search(nums []int, target int) int {
	l, r := 0, len(nums)-1
	for l <= r {
		mid := l + (r-l)/2
		if nums[mid] == target {
			return mid
		}
		if nums[mid] >= nums[0] {
			if nums[0] <= target && target < nums[mid] {
				r = mid - 1
			} else {
				l = mid + 1
			}
		} else {
			if nums[mid] < target && target <= nums[len(nums)-1] {
				l = mid + 1
			} else {
				r = mid - 1
			}
		}
	}
	return -1
}
