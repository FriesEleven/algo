package groupI

/*
题目：21. 合并两个有序链表
链接：https://leetcode.cn/problems/merge-two-sorted-lists/

题目内容：
给定两个按非递减顺序排列的链表 list1 和 list2，将它们合并为一条新的非递减链表，
并返回合并后链表的头节点。结果链表由两个输入链表的节点连接而成。
*/

func mergeTwoLists(list1, list2 *ListNode) *ListNode {
	if list1==nil {
		return list2
	} 
	if list2==nil {
		return list1
	}
	if list1.Val <= list2.Val {
		list1.Next=mergeTwoLists(list1.Next,list2)
		return list1
	} else {
		list2.Next=mergeTwoLists(list1,list2.Next)
		return list2
	}
}
