package groupI

/*
题目：215. 数组中的第 K 个最大元素
链接：https://leetcode.cn/problems/kth-largest-element-in-an-array/

题目内容：
给定整数数组 nums 和整数 k，返回数组按降序排列后的第 k 个元素。
需要寻找的是排序意义上的第 k 大元素，而不是第 k 个不同的元素。
*/

import (
	"container/heap"
)

type MinHeap []int

func (h MinHeap) Len() int {
	return len(h)
}

func (h MinHeap) Less(i, j int) bool {
	return h[i] < h[j]
}

func (h MinHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}

func (h *MinHeap) Push(x any) {
	*h = append(*h, x.(int))
}

func (h *MinHeap) Pop() any {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[:n-1]
	return x
}

func findKthLargest(nums []int, k int) int {
	maxheap := &MinHeap{}
	heap.Init(maxheap)
	for i := 0; i < k; i++ {
		heap.Push(maxheap, nums[i])
	}
	for i := k; i < len(nums); i++ {
		if nums[i] > (*maxheap)[0] {
			heap.Pop(maxheap)
			heap.Push(maxheap, nums[i])
		}
	}
	return (*maxheap)[0]
}
