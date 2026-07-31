/*
最小栈

设计 MinStack，支持 push(val) 入栈、pop() 删除栈顶、top() 获取栈顶，以及 getMin() 获取栈中
最小元素，并要求所有操作均在常数时间内完成。题目保证 pop、top 和 getMin 只在非空栈上调用。

算法实现说明：
1. 用单链表表示栈，top 指向栈顶 Node；每个节点保存 value、指向旧栈顶的 next，以及从当前
   节点到栈底的 minimum。
2. push 时，新节点的 minimum 取 val 与旧 top.minimum 的较小值；若原栈为空，最小值就是 val，
   然后让 top 指向新节点。
3. pop 只需把 top 改为 top.next，top() 直接读取 top.value，三者都不需要遍历链表。
4. 因为 top.minimum 在入栈时已经汇总了它下面全部元素的最小值，getMin 直接返回该字段就是
   当前整栈最小值，且弹栈后旧节点保存的最小值会自动恢复。

时间复杂度：push、pop、top 和 getMin 均为 O(1)。
空间复杂度：O(n)，n 为当前栈内元素数量。
*/
class MinStack {
    private static final class Node {
        private final int value;
        private final int minimum;
        private final Node next;

        private Node(int value, int minimum, Node next) {
            this.value = value;
            this.minimum = minimum;
            this.next = next;
        }
    }

    private Node top;

    public MinStack() {
    }

    public void push(int val) {
        int minimum = top == null ? val : Math.min(val, top.minimum);
        top = new Node(val, minimum, top);
    }

    public void pop() {
        top = top.next;
    }

    public int top() {
        return top.value;
    }

    public int getMin() {
        return top.minimum;
    }
}
