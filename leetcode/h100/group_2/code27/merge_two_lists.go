package code27

// ListNode 是单链表节点。
type ListNode struct {
	Val  int
	Next *ListNode
}

/*
合并两个有序链表

将两个升序链表 list1 和 list2 合并为一个新的升序链表并返回。
新链表由给定的两个链表的全部节点拼接而成。
*/
func mergeTwoLists(list1, list2 *ListNode) (head *ListNode) {
	if list1==nil {
		return list2
	}
	if list2==nil {
		return list1
	}
	if list1.Val<=list2.Val {
		list1.Next=mergeTwoLists(list1.Next,list2)
		return list1
	} else {
		list2.Next=mergeTwoLists(list1,list2.Next)
		return list2
	}
}
