import java.util.ArrayList;
import java.util.List;

/*
二叉树的中序遍历

给定二叉树的根节点 root，返回按“左子树、根节点、右子树”顺序访问得到的
节点值列表。空树应返回空列表。

使用 Morris 遍历临时建立前驱节点到当前节点的线索，访问后立即恢复原树，
从而无需递归栈或显式栈。

算法实现说明：
1. current 没有左子树时，可直接记录 current.val，再转向右子树，这符合中序顺序。
2. current 有左子树时，找到左子树最右侧的 predecessor。第一次到达时令
   predecessor.right 指回 current，再转向左子树，借这条线索实现递归返回。
3. 第二次找到同一个 predecessor 时，它的 right 已指向 current；先把该引用恢复
   为 null，再访问 current 并进入右子树。
4. 每个节点都按“左、根、右”被访问一次，所有临时线索也都会撤销，因此结果是
   正确的中序序列，并且原树结构不变。

时间复杂度：O(n)，n 为二叉树节点数。
空间复杂度：O(1)，不计返回结果。
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                values.add(current.val);
                current = current.right;
                continue;
            }

            TreeNode predecessor = current.left;
            while (predecessor.right != null && predecessor.right != current) {
                predecessor = predecessor.right;
            }
            if (predecessor.right == null) {
                predecessor.right = current;
                current = current.left;
            } else {
                predecessor.right = null;
                values.add(current.val);
                current = current.right;
            }
        }
        return values;
    }
}
