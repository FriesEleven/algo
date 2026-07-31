/*
有效的括号

给定一个只包含圆括号、方括号和花括号的字符串 s，判断它是否有效。每个左括号必须由相同类型
的右括号闭合，括号必须按正确顺序闭合，并且每个右括号都有对应的左括号。

算法实现说明：
1. 括号必须成对出现，所以字符串长度为奇数时可立即返回 false；随后用 char[] 和 size 模拟栈，
   避免包装字符对象。
2. 读到左括号时，不压入左括号本身，而是压入它期待的右括号，例如 '(' 压入 ')'，这样匹配时
   只需一次字符比较。
3. 读到右括号时，栈不能为空，并且弹出的栈顶必须与当前字符相同；否则它没有左括号或闭合
   顺序错误，立即返回 false。
4. 扫描结束后 size 为零，说明每个左括号都按后进先出的正确顺序闭合；若栈中有剩余期待字符，
   则仍有左括号未闭合。

时间复杂度：O(n)。
空间复杂度：O(n)。
*/
class Solution {
    public boolean isValid(String s) {
        if ((s.length() & 1) == 1) {
            return false;
        }

        char[] stack = new char[s.length()];
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            char bracket = s.charAt(i);
            if (bracket == '(') {
                stack[size++] = ')';
            } else if (bracket == '[') {
                stack[size++] = ']';
            } else if (bracket == '{') {
                stack[size++] = '}';
            } else if (size == 0 || stack[--size] != bracket) {
                return false;
            }
        }
        return size == 0;
    }
}
