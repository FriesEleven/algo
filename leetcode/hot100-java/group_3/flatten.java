/*
二叉树展开为链表

给定二叉树的根节点 root，将它原地展开为只使用 right 指针的单链表。展开后，
节点顺序必须与原树的前序遍历一致，并且每个节点的 left 指针都必须为 null。

若当前节点有左子树，就把原右子树接到左子树最右节点之后，再把整棵左子树
移到右侧。沿最终的 right 链继续处理即可，不需要栈或额外节点。

算法实现说明：
1. current 沿已经形成的 right 链向后移动；没有左子树时无需改线，直接处理
   下一个节点。
2. 有左子树时，predecessor 找到这棵左子树最右侧的节点，它正是左子树前序序列
   的最后一个节点，因此应把原 current.right 接到 predecessor.right。
3. 再把 current.left 整体移到 current.right，并将 current.left 置为 null。
4. 这样局部顺序变成“当前节点、原左子树、原右子树”，正好是前序遍历；不断沿
   right 重复后，所有节点都按前序相连且所有 left 都被清空。

时间复杂度：O(n)，n 为二叉树节点数。
空间复杂度：O(1)。
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
    public void flatten(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            if (current.left != null) {
                TreeNode predecessor = current.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }
}
