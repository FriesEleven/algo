/*
翻转二叉树

给定一棵二叉树的根节点 root，交换树中每个节点的左、右子树，并返回翻转后
二叉树的根节点。操作在原树上完成。

算法实现说明：
1. root 为 null 时直接返回 null，空子树无需翻转。
2. 先用 oldLeft 保存原左子树，再递归翻转原右子树并赋给 root.left，递归翻转
   oldLeft 并赋给 root.right。
3. 递归返回时，每棵子树内部已经完成镜像，当前节点又交换了左右子树；由此从
   叶子到根的所有节点都被翻转，返回原 root 即为整棵树的镜像。

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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode oldLeft = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(oldLeft);
        return root;
    }
}
