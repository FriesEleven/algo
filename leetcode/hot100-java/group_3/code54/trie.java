/*
实现 Trie（前缀树）

实现 Trie 类：insert(word) 向前缀树中插入字符串；search(word) 判断字符串是否已经完整插入；
startsWith(prefix) 判断前缀树中是否存在以 prefix 开头的字符串。输入单词和前缀仅由小写
英文字母组成。

算法实现说明：
1. 每个 Node 用 children[26] 保存下一位小写字母对应的节点，用 isWord 区分“某个单词在此
   结束”和“这里只是其他单词的前缀”；root 是不代表具体字符的起点。
2. insert 从 root 出发逐字符计算下标，缺少子节点时就创建，走完整个 word 后把末节点的
   isWord 设为 true。
3. findNode 封装公共查找过程：沿字符串的唯一路径前进，只要某个子节点不存在就返回 null，
   否则返回最后到达的节点。
4. search 除了要求路径存在，还要求末节点 isWord 为 true；startsWith 只要求路径存在，因此
   能正确区分完整单词和普通前缀。

时间复杂度：insert、search 和 startsWith 均为 O(L)，L 为本次字符串长度。
空间复杂度：单次操作的额外空间为 O(1)；整棵树最多占 O(S) 空间，S 为所有插入字符串的
字符总数。
*/
class Trie {
    private static final class Node {
        private final Node[] children = new Node[26];
        private boolean isWord;
    }

    private final Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Node();
            }
            node = node.children[index];
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        Node node = findNode(word);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    private Node findNode(String text) {
        Node node = root;
        for (int i = 0; i < text.length(); i++) {
            node = node.children[text.charAt(i) - 'a'];
            if (node == null) {
                return null;
            }
        }
        return node;
    }
}
