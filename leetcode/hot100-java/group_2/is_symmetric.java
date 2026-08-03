/*
对称二叉树

给定一个二叉树的根节点 root，判断该树是否关于其中心轴镜像对称。两个对应
位置的节点必须值相同，并且一侧的左子树要与另一侧的右子树互为镜像。

算法实现说明：
1. 空树天然对称；非空树从 root.left 与 root.right 这一对镜像位置开始比较。
2. isMirror 遇到某一侧为 null 时，只有两侧同时为 null 才返回 true；两节点都
   存在时还必须保证 val 相同。
3. 接着交叉比较 left.left 与 right.right、left.right 与 right.left。只有两组
   外侧和内侧子树都互为镜像时当前层才对称，因此递归结果能覆盖整棵树。

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
    public boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        return left.val == right.val
                && isMirror(left.left, right.right)
                && isMirror(left.right, right.left);
    }
}
