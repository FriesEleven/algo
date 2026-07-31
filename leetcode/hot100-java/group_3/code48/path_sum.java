import java.util.HashMap;
import java.util.Map;

/*
路径总和 III

给定二叉树的根节点 root 和整数 targetSum，统计节点值之和等于 targetSum 的
向下路径数量。路径可以从任意节点开始、在任意节点结束，但必须沿父节点到
子节点的方向连续移动，且至少包含一个节点。

深度优先遍历时记录当前根路径上的前缀和出现次数。若当前前缀和与某个旧前缀
和之差为 targetSum，则两者之间形成一条合法路径；回溯时撤销当前前缀和。

算法实现说明：
1. prefixCounts 只统计当前“根到正在访问节点”路径上的前缀和；先放入 0 -> 1，
   这样从根开始且总和等于 targetSum 的路径也能被计数。
2. 到达 node 时计算 currentSum。每个值为 currentSum - targetSum 的旧前缀和，
   都对应一条从旧位置之后到 node、且和恰为 targetSum 的向下路径。
3. 把 currentSum 加入表后递归左右子树，使后代可以使用它；两边处理完再把次数
   减回去或移除，避免另一条分支错误使用不在其祖先路径上的前缀和。
4. 每条合法路径都由唯一的一对前缀和确定，因此上述计数既不会遗漏也不会重复。

时间复杂度：O(n)，n 为二叉树节点数。
空间复杂度：O(h)，h 为树高，包含前缀和表与递归调用栈。
*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixCounts = new HashMap<>();
        prefixCounts.put(0L, 1);
        return countPaths(root, 0L, targetSum, prefixCounts);
    }

    private int countPaths(
            TreeNode node,
            long prefixSum,
            int targetSum,
            Map<Long, Integer> prefixCounts) {
        if (node == null) {
            return 0;
        }

        long currentSum = prefixSum + node.val;
        int count = prefixCounts.getOrDefault(currentSum - targetSum, 0);
        prefixCounts.put(currentSum, prefixCounts.getOrDefault(currentSum, 0) + 1);

        count += countPaths(node.left, currentSum, targetSum, prefixCounts);
        count += countPaths(node.right, currentSum, targetSum, prefixCounts);

        int remaining = prefixCounts.get(currentSum) - 1;
        if (remaining == 0) {
            prefixCounts.remove(currentSum);
        } else {
            prefixCounts.put(currentSum, remaining);
        }
        return count;
    }
}
