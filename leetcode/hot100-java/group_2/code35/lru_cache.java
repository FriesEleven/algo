import java.util.HashMap;
import java.util.Map;

/*
LRU 缓存

设计并实现满足最近最少使用（LRU）策略的缓存。用正整数 capacity 初始化；
get(key) 在键存在时返回对应值并把它标记为最近使用，否则返回 -1；
put(key, value) 更新已有键或插入新键，并把它标记为最近使用。当缓存超过容量时，
删除最久未使用的键。get 和 put 都必须达到 O(1) 平均时间复杂度。

哈希表负责 O(1) 定位节点，带头尾哑节点的双向链表按使用新旧维护节点顺序。

算法实现说明：
1. cache 把 key 映射到双向链表节点；head 后面表示最近使用，tail 前面表示最久
   未使用，两个哑节点让插入和删除都不必单独判断首尾边界。
2. get 先从 cache 定位节点：不存在就返回 -1，存在则调用 moveToFront 把它移到
   head 后面，再返回 value，表示本次访问已刷新使用时间。
3. put 遇到已有 key 时更新 value 并移到最前；遇到新 key 时创建节点，同时写入
   哈希表和链表，保证两个结构始终保存同一批缓存项。
4. 插入后若数量超过 capacity，就删除 tail.previous，并从 cache 移除它；该节点
   正是最久未使用项，所以每次淘汰都严格符合 LRU 规则。

时间复杂度：构造为 O(1)，每次 get、put 的平均时间复杂度均为 O(1)。
空间复杂度：O(capacity)。
*/
class LRUCache {
    private static class Node {
        int key;
        int value;
        Node previous;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache = new HashMap<>();
    private final Node head = new Node(0, 0);
    private final Node tail = new Node(0, 0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.previous = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToFront(node);
            return;
        }

        Node inserted = new Node(key, value);
        cache.put(key, inserted);
        addFirst(inserted);
        if (cache.size() > capacity) {
            Node leastRecentlyUsed = tail.previous;
            remove(leastRecentlyUsed);
            cache.remove(leastRecentlyUsed.key);
        }
    }

    private void moveToFront(Node node) {
        remove(node);
        addFirst(node);
    }

    private void addFirst(Node node) {
        node.previous = head;
        node.next = head.next;
        head.next.previous = node;
        head.next = node;
    }

    private void remove(Node node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }
}
