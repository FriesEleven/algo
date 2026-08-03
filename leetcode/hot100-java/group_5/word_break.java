import java.util.List;

/*
单词拆分

给定字符串 s 和字符串列表 wordDict，判断能否用字典中的一个或多个单词按顺序拼接出 s。
字典中的单词可以重复使用，且不要求使用所有单词；输入只含小写英文字母。

算法实现说明：
1. 先把字典中的所有单词放进 Trie。沿字符逐层走 Trie，就能同时判断一个前缀是否仍可能
   是字典单词，以及何时已经匹配到一个完整单词。
2. `reachable[i]` 表示字符串前 `i` 个字符能否被成功拆分，空前缀天然可拆，所以先设置
   `reachable[0] = true`。
3. 只从可达位置 `start` 开始向后匹配字符。Trie 中没有对应子节点时立即停止，因为继续增加
   字符也不可能组成字典单词。
4. 每当走到 Trie 的单词结尾，就把 `end + 1` 标记为新的可达位置；之后又可以从这里开始
   匹配下一个单词，同一个字典单词也因此能够重复使用。
5. 字符串末尾最终可达就返回 `true`。设最长单词长度为 L、字典总字符数为 D，时间复杂度
   O(D + nL)，
空间复杂度 O(n + D)。
*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        TrieNode root = new TrieNode();
        for (String word : wordDict) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isWord = true;
        }

        boolean[] reachable = new boolean[s.length() + 1];
        reachable[0] = true;
        for (int start = 0; start < s.length(); start++) {
            if (!reachable[start]) {
                continue;
            }
            TrieNode node = root;
            for (int end = start; end < s.length(); end++) {
                node = node.children[s.charAt(end) - 'a'];
                if (node == null) {
                    break;
                }
                if (node.isWord) {
                    reachable[end + 1] = true;
                }
            }
            if (reachable[s.length()]) {
                return true;
            }
        }
        return reachable[s.length()];
    }

    private static class TrieNode {
        private final TrieNode[] children = new TrieNode[26];
        private boolean isWord;
    }
}
