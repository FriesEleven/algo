/*
验证二叉搜索树

给定二叉树的根节点 root，判断它是否为有效的二叉搜索树。任意节点左子树中的
所有值都必须严格小于该节点值，右子树中的所有值都必须严格大于该节点值，
并且左右子树本身也必须满足相同性质；重复值不合法。

递归传递每棵子树允许的开区间，并使用 long 边界覆盖所有 int 节点值。

算法实现说明：
1. validate(node, lower, upper) 表示 node 及其整棵子树的值都必须落在开区间
   (lower, upper) 内；根节点从 long 的最小值与最大值开始，覆盖所有 int 值。
2. 当前值触碰或越过任一边界就立即返回 false，严格不等号也会正确拒绝重复值。
3. 检查左子树时把 upper 收紧为 node.val，检查右子树时把 lower 收紧为 node.val；
   边界携带了所有祖先的限制，所以两边都通过时整棵子树才是有效搜索树。

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
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return validate(node.left, lower, node.val)
                && validate(node.right, node.val, upper);
    }
}
