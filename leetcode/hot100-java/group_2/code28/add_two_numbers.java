/*
两数相加

两个非空链表分别表示两个非负整数，每个节点保存一位数字，并按个位到高位的
逆序存储。将这两个整数相加，返回一个以相同形式表示结果的新链表。除数字 0
本身以外，输入数字不会以 0 开头。

算法实现说明：
1. dummy 和 tail 用来依次构造答案，carry 保存上一位相加产生的进位。
2. 每轮读取 l1、l2 当前位；某条链表已经结束时该位按 0 处理，再把两位与
   carry 相加。sum % 10 是当前结果位，sum / 10 是传给下一位的新进位。
3. 循环条件额外包含 carry != 0，保证最高位进位不会丢失。逐位执行的过程与
   手算加法完全相同，所以生成的逆序链表正好表示两数之和。

时间复杂度：O(max(m, n))，m、n 分别为两个链表的长度。
空间复杂度：O(1)，不计必须创建的结果链表。
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            carry = sum / 10;
        }
        return dummy.next;
    }
}
