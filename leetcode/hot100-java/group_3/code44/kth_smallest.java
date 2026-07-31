import java.util.ArrayDeque;
import java.util.Deque;

/*
二叉搜索树中第 K 小的元素

给定一棵二叉搜索树的根节点 root 和正整数 k，返回树中按节点值从小到大排列
后的第 k 个值。题目保证 1 <= k <= 树的节点数。

二叉搜索树的中序遍历天然有序；使用显式栈并在访问到第 k 个节点时立即停止。

算法实现说明：
1. current 指向下一棵待处理子树，stack 保存尚未访问、但左子树正在或已经处理
   的祖先；先沿 left 不断入栈，找到当前最小的未访问节点。
2. 弹出栈顶就相当于中序访问该节点，将 k 减 1；然后令 current 指向其右子树，
   重复寻找下一个更大的节点。
3. 二叉搜索树的中序序列严格递增，所以 k 第一次减到 0 时弹出的值恰好是第 k 小，
   此时立即返回也避免遍历无关的剩余节点。

时间复杂度：O(h + k)，h 为树高，最坏为 O(n)。
空间复杂度：O(h)。
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
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            if (--k == 0) {
                return current.val;
            }
            current = current.right;
        }
        throw new IllegalArgumentException("k 超出节点数量");
    }
}
