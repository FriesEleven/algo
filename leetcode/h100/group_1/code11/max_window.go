package code11

/*给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回 滑动窗口中的最大值 。

*/

func maxSlidingWindow(nums []int, k int) []int {
	ans := make([]int, len(nums)-k+1)
	deque := make([]int, len(nums))
	h, t := 0, 0
	for i := range k - 1 {
		for h < t && nums[deque[t-1]] <= nums[i] {
			t--
		}
		deque[t] = i
		t++
	}
	for l, r := 0, k-1; l <= len(nums)-k; l, r = l+1, r+1 {
		for h < t && nums[deque[t-1]] <= nums[r] {
			t--
		}
		deque[t] = r
		t++
		ans[l] = nums[deque[h]]
		if l == deque[h] {
			h++
		}
	}
	return ans
}
