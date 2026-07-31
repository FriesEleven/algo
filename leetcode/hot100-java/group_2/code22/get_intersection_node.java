/*
 * 160. 相交链表
 *
 * 给定两个单链表的头节点 headA 和 headB，返回两个链表相交的起始节点；若不
 * 相交则返回 null。这里的相交按节点引用相同判断，而不是节点值相同，并且返回
 * 后两个链表必须保持原有结构。两个指针分别走完 A+B 与 B+A 后会在交点对齐。
 *
 * 算法实现说明：
 * 1. pointerA、pointerB 分别从 headA、headB 出发，每次都向后移动一个节点。
 * 2. 某个指针走到 null 后，就切换到另一条链表的头部，使两指针最终都走过 A+B 长度。
 * 3. 两条路径的长度差因此被抵消；有交点时二者会在首个公共节点相遇，无交点时会同时成为 null。
 *
 * 时间复杂度：O(m + n)；空间复杂度：O(1)。
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pointerA = headA;
        ListNode pointerB = headB;
        while (pointerA != pointerB) {
            pointerA = pointerA == null ? headB : pointerA.next;
            pointerB = pointerB == null ? headA : pointerB.next;
        }
        return pointerA;
    }
}
