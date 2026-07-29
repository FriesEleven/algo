package code33

// ListNode 是单链表节点。
type ListNode struct {
	Val  int
	Next *ListNode
}

/*
排序链表

给定链表的头节点 head，请按升序排列并返回排序后的链表。
进阶要求为 O(n log n) 时间复杂度和常数级额外空间。
*/
func sortList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	slow, fast := head, head
	if fast.Next != nil && fast.Next.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
	}
	right := slow.Next
	slow.Next = nil
	left := sortList(head)
	right = sortList(right)
	return merge(left, right)
}

func mergeSortedList(l1, l2 *ListNode) *ListNode {
	if l1 == nil {
		return l2
	}
	if l2 == nil {
		return l1
	}
	if l1.Val <= l2.Val {
		l1.Next = mergeSortedList(l1.Next, l2)
		return l1
	} else {
		l2.Next = mergeSortedList(l1, l2.Next)
		return l2
	}
}

func merge(l1, l2 *ListNode) *ListNode {
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
	} else {
		tail.Next = l2
	}
	return dummy.Next
}
