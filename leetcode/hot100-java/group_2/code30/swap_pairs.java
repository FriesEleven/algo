/*
两两交换链表中的节点

给定一个链表，两两交换其中相邻的节点，并返回交换后的头节点。只能调整节点
之间的链接关系，不能修改节点内部保存的值；末尾单独剩余的节点保持不变。

算法实现说明：
1. dummy.next 指向原头节点，previous 始终指向下一对待交换节点之前的节点。
2. 用 first、second 保存这一对节点，依次修改三条 next 引用，把局部顺序从
   previous -> first -> second 改成 previous -> second -> first。
3. 交换后 first 是这一对的尾部，把 previous 移到 first 再处理下一对；循环只在
   后面至少还有两个节点时执行，因此最后的单个节点会自然保留。

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
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode previous = dummy;

        while (previous.next != null && previous.next.next != null) {
            ListNode first = previous.next;
            ListNode second = first.next;
            first.next = second.next;
            second.next = first;
            previous.next = second;
            previous = first;
        }
        return dummy.next;
    }
}
