import java.util.HashMap;
import java.util.Map;

/*
从前序与中序遍历序列构造二叉树

给定两个整数数组 preorder 和 inorder，分别表示同一棵二叉树的前序遍历和
中序遍历，请构造并返回这棵二叉树。树中节点值互不相同，两个数组长度相等且
包含相同的节点值；空数组对应空树。

前序序列依次给出子树根节点，中序序列确定左右子树边界。用哈希表预存每个值
在中序数组中的位置，使每个节点只被处理一次。

算法实现说明：
1. inorderIndex 预先记录“节点值 -> 中序下标”，preorderIndex[0] 指向前序数组
   中下一个尚未建树的根节点。
2. build 接收当前子树在中序数组中的左右边界；先取 preorderIndex 指向的值创建
   root，再用哈希表找到它在中序序列中的位置 middle。
3. middle 左边只可能属于左子树，右边只可能属于右子树；按此前序顺序先递归构造
   root.left，再构造 root.right，空区间返回 null。
4. 节点值互不相同，因此每次划分位置唯一；前序确定根、中序确定两侧范围，递归
   构造出的树必然与给定的两种遍历序列一致。

时间复杂度：O(n)，n 为节点数。
空间复杂度：O(n)，用于索引表和最坏情况下的递归调用栈。
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndex.put(inorder[i], i);
        }
        int[] preorderIndex = {0};
        return build(preorder, 0, inorder.length - 1, preorderIndex, inorderIndex);
    }

    private TreeNode build(
            int[] preorder,
            int inorderLeft,
            int inorderRight,
            int[] preorderIndex,
            Map<Integer, Integer> inorderIndex) {
        if (inorderLeft > inorderRight) {
            return null;
        }

        int rootValue = preorder[preorderIndex[0]++];
        TreeNode root = new TreeNode(rootValue);
        int middle = inorderIndex.get(rootValue);
        root.left = build(preorder, inorderLeft, middle - 1, preorderIndex, inorderIndex);
        root.right = build(preorder, middle + 1, inorderRight, preorderIndex, inorderIndex);
        return root;
    }
}
