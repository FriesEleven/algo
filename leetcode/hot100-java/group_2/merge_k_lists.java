/*
合并 K 个升序链表

给定链表数组 lists，其中每条链表都按非递减顺序排列。将所有链表中的节点
合并为一条非递减链表并返回；数组为空时返回 null。

采用两两归并：第 i 轮把间隔为 2^i 的链表合并，避免逐条合并产生的平方级
代价，并原地复用输入链表的节点。

算法实现说明：
1. interval 表示本轮两个待合并结果在 lists 中的间隔，初始为 1；把 lists[i]
   与 lists[i + interval] 合并后仍保存到 lists[i]。
2. merge 同时查看两条链表的当前节点，每次接入较小者，最后接上未耗尽的部分，
   因而合并结果有序且包含两条链表的全部节点。
3. 每轮把 interval 翻倍，相当于已合并的链表数量从 1、2、4……不断扩大；最终
   lists[0] 包含全部 k 条链表，且各层总共只遍历 N 个节点。

时间复杂度：O(N log k)，N 为全部节点数，k 为链表条数。
空间复杂度：O(1)，不计输入数组和输出链表本身。
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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        for (int interval = 1; interval < lists.length; interval <<= 1) {
            for (int i = 0; i + interval < lists.length; i += interval << 1) {
                lists[i] = merge(lists[i], lists[i + interval]);
            }
        }
        return lists[0];
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
