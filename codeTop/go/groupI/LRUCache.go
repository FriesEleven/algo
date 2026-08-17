package groupI

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
