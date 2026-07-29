package code30

// ListNode 是单链表节点。
type ListNode struct {
	Val  int
	Next *ListNode
}

/*
两两交换链表中的节点

给定一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
必须在不修改节点内部值的情况下完成，只能进行节点交换。
*/
func swapPairs(head *ListNode) *ListNode {
	dummy := &ListNode{Next: head}
	for pre := dummy; pre.Next != nil && pre.Next.Next != nil; {
		first, second := pre.Next, pre.Next.Next
		first.Next = second.Next
		second.Next = first
		pre.Next = second
		pre = first
	}
	return dummy.Next
}
