import java.util.ArrayList;
import java.util.List;

/*
字符串解码

给定一个编码字符串 s，规则 k[encoded_string] 表示方括号内的字符串恰好重复 k 次。编码可以
嵌套，原始文本只含英文字母，输入保证格式有效且数字只表示重复次数。返回完整解码字符串。

算法实现说明：
1. parseSequence 用共享 index 从左到右解析当前括号层：连续字母组成 Literal，连续数字组成重复
   次数，遇到 '[' 后递归解析其内部，遇到对应的 ']' 就返回当前层节点。
2. 同一层的多个片段放入 Sequence 以保持原顺序；k[内容] 建成 Repeat 节点。k 等于 1 时直接
   复用内容节点，只有一个片段的层也直接返回该节点，减少无意义的结构层级。
3. 解析完成后，让根节点 appendTo 同一个 StringBuilder：Literal 追加文本，Sequence 顺序访问
   子节点，Repeat 按 count 次展开其内容。
4. 语法树保存了括号嵌套和重复关系，因此展开顺序与编码定义完全一致；字符只在最终展开时写入
   结果，避免多层解码过程中反复复制越来越大的中间字符串。

时间复杂度：O(n + L)，n 为编码串长度，L 为解码结果长度。
空间复杂度：O(n + L)，语法结构和递归栈占 O(n)，最终字符串占 O(L)。
*/
class Solution {
    private interface Node {
        void appendTo(StringBuilder output);
    }

    private static final class Literal implements Node {
        private final String text;

        private Literal(String text) {
            this.text = text;
        }

        @Override
        public void appendTo(StringBuilder output) {
            output.append(text);
        }
    }

    private static final class Sequence implements Node {
        private final List<Node> children;

        private Sequence(List<Node> children) {
            this.children = children;
        }

        @Override
        public void appendTo(StringBuilder output) {
            for (Node child : children) {
                child.appendTo(output);
            }
        }
    }

    private static final class Repeat implements Node {
        private final int count;
        private final Node content;

        private Repeat(int count, Node content) {
            this.count = count;
            this.content = content;
        }

        @Override
        public void appendTo(StringBuilder output) {
            for (int repetition = 0; repetition < count; repetition++) {
                content.appendTo(output);
            }
        }
    }

    public String decodeString(String s) {
        int[] index = {0};
        Node root = parseSequence(s, index);
        StringBuilder decoded = new StringBuilder();
        root.appendTo(decoded);
        return decoded.toString();
    }

    private Node parseSequence(String s, int[] index) {
        List<Node> nodes = new ArrayList<>();
        while (index[0] < s.length() && s.charAt(index[0]) != ']') {
            char current = s.charAt(index[0]);
            if (Character.isLetter(current)) {
                int start = index[0];
                while (index[0] < s.length() && Character.isLetter(s.charAt(index[0]))) {
                    index[0]++;
                }
                nodes.add(new Literal(s.substring(start, index[0])));
                continue;
            }

            int repeat = 0;
            while (index[0] < s.length() && Character.isDigit(s.charAt(index[0]))) {
                repeat = repeat * 10 + s.charAt(index[0]++) - '0';
            }
            index[0]++;
            Node content = parseSequence(s, index);
            index[0]++;
            if (repeat == 1) {
                nodes.add(content);
            } else {
                nodes.add(new Repeat(repeat, content));
            }
        }
        return nodes.size() == 1 ? nodes.get(0) : new Sequence(nodes);
    }
}
