import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
组合总和

给定一个不含重复元素的正整数数组 candidates 和正整数 target，找出所有元素之和等于 target
的不同组合。每个候选数字可以不限次数重复选取，答案中组合的顺序不作要求。

算法实现说明：
1. 先将 candidates 升序排列；remaining 表示当前组合还差多少，start 限制本层可以选择的最小
   候选下标，path 保存正在构造的组合。
2. 从 start 开始枚举 candidate。若 candidate 已大于 remaining，后面的数只会更大，可以直接
   结束本层循环，这是排序带来的剪枝。
3. 选择一个数后把它加入 path，并用 remaining - candidate 继续搜索；递归仍传入当前 index，
   因而同一个候选数字可以不限次数重复使用。
4. remaining 变为零时复制 path 得到一个答案；回溯时删除末尾元素。下标始终非递减，所以同一
   组数字只会按一种顺序生成，不会出现排列造成的重复组合。

时间复杂度：排序为 O(n log n)；搜索的最坏上界为 O(n^(target / m))，其中 m 是最小候选值，
实际开销还与有效组合的输出总长度有关。
空间复杂度：O(target / m) 辅助递归和路径空间；返回结果所占空间另计。
*/
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        search(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void search(
            int[] candidates,
            int remaining,
            int start,
            List<Integer> path,
            List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int index = start; index < candidates.length; index++) {
            int candidate = candidates[index];
            if (candidate > remaining) {
                break;
            }
            path.add(candidate);
            search(candidates, remaining - candidate, index, path, result);
            path.remove(path.size() - 1);
        }
    }
}
