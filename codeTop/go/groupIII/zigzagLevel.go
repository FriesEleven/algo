package groupIII

import "algo/structure"

type TreeNode = structure.TreeNode

func zigzagLevelOrder(root *TreeNode) [][]int {
	ans := make([][]int, 0)
	if root == nil {
		return ans
	}
	queue := []*TreeNode{root}
	l := 0
	reverse := false
	for l < len(queue) {
		r := len(queue)
		level := make([]int, 0, r-l)
		if !reverse {
			for i := l; i < r; i++ {
				level = append(level, queue[i].Val)
			}
		} else {
			for i := r - 1; i >= l; i-- {
				level = append(level, queue[i].Val)
			}
		}
		for l < r {
			node := queue[l]
			l++
			if node.Left != nil {
				queue = append(queue, node.Left)
			}
			if node.Right!=nil {
				queue = append(queue, node.Right)
			}
		}
		ans = append(ans, level)
		reverse=!reverse
	}
	return ans
}
