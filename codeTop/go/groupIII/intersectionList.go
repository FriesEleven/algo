package groupIII

import "math"

func getIntersectionNode(headA, headB *ListNode) *ListNode {
	len1, len2 := 0, 0
	p1, p2 := headA, headB
	for p1 != nil {
		len1++
		p1 = p1.Next
	}
	for p2 != nil {
		len2++
		p2 = p2.Next
	}
	p1, p2 = headA, headB
	if len1 < len2 {
		p1, p2 = headB, headA
	}
	diff := math.Abs(float64(len1) - float64(len2))
	for diff > 0 {
		p1 = p1.Next
		diff--
	}
	for p1 != p2 {
		p1 = p1.Next
		p2 = p2.Next
	}
	return p1
}
