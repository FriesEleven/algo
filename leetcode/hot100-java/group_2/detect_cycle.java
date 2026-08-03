/*
环形链表 II

给定一个链表的头节点 head，返回链表开始入环的第一个节点；如果链表无环，
返回 null。解题过程中不允许修改链表。

使用 Floyd 快慢指针：相遇后让一个指针回到头节点，两个指针同速前进，
再次相遇的位置就是环的入口。

算法实现说明：
1. slow 每次走一步，fast 每次走两步；若 fast 到达 null，说明链表没有环。
2. 两指针在环内相遇后，新建 seeker 并让它从 head 出发，slow 留在相遇点。
3. seeker 与 slow 改为每次都走一步。由快慢指针的路程关系可知，它们到环入口的
   剩余距离相同，所以第二次相遇的节点必定是入环节点。

时间复杂度：O(n)，n 为链表节点数。
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
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode seeker = head;
                while (seeker != slow) {
                    seeker = seeker.next;
                    slow = slow.next;
                }
                return seeker;
            }
        }
        return null;
    }
}
