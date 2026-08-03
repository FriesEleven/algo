import java.util.ArrayList;
import java.util.List;

/*
划分字母区间

给定只含小写英文字母的字符串 s，将其划分成尽可能多的片段，使同一个字母最多只出现
在一个片段中。划分后的片段按顺序拼接仍为 s，返回每个片段的长度。

算法实现说明：
1. 第一次扫描用 `last` 记录每个字母最后一次出现的下标。这样看到一个字母时，就知道包含
   它的片段至少要延伸到哪里。
2. 第二次扫描中，`start` 是当前片段起点，`end` 是片段必须覆盖的最右位置。
3. 每看到一个新字母，就用它的最后位置更新 `end`；如果途中又遇到最后位置更靠后的字母，
   当前片段也必须随之变长。
4. 当下标 `i` 正好走到 `end` 时，片段内所有字母以后都不会再出现，可以安全切分并记录
   长度。每次都在最早可切的位置切分，所以最终片段数量最多。

时间复杂度 O(n)，空间复杂度 O(1)（固定 26 个位置）。
*/
class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }
        return result;
    }
}
