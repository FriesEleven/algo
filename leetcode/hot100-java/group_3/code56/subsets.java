import java.util.ArrayList;
import java.util.List;

/*
子集

给定一个元素互不相同的整数数组 nums，返回该数组所有可能的子集（幂集）。结果中不能包含
重复子集，子集和答案的顺序均不受限制。

算法实现说明：
1. path 保存当前已经选择的元素，start 表示下一次只能从 nums[start] 及其后面选择，result
   收集所有子集。
2. 每进入一个递归节点，先复制 path 加入 result；这样空集和每个中间状态本身都会成为答案，
   不需要等到固定深度。
3. 从 start 起依次选择 nums[index] 加入 path，再以 index + 1 递归，保证同一元素不会重复选择，
   元素下标也始终按递增顺序出现。
4. 递归返回后删除 path 末尾元素以尝试下一种选择。每个子集对应唯一的一组递增下标，因此不会
   重复，也不会遗漏。

时间复杂度：O(n * 2^n)，复制全部子集中的元素需要该数量级的时间。
空间复杂度：O(n) 辅助栈和路径空间；若计入返回结果，则为 O(n * 2^n)。
*/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        collect(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void collect(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        for (int index = start; index < nums.length; index++) {
            path.add(nums[index]);
            collect(nums, index + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}
