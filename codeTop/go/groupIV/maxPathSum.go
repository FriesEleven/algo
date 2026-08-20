package groupiv

import (
	"algo/structure"
	"math"
)

type TreeNode = structure.TreeNode

func maxPathSum(root *TreeNode) int {
	ans := math.MinInt
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		lv := dfs(root.Left)
		rv := dfs(root.Right)
		ans = max(ans, lv+rv+root.Val)
		return max(0, max(lv, rv)+root.Val)
	}
	dfs(root)
	return ans
}
