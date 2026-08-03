/*
删除链表的倒数第 N 个节点

给定一个链表的头节点 head 和正整数 n，删除链表的倒数第 n 个节点，并返回
删除后的头节点。题目保证 n 合法且不超过链表长度。

用哑节点统一处理删除头节点的情况，并让快指针领先慢指针 n 个节点。

算法实现说明：
1. dummy 放在 head 前面，使删除头节点与删除中间节点都能写成修改前驱的 next。
2. fast 和 slow 都从 dummy 出发，先让 fast 前进 n 步，再让两者同步前进，直到
   fast 位于最后一个节点。
3. 此时 slow 恰好位于倒数第 n 个节点的前一个位置，令 slow.next 跳过目标节点
   即完成删除；返回 dummy.next 可正确覆盖头节点被删除的情况。

时间复杂度：O(L)，L 为链表长度，只需一次遍历。
空间复杂度：O(1)。
*/
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
