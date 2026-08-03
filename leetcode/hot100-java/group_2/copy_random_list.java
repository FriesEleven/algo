/*
随机链表的复制

给定一个链表，每个节点除 next 指针外，还有一个可指向链表中任意节点或 null
的 random 指针。构造该链表的深拷贝：新链表与原链表的值和指针关系完全相同，
但任何新节点都不能引用原链表中的节点。

先把复制节点交织到原节点之后，从相邻关系得到 random 指针，再拆分两条链表。

算法实现说明：
1. 为每个原节点创建复制节点，并把它插到原节点后面，形成“原节点 -> 复制节点”
   交替排列的链表；这样无需哈希表也能立刻找到任意原节点的副本。
2. 若原节点的 random 指向 x，那么复制节点的 random 就应指向 x.next，因为
   x.next 正是 x 的复制节点。
3. 最后一轮分别恢复原节点的 next，并把各复制节点连在一起。原链表恢复原状，
   新链表只引用复制节点，因此得到关系完全相同的深拷贝。

时间复杂度：O(n)，n 为链表节点数。
空间复杂度：O(1)，不计必须创建的复制链表。
*/
class Node {
    int val;
    Node next;
    Node random;

    Node(int val) {
        this.val = val;
    }
}

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        for (Node current = head; current != null; current = current.next.next) {
            Node copy = new Node(current.val);
            copy.next = current.next;
            current.next = copy;
        }

        for (Node current = head; current != null; current = current.next.next) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
        }

        Node copyHead = head.next;
        for (Node current = head; current != null; ) {
            Node copy = current.next;
            current.next = copy.next;
            copy.next = copy.next == null ? null : copy.next.next;
            current = current.next;
        }
        return copyHead;
    }
}
