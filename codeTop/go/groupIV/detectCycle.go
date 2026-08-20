package groupiv

func detectCycle(head *ListNode) *ListNode {
	if head == nil || head.Next == nil || head.Next.Next == nil {
		return nil
	}
	slow, fast := head, head
	for fast.Next != nil && fast.Next.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
		if slow == fast {
			break
		}
	}
	if fast != slow {
		return nil
	}
	fast = head
	for fast != slow {
		slow = slow.Next
		fast = fast.Next
	}
	return slow
}
