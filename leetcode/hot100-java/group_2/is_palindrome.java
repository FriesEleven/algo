/*
 * 234. 回文链表
 *
 * 给定单链表的头节点 head，判断从头到尾读取的节点值序列是否为回文序列。
 * 使用快慢指针找到前半部分末尾，原地反转后半部分进行比较，并在返回前恢复
 * 链表原结构。
 *
 * 算法实现说明：
 * 1. slow 每次走一步、fast 每次走两步，fast 到达末尾时 slow 位于前半部分的末尾。
 * 2. 原地反转 slow.next 开始的后半链表，使两半能够从各自开头按相同方向逐个比较。
 * 3. left 从链表头、right 从反转后的后半头同步前进；任一节点值不同就不是回文。
 * 4. 比较结束后再次反转后半部分并接回 slow.next，在返回判断结果前恢复输入链表。
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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf = reverse(slow.next);
        ListNode left = head;
        ListNode right = secondHalf;
        boolean palindrome = true;
        while (right != null) {
            if (left.val != right.val) {
                palindrome = false;
                break;
            }
            left = left.next;
            right = right.next;
        }
        slow.next = reverse(secondHalf);
        return palindrome;
    }

    private ListNode reverse(ListNode head) {
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
