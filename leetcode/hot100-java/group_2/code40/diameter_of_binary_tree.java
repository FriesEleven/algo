/*
二叉树的直径

给定一棵二叉树的根节点 root，返回该树任意两个节点之间最长路径的边数。
这条路径可以经过根节点，也可以完全位于某棵子树中；空树的直径为 0。

后序遍历计算每个节点向下延伸的最大深度，并用左右子树深度之和更新直径。

算法实现说明：
1. diameter[0] 保存遍历至今发现的最大直径；depth(null) 返回 0，表示空子树没有
   可向下延伸的节点。
2. 对每个节点先递归得到 leftDepth、rightDepth。以当前节点为路径最高点时，
   最长路径的边数正好是这两个深度之和，用它更新 diameter[0]。
3. 当前节点向父节点只能贡献左、右方向中的一条路径，所以 depth 返回较大深度
   加 1。遍历会让每个节点都作为一次最高点，故全局最大值不会遗漏真正直径。

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
    public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[1];
        depth(root, diameter);
        return diameter[0];
    }

    private int depth(TreeNode node, int[] diameter) {
        if (node == null) {
            return 0;
        }
        int leftDepth = depth(node.left, diameter);
        int rightDepth = depth(node.right, diameter);
        diameter[0] = Math.max(diameter[0], leftDepth + rightDepth);
        return 1 + Math.max(leftDepth, rightDepth);
    }
}
