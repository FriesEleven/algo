import java.util.Collections;
import java.util.PriorityQueue;

/*
数据流的中位数

设计 MedianFinder：addNum(int num) 持续接收数据流中的整数，findMedian() 返回当前
所有元素的中位数。元素个数为奇数时返回中间元素；为偶数时返回中间两个元素的平均值。

算法实现说明：
1. `lower` 是大根堆，保存较小的那一半数字，堆顶就是这一半中的最大值。
2. `upper` 是小根堆，保存较大的那一半数字，堆顶就是这一半中的最小值。
3. 加入新数字时，先和 `lower` 的堆顶比较，决定放进哪一半；随后把较大堆的堆顶移到
   另一堆，保证两个堆的元素个数最多只差 1。
4. 数量为奇数时，中位数就是较大堆的堆顶；数量为偶数时，取两个堆顶的平均值。求和前
   转成 `long`，可以避免两个大整数相加时溢出。

addNum 时间复杂度 O(log n)，findMedian 时间复杂度 O(1)，空间复杂度 O(n)。
*/
class MedianFinder {
    private final PriorityQueue<Integer> lower =
            new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> upper = new PriorityQueue<>();

    public MedianFinder() {
    }

    public void addNum(int num) {
        if (lower.isEmpty() || num <= lower.peek()) {
            lower.offer(num);
        } else {
            upper.offer(num);
        }

        if (lower.size() > upper.size() + 1) {
            upper.offer(lower.poll());
        } else if (upper.size() > lower.size() + 1) {
            lower.offer(upper.poll());
        }
    }

    public double findMedian() {
        if (lower.size() == upper.size()) {
            return ((long) lower.peek() + upper.peek()) / 2.0;
        }
        return lower.size() > upper.size() ? lower.peek() : upper.peek();
    }
}
