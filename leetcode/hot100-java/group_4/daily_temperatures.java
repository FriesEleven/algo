/*
每日温度

给定整数数组 temperatures 表示每天的温度，返回数组 answer，其中 answer[i] 是第 i 天之后首次
出现更高温度还需等待的天数；如果之后不会出现更高温度，则 answer[i] 为 0。

算法实现说明：
1. 用 int[] stack 保存“还没找到更暖一天”的日期下标，size 表示栈大小；从栈底到栈顶对应的
   温度保持单调不增。
2. 扫描到 day 时，只要当前温度高于栈顶日期温度，就弹出 previousDay，并设置
   answer[previousDay] = day - previousDay。
3. 当前 day 是从 previousDay 向后扫描遇到的第一个更高温度，因为中间日期都已处理且未让它
   出栈，所以这个等待天数一定最小。
4. 处理完能弹出的日期后，把 day 入栈；最终仍留在栈中的日期右侧没有更高温度，其 answer 保持
   int 数组初始值 0，正好符合题意。

时间复杂度：O(n)。
空间复杂度：O(n)。
*/
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        int[] stack = new int[temperatures.length];
        int size = 0;

        for (int day = 0; day < temperatures.length; day++) {
            while (size > 0 && temperatures[day] > temperatures[stack[size - 1]]) {
                int previousDay = stack[--size];
                answer[previousDay] = day - previousDay;
            }
            stack[size++] = day;
        }
        return answer;
    }
}
