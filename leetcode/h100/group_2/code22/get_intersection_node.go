package code22

import "math"

// ListNode 是单链表节点。
type ListNode struct {
	Val  int
	Next *ListNode
}

/*
相交链表

给定两个单链表的头节点 headA 和 headB，请找出并返回两个链表相交的起始节点。
如果两个链表不存在相交节点，返回 nil。链表在函数返回后必须保持原有结构。
*/
func getIntersectionNode(headA, headB *ListNode) *ListNode {
	lena, lenb := 0, 0
	p1, p2 := headA, headB
	for p1 != nil {
		lena++
		p1 = p1.Next
	}
	for p2 != nil {
		lenb++
		p2 = p2.Next
	}
	p1, p2 = headA, headB
	if lena > lenb {
		p1, p2 = headB, headA
	}
	diff := math.Abs(float64(lena) - float64(lenb))
	for diff > 0 {
		p1 = p1.Next
		diff--
	}
	for p1 != p2 {
		p1 = p1.Next
		p2 = p2.Next
	}
	return p1
}
