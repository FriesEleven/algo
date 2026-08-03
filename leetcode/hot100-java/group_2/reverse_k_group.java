/*
K 个一组翻转链表

给定链表头节点 head 和正整数 k，每 k 个连续节点为一组进行原地翻转，返回
修改后的链表。最后不足 k 个节点的一组保持原顺序，并且不能仅交换节点值。

算法实现说明：
1. groupPrevious 指向当前分组的前驱；先从它向后寻找第 k 个节点 kth，找不到就
   说明剩余节点不足 k 个，直接结束而不改变这一段。
2. 保存 kth 后面的 groupNext，以它作为翻转时 previous 的初值，逐个反转本组
   节点的 next，直到 current 到达 groupNext。
3. 把 groupPrevious.next 接到新的组头 kth，再把 groupPrevious 移到原组头
   oldGroupHead。每轮完整翻转并正确接回一组，最终得到题目要求的链表。

时间复杂度：O(n)，每个节点只参与常数次指针操作。
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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode groupPrevious = dummy;

        while (true) {
            ListNode kth = groupPrevious;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) {
                break;
            }

            ListNode groupNext = kth.next;
            ListNode current = groupPrevious.next;
            ListNode previous = groupNext;
            while (current != groupNext) {
                ListNode next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }

            ListNode oldGroupHead = groupPrevious.next;
            groupPrevious.next = kth;
            groupPrevious = oldGroupHead;
        }
        return dummy.next;
    }
}
