/*
合并两个有序链表

给定两个按非递减顺序排列的链表 list1 和 list2，将它们的全部节点合并为
一个新的非递减链表并返回。结果链表直接复用两个输入链表中的节点。

算法实现说明：
1. dummy 是结果链表前的哑节点，tail 始终指向已经合并部分的最后一个节点。
2. 同时查看 list1 和 list2 的当前节点，把值较小者接到 tail 后面，再移动对应
   链表指针；因此 tail 前面的节点始终有序且不会遗漏。
3. 某条链表耗尽后，另一条链表本身已经有序，可整体接到 tail 后，最后返回
   dummy.next 作为真正的头节点。

时间复杂度：O(m + n)，m、n 分别为两个链表的长度。
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        tail.next = list1 != null ? list1 : list2;
        return dummy.next;
    }
}
