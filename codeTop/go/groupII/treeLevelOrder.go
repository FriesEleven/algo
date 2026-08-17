package groupII

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
