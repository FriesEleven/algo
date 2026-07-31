/*
排序链表

给定链表的头节点 head，将链表中的节点按值的非递减顺序排列并返回。要求达到
O(n log n) 时间复杂度，并尽量只使用常数级额外空间。

使用自底向上的迭代归并排序，依次合并长度为 1、2、4……的有序段；所有合并
都复用原节点，因此没有递归栈或辅助数组。

算法实现说明：
1. 先统计链表长度；size 表示本轮有序段长度，从 1 开始每轮翻倍，dummy 用来
   稳定保存整条结果链表的入口。
2. split 把 current 后的节点切成长度至多为 size 的 left、right 两段，并返回
   下一组的起点；merge 再按节点值把这两段原地合并。
3. previous 把每次合并后的链表接回整体并移动到该段尾部，随后继续处理下一组。
4. 每轮结束后，有序段长度至少翻倍；当 size 覆盖整条链表时所有节点已经有序，
   且整个过程只调整 next 引用，所以额外空间保持常数级。

时间复杂度：O(n log n)，n 为链表节点数。
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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        int length = 0;
        for (ListNode node = head; node != null; node = node.next) {
            length++;
        }

        ListNode dummy = new ListNode(0, head);
        for (int size = 1; size < length; size <<= 1) {
            ListNode previous = dummy;
            ListNode current = dummy.next;
            while (current != null) {
                ListNode left = current;
                ListNode right = split(left, size);
                current = split(right, size);
                previous.next = merge(left, right);
                while (previous.next != null) {
                    previous = previous.next;
                }
            }
        }
        return dummy.next;
    }

    private ListNode split(ListNode head, int size) {
        if (head == null) {
            return null;
        }
        for (int i = 1; i < size && head.next != null; i++) {
            head = head.next;
        }
        ListNode next = head.next;
        head.next = null;
        return next;
    }

    private ListNode merge(ListNode first, ListNode second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }

        ListNode head;
        if (first.val <= second.val) {
            head = first;
            first = first.next;
        } else {
            head = second;
            second = second.next;
        }
        ListNode tail = head;
        while (first != null && second != null) {
            if (first.val <= second.val) {
                tail.next = first;
                first = first.next;
            } else {
                tail.next = second;
                second = second.next;
            }
            tail = tail.next;
        }
        tail.next = first != null ? first : second;
        return head;
    }
}
