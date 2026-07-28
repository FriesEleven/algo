package code24

// ListNode 是单链表节点。
type ListNode struct {
	Val  int
	Next *ListNode
}

/*
回文链表

给定一个单链表的头节点 head，请判断该链表是否为回文链表。
*/
func isPalindrome(head *ListNode) (ok bool) {
	if head == nil || head.Next == nil {
		return true
	}
	slow, fast := head, head
	for fast.Next != nil && fast.Next.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
	}
	tail := reverse(slow)
	for head != nil {
		if head.Val != tail.Val {
			return false
		}
		head = head.Next
		tail = tail.Next
	}
	return true
}

func reverse(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	var pre *ListNode
	cur := head
	for cur != nil {
		next := cur.Next
		cur.Next = pre
		pre = cur
		cur = next
	}
	return pre
}
