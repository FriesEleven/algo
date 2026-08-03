import java.util.ArrayList;
import java.util.List;

/*
二叉树的右视图

给定二叉树的根节点 root，想象从树的右侧观察它，返回从顶部到底部每一层最先
能看到的节点值。空树返回空列表。

按“根、右、左”的顺序深度优先遍历；第一次到达某个深度的节点就是该层右视图。

算法实现说明：
1. values.size() 也表示当前已经记录的层数；collect 的 depth 表示 node 所在层。
2. 遍历顺序固定为当前节点、右子树、左子树，因此到达一个新 depth 时，当前节点
   一定是这一层最靠右、最先被访问的节点，把它加入 values。
3. 同层后续节点不会满足 depth == values.size()，因此不会覆盖已经选出的右侧
   节点；遍历结束后每个非空层恰好留下一个可从右侧看到的值。

时间复杂度：O(n)，n 为二叉树节点数。
空间复杂度：O(h)，h 为树高，不计返回结果。
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        collect(root, 0, values);
        return values;
    }

    private void collect(TreeNode node, int depth, List<Integer> values) {
        if (node == null) {
            return;
        }
        if (depth == values.size()) {
            values.add(node.val);
        }
        collect(node.right, depth + 1, values);
        collect(node.left, depth + 1, values);
    }
}
