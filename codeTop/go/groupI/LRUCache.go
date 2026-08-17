package groupI

/*
题目：146. LRU 缓存
链接：https://leetcode.cn/problems/lru-cache/

题目内容：
设计并实现一个满足最近最少使用（LRU）策略的缓存。get(key) 在键存在时返回对应值，
否则返回 -1；put(key, value) 更新或插入键值，容量超限时淘汰最久未使用的键。
get 和 put 都应达到 O(1) 平均时间复杂度。
*/

import "container/list"

type DoubleNode struct {
	key int
	val int
}

type LRUCache struct {
	nodeMap map[int]*list.Element
	cache   *list.List
	cap     int
}

func Constructor(capacity int) LRUCache {
	return LRUCache{
		nodeMap: make(map[int]*list.Element),
		cache:   list.New(),
		cap:     capacity,
	}
}

func (c *LRUCache) Get(key int) int {
	elem, ok := c.nodeMap[key]
	if !ok {
		return -1
	}
	c.cache.MoveToBack(elem)
	return elem.Value.(*DoubleNode).val
}

func (c *LRUCache) Put(key, val int) {
	if c.cap <= 0 {
		return
	}
	if elem, ok := c.nodeMap[key]; ok {
		node := elem.Value.(*DoubleNode)
		node.val = val
		c.cache.MoveToBack(elem)
		return
	}
	if len(c.nodeMap) == c.cap {
		elem := c.cache.Front()
		node := elem.Value.(*DoubleNode)
		c.cache.Remove(elem)
		delete(c.nodeMap, node.key)
	}
	node:=&DoubleNode{
		key: key,
		val: val,
	}
	c.nodeMap[key]=c.cache.PushBack(node)
}
