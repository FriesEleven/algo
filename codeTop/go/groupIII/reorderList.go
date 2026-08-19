package groupIII

func reorderList(head *ListNode) {
	if head == nil || head.Next == nil {
		return
	}
	mid := findMid(head)
	l, r := head, mid.Next
	mid.Next = nil
	r = reverseList(r)
	mergeList(l, r)
}

func findMid(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	slow, fast := head, head
	for fast.Next != nil && fast.Next.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
	}
	return slow
}

func reverseList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	var pre *ListNode = nil
	cur:= head
	for cur != nil {
		next := cur.Next
		cur.Next = pre
		pre, cur = cur, next
	}
	return pre
}

func mergeList(l1, l2 *ListNode) {
	for l1 != nil && l2 != nil {
		l1Ne := l1.Next
		l2Ne := l2.Next
		l1.Next = l2
		l1 = l1Ne
		l2.Next = l1
		l2 = l2Ne
	}
}
