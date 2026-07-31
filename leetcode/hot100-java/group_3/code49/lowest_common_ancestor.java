/*
二叉树的最近公共祖先

给定一棵二叉树的根节点 root 以及树中的两个不同节点 p 和 q，返回它们的最近
公共祖先。最近公共祖先是深度最大的、同时为 p 和 q 祖先的节点；按照定义，
节点也可以是自己的祖先。题目保证 p、q 都存在于树中。

后序遍历每棵子树：若左右子树分别找到目标，当前节点就是答案；否则把找到的
目标或祖先继续向上返回。

算法实现说明：
1. 遇到 null 返回 null；遇到 p 或 q 立即返回该节点，因为目标节点本身可以是
   另一个目标的祖先。
2. 分别递归搜索 root.left 和 root.right，left、right 表示对应子树中找到的目标
   节点或已经确定的公共祖先。
3. 若 left、right 都非 null，说明 p、q 分居当前节点两侧，当前 root 就是它们
   第一次汇合且最深的公共祖先；若只有一侧非 null，则把该结果继续向上传递。
4. 题目保证两个目标都在树中，因此根调用最终一定返回正确的最近公共祖先。

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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
