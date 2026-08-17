package groupI

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
