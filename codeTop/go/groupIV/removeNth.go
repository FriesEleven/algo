package groupiv

func removeNthFromEnd(head *ListNode, n int) (ans *ListNode) {
	slow,fast:=head,head
	for n>0 && fast!=nil{
		fast=fast.Next
		n--
	}
	if fast==nil {
		ans=head.Next
		head.Next=nil
		return
	}
	for fast.Next!=nil{
		slow=slow.Next
		fast=fast.Next
	}
	node:=slow.Next
	slow.Next=node.Next
	node.Next=nil
	return head
}