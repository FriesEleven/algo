package code34

import "container/heap"

type nodeHeap []*ListNode

func (h nodeHeap) Len() int {
	return len(h)
}

func (h nodeHeap) Less(i, j int) bool {
	return h[i].Val < h[j].Val
}

func (h nodeHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}

func (h *nodeHeap) Push(x any) {
	*h = append(*h, x.(*ListNode))
}

func (h *nodeHeap) Pop() any {
	old := *h
	last := len(old) - 1
	node := old[last]
	*h = old[:last]
	return node
}

// ListNode 是单链表节点。
type ListNode struct {
	Val  int
	Next *ListNode
}

/*
合并 K 个升序链表

给定一个链表数组 lists，其中每个链表都已按升序排列。
请将所有链表合并成一个升序链表并返回。
*/
func mergeKLists1(lists []*ListNode) *ListNode {
	h := make(nodeHeap, 0, len(lists))
	for _, node := range lists {
		if node != nil {
			h = append(h, node)
		}
	}
	heap.Init(&h)
	dummy := &ListNode{}
	tail := dummy
	for h.Len() > 0 {
		node := heap.Pop(&h).(*ListNode)
		tail.Next = node
		tail = tail.Next
		if node.Next != nil {
			heap.Push(&h, node.Next)
		}
	}
	return dummy.Next
}

func mergeKLists(lists []*ListNode) *ListNode {
	if len(lists) == 0 {
		return nil
	}
	if len(lists) == 1 {
		return lists[0]
	}
	mid := len(lists) / 2
	left := mergeKLists(lists[:mid])
	right := mergeKLists(lists[mid:])
	return mergeSortedList(left, right)
}

func mergeSortedList(l1, l2 *ListNode) *ListNode {
	if l1 == nil {
		return l2
	}
	if l2 == nil {
		return l1
	}
	dummy := &ListNode{}
	tail := dummy
	for l1 != nil && l2 != nil {
		if l1.Val <= l2.Val {
			tail.Next = l1
			l1 = l1.Next
		} else {
			tail.Next = l2
			l2 = l2.Next
		}
		tail = tail.Next
	}
	if l1 != nil {
		tail.Next = l1
	}
	if l2 != nil {
		tail.Next = l2
	}
	return dummy.Next
}
