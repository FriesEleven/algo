/*
将有序数组转换为二叉搜索树

给定一个严格递增排列的整数数组 nums，将其转换为一棵高度平衡的二叉搜索树。
高度平衡是指每个节点的左右子树高度差不超过 1；存在多个答案时返回任意一个。

每次选择当前区间的中间元素作为根节点，并递归构造左右两半，天然保证平衡。

算法实现说明：
1. build 用闭区间 [left, right] 表示当前子树对应的数组范围；left > right 时
   区间为空，返回 null。
2. 选择 middle 位置创建根节点，小于它的左半区间递归生成 root.left，大于它的
   右半区间递归生成 root.right，所以二叉搜索树的大小关系始终成立。
3. 中点把每个区间分成长度最多相差 1 的两半，因此递归生成的左右子树高度最多
   相差 1；所有数组元素各使用一次，最终得到高度平衡的二叉搜索树。

时间复杂度：O(n)，n 为数组长度。
空间复杂度：O(log n)，对应平衡树的递归调用栈，不计返回的树。
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
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int middle = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = build(nums, left, middle - 1);
        root.right = build(nums, middle + 1, right);
        return root;
    }
}
