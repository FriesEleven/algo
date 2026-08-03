/*
 * 206. 反转链表
 *
 * 给定单链表的头节点 head，原地反转链表中所有 next 指针，并返回反转后的新
 * 头节点。空链表应返回 null。
 *
 * 算法实现说明：
 * 1. previous 指向已经反转好的链表头，current 指向下一个等待处理的节点。
 * 2. 每轮先用 following 保存 current.next，再把 current.next 改指向 previous，防止丢失后续链表。
 * 3. 随后同步前移 previous 和 current；current 变为 null 时，previous 就是完整反转后的新头节点。
 *
 * 时间复杂度：O(n)；空间复杂度：O(1)。
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
    public ListNode reverseList(ListNode head) {
        ListNode previous = null;
        ListNode current = head;
        while (current != null) {
            ListNode following = current.next;
            current.next = previous;
            previous = current;
            current = following;
        }
        return previous;
    }
}
