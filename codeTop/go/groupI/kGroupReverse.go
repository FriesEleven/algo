package groupI

func reverseKGroup(head *ListNode, k int) *ListNode {
	if k <= 1 || head == nil {
		return head
	}
	dummy := &ListNode{Next: head}
	groupPre := dummy
	for {
		kth := groupPre
		for i := 0; i < k && kth != nil; i++ {
			kth = kth.Next
		}
		if kth == nil {
			break
		}
		groupNext := kth.Next
		groupStart := groupPre.Next
		pre, cur := groupNext, groupStart
		for cur != groupNext {
			next := cur.Next
			cur.Next = pre
			pre, cur = cur, next
		}
		groupPre.Next = kth
		groupPre = groupStart
	}
	return dummy.Next
}
