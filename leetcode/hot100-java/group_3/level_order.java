import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
二叉树的层序遍历

给定二叉树的根节点 root，返回其节点值的层序遍历结果：从根节点所在层开始，
逐层从左到右访问。每一层的节点值单独组成一个列表；空树返回空列表。

算法实现说明：
1. root 非空时先进入 queue；队列始终按从左到右的顺序保存已经发现、尚未处理
   的节点，levels 用来收集各层结果。
2. 每轮先记录 levelSize = queue.size()，它恰好是当前层的节点数；只弹出这
   levelSize 个节点并按顺序记录值，同时把它们的左右孩子加入队尾。
3. 新加入的孩子全部属于下一层，不会混入本轮；因此每轮生成一个完整且从左到右
   的层列表，队列清空时所有层也正好处理完毕。

时间复杂度：O(n)，n 为二叉树节点数。
空间复杂度：O(w)，w 为二叉树的最大层宽，不计返回结果。
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) {
            return levels;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            levels.add(level);
        }
        return levels;
    }
}
