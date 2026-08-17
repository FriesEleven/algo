package groupI

/*
题目：206. 反转链表
链接：https://leetcode.cn/problems/reverse-linked-list/

题目内容：
给定单链表的头节点 head，反转链表中所有节点的连接方向，并返回反转后的新头节点。
空链表应返回 nil。
*/

import "algo/structure"

type ListNode = structure.ListNode

func reverseList(head *ListNode) *ListNode {
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
