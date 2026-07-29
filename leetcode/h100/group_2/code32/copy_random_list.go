package code32

// Node 是带随机指针的链表节点。
type Node struct {
	Val    int
	Next   *Node
	Random *Node
}

/*
随机链表的复制

给定一个链表，每个节点除 next 指针外还有一个可指向任意节点或 nil 的 random 指针。
请构造该链表的深拷贝，使新链表的节点和指针关系与原链表一致且不引用原节点。
*/
func copyRandomList(head *Node) *Node {
	if head == nil {
		return nil
	}
	for cur := head; cur != nil; {
		clone := &Node{
			Val:  cur.Val,
			Next: cur.Next,
		}
		cur.Next = clone
		cur = clone.Next
	}
	for cur := head; cur != nil; cur = cur.Next.Next {
		if cur.Random != nil {
			cur.Next.Random = cur.Random.Next
		}
	}
	cloneHead := head.Next
	for cur := head; cur != nil; {
		clone := cur.Next
		cur.Next = clone.Next
		if clone.Next != nil {
			clone.Next = clone.Next.Next
		}
		cur = cur.Next
	}
	return cloneHead
}
