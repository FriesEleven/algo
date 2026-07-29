package code31

// ListNode 是单链表节点。
type ListNode struct {
	Val  int
	Next *ListNode
}

/*
K 个一组翻转链表

给定链表的头节点 head，每 k 个节点一组进行翻转，并返回修改后的链表。
不足 k 个的最后一组保持原有顺序；不能只改变节点值。
*/
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
