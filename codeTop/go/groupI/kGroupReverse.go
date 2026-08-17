package groupI

/*
题目：25. K 个一组翻转链表
链接：https://leetcode.cn/problems/reverse-nodes-in-k-group/

题目内容：
给定链表头节点 head 和正整数 k，每 k 个节点为一组进行翻转并返回修改后的链表。
最后不足 k 个的节点保持原有顺序；只能改变节点之间的连接，不能只交换节点值。
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
