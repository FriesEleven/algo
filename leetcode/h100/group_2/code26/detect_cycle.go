package code26

// ListNode 是单链表节点。
type ListNode struct {
	Val  int
	Next *ListNode
}

/*
环形链表 II

给定一个链表的头节点 head，返回链表开始入环的第一个节点。
如果链表无环，则返回 nil；不允许修改链表。
*/
func detectCycle(head *ListNode) *ListNode {
	if head == nil || head.Next == nil || head.Next.Next==nil {
		return nil
	}
	slow, fast := head, head
	for fast.Next != nil && fast.Next.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
		if fast == slow {
			break
		}
	}
	if fast!=slow {
		return nil
	}
	fast = head
	for fast != slow {
		slow = slow.Next
		fast = fast.Next
	}
	return slow
}
