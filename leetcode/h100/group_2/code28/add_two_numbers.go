package code28

// ListNode 是单链表节点。
type ListNode struct {
	Val  int
	Next *ListNode
}

/*
两数相加

两个非空链表分别表示两个非负整数，数字按逆序存储，每个节点只存一位数字。
请将两数相加，并以相同形式返回一个表示和的链表。除数字 0 外，数字不会以 0 开头。
*/
func addTwoNumbers(l1, l2 *ListNode) (head *ListNode) {
	carry := 0
	var cur *ListNode
	for l1 != nil || l2 != nil {
		num1, num2 := 0, 0
		if l1 != nil {
			num1 = l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			num2 = l2.Val
			l2 = l2.Next
		}
		sum := num1 + num2 + carry
		val := sum % 10
		carry = sum / 10
		node := &ListNode{Val: val}
		if head == nil {
			head = node
			cur = node
		} else {
			cur.Next = node
			cur = cur.Next
		}
		if carry > 0 {
			cur.Next = &ListNode{Val: carry}
		}
	}
	return
}
