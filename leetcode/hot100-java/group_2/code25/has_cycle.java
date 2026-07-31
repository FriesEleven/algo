/*
 * 141. 环形链表
 *
 * 给定单链表的头节点 head，判断链表中是否存在环。如果沿 next 指针能够再次
 * 到达某个已访问节点，则链表有环。快指针每次走两步、慢指针每次走一步；有环
 * 时二者必定相遇，无环时快指针会到达链表末尾。
 *
 * 算法实现说明：
 * 1. slow 和 fast 都从头节点出发，循环中 slow 走一步、fast 走两步。
 * 2. 若链表无环，fast 或 fast.next 会先变为 null，循环结束并返回 false。
 * 3. 若链表有环，fast 会在环内逐渐追上 slow；两者引用相同节点时即可确定存在环并返回 true。
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
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
