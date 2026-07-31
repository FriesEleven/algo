/*
二叉树的最大深度

给定二叉树的根节点 root，返回该树的最大深度。最大深度是从根节点到最远
叶子节点的最长路径所包含的节点数；空树的深度为 0。

算法实现说明：
1. root 为 null 时没有节点，递归直接返回深度 0，这是计算的边界条件。
2. 分别递归调用 maxDepth 得到左子树深度和右子树深度。
3. 经过当前根节点的最深向下路径只能选择较深的一侧，所以返回两者最大值加 1；
   从叶子向根逐层汇总后，根节点得到的就是整棵树的最大深度。

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
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
