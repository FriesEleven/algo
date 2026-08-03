/*
二叉树中的最大路径和

二叉树中的路径由通过父子边相连的一系列节点组成，同一节点在一条路径中最多
出现一次，路径可以从任意节点开始和结束且不必经过根节点。给定非空二叉树的
根节点 root，返回任意一条非空路径上的最大节点值之和；节点值可以为负数。

后序遍历计算每个节点能向父节点贡献的最大单边路径和，同时用“左贡献 + 当前值
+ 右贡献”更新完整路径答案；负贡献按 0 舍弃。

算法实现说明：
1. best[0] 保存全局最大路径和，并初始化为最小整数，保证整棵树节点都为负数时
   仍会选择某个非空路径。
2. maximumGain 先递归求左右子树能向当前节点提供的单边收益；收益为负会降低
   路径总和，所以用 Math.max(0, gain) 将其舍弃。
3. 以当前节点为最高点的完整路径可以同时使用左右收益，用 node.val + leftGain
   + rightGain 更新 best[0]，从而让每个节点都有机会成为答案路径的最高点。
4. 返回父节点时路径不能分叉，只能选择左右收益较大的一侧加上 node.val；这一
   返回定义与全局更新配合，枚举了树中所有可能的非空路径。

时间复杂度：O(n)，n 为二叉树节点数。
空间复杂度：O(h)，h 为树高，对应递归调用栈。
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
    public int maxPathSum(TreeNode root) {
        int[] best = {Integer.MIN_VALUE};
        maximumGain(root, best);
        return best[0];
    }

    private int maximumGain(TreeNode node, int[] best) {
        if (node == null) {
            return 0;
        }
        int leftGain = Math.max(0, maximumGain(node.left, best));
        int rightGain = Math.max(0, maximumGain(node.right, best));
        best[0] = Math.max(best[0], node.val + leftGain + rightGain);
        return node.val + Math.max(leftGain, rightGain);
    }
}
