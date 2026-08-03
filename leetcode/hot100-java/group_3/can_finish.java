import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/*
课程表

共有 numCourses 门课程，课程编号为 0 到 numCourses - 1。先修关系 prerequisites[i] = [a, b]
表示学习课程 a 之前必须先完成课程 b。判断是否能够完成全部课程。

算法实现说明：
1. 把每条 [a, b] 建成从 b 指向 a 的边：graph 记录学完某课程后可以继续学习哪些课程，
   indegree[a] 记录课程 a 还有多少门先修课未完成。
2. 先把所有 indegree 为零的课程加入 queue，它们不依赖其他未完成课程，可以立即学习。
3. 每次出队一门课程就将 completed 加一，并把它指向课程的 indegree 减一；某门课降到零时，
   说明其全部先修课都已处理，可以入队。
4. 若最终 completed 等于 numCourses，就得到了一种合法学习顺序；若小于课程总数，剩余课程
   必然困在有向环中，所以无法完成全部课程。

时间复杂度：O(V + E)，V 为课程数，E 为先修关系数。
空间复杂度：O(V + E)，用于邻接表、入度数组和队列。
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int course = 0; course < numCourses; course++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int previous = prerequisite[1];
            graph.get(previous).add(course);
            indegree[course]++;
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int course = 0; course < numCourses; course++) {
            if (indegree[course] == 0) {
                queue.offer(course);
            }
        }

        int completed = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            completed++;
            for (int nextCourse : graph.get(course)) {
                if (--indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        return completed == numCourses;
    }
}
