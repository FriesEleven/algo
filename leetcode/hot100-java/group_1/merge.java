/*
 * 56. 合并区间
 *
 * 给定区间数组 intervals，其中 intervals[i] = [start_i, end_i]。合并所有
 * 相互重叠的区间，返回一组互不重叠且恰好覆盖原有全部区间的新区间。
 *
 * 算法实现说明：
 * 1. 先按区间起点升序排序，使可能重叠的区间在数组中连续出现。
 * 2. start、end 保存当前正在合并的区间；若下一区间起点不超过 end，就扩大 end 覆盖它。
 * 3. 若下一区间与当前区间分离，就把当前区间加入结果，并从下一区间开始新的一段。
 * 4. 扫描结束后补入最后一段，得到的区间按序且互不重叠，并覆盖全部输入区间。
 *
 * 时间复杂度：O(n log n)，主要来自排序；除返回结果和排序栈外，空间复杂度：O(log n)。
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][];
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            } else {
                merged.add(new int[] {start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        merged.add(new int[] {start, end});
        return merged.toArray(new int[merged.size()][]);
    }
}
