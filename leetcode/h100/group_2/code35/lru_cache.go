package code35

/*
LRU 缓存

设计并实现满足最近最少使用（LRU）约束的缓存：以正整数 capacity 初始化；
Get(key) 在键存在时返回值，否则返回 -1；Put(key, value) 更新或插入键值，
超过容量时删除最久未使用的键。Get 和 Put 均应达到 O(1) 平均时间复杂度。
*/
type LRUCache struct {
}

func Constructor(capacity int) (cache LRUCache) {
	return
}

func (l *LRUCache) Get(key int) (value int) {
	return
}

func (l *LRUCache) Put(key int, value int) {
}
