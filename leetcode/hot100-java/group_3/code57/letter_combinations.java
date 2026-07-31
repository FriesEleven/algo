import java.util.ArrayList;
import java.util.List;

/*
电话号码的字母组合

给定一个仅包含数字 2 到 9 的字符串 digits，按照电话按键上数字到字母的映射，返回它能够
表示的所有字母组合，答案可以按任意顺序返回；数字 1 不对应任何字母，空输入返回空列表。

算法实现说明：
1. LETTERS 数组以下标 2 到 9 保存电话按键字母；若 digits 为空，直接返回空结果列表。
2. 递归参数 index 表示当前处理第几个数字，path 用 StringBuilder 保存已经选择的字母，避免
   每层都创建新的中间字符串。
3. 取出当前数字对应的 letters，逐个追加其中一个字母并递归处理下一个数字；返回后删除末尾
   字符，继续尝试当前按键的其他字母。
4. 当 index 到达 digits 末尾时，path 中恰好包含每个数字的一次选择，将其转成字符串加入结果；
   所有按键选择的笛卡尔积都会被完整且不重复地枚举。

时间复杂度：O(L * 4^L)，L 为 digits 的长度；这是生成并复制所有结果的最坏上界。
空间复杂度：O(L) 辅助递归和路径空间；若计入输出，最坏为 O(L * 4^L)。
*/
class Solution {
    private static final String[] LETTERS = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        build(digits, 0, new StringBuilder(digits.length()), result);
        return result;
    }

    private void build(String digits, int index, StringBuilder path, List<String> result) {
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }

        String letters = LETTERS[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            build(digits, index + 1, path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
