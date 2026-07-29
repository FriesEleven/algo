package code29

// ListNode 是单链表节点。
type ListNode struct {
	Val  int
	Next *ListNode
}

/*
删除链表的倒数第 N 个节点

给定一个链表的头节点 head，删除链表的倒数第 n 个节点，并返回链表的头节点。
*/
func removeNthFromEnd(head *ListNode, n int) (ans *ListNode) {
	slow, fast := head, head
	for n > 0 && fast != nil {
		fast = fast.Next
		n--
	}
	if fast == nil {
		ans = head.Next
		head.Next = nil
		return
	}
	for fast.Next != nil {
		slow = slow.Next
		fast = fast.Next
	}
	node := slow.Next
	slow.Next = node.Next
	node.Next = nil
	return head
}
