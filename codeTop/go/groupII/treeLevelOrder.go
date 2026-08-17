package groupII

/*
题目：102. 二叉树的层序遍历
链接：https://leetcode.cn/problems/binary-tree-level-order-traversal/

题目内容：
给定二叉树的根节点 root，按照从上到下、每层从左到右的顺序遍历节点，
返回按层分组的节点值列表。空树返回空列表。
*/

import "algo/structure"

type TreeNode = structure.TreeNode

func levelOrder(root *TreeNode) [][]int {
	ans := make([][]int, 0)
	if root == nil {
		return ans
	}
	queue := []*TreeNode{root}
	l:=0
	for l < len(queue) {
		size := len(queue)-l
		level := make([]int, 0, size)
		for i := 0; i < size; i++ {
			node := queue[l]
			l++
			level = append(level, node.Val)
			if node.Left != nil {
				queue=append(queue, node.Left)
			}
			if node.Right != nil {
				queue=append(queue, node.Right)
			}
		}
		ans = append(ans, level)
	}
	return ans
}
