package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"os"
)

func main() {
	in := bufio.NewReaderSize(os.Stdin, 1<<20)
	out := bufio.NewWriterSize(os.Stdout, 1<<20)
	defer out.Flush()

	var n, k int
	if _, err := fmt.Fscan(in, &n, &k); err != nil {
		return
	}

	a := make([]int, n)
	for i := range a {
		fmt.Fscan(in, &a[i])
	}

	ans := solve(a, k)
	fmt.Fprintln(out, ans)
}

func solve(arr []int, k int) int64 {
	var h maxHeap
	heap.Init(&h)
	sum, score, n := int64(0), int64(0), len(arr)
	for _, num := range arr {
		sum += int64(num)
	}
	for i := n - 1; i >= 0; i-- {
		if (i+1)%k == 0 && h.Len() != 0 {
			score += int64(heap.Pop(&h).(int))
		}
		heap.Push(&h, arr[i])
	}
	return sum - score
}

type maxHeap []int

func (h maxHeap) Len() int {
	return len(h)
}

func (h maxHeap) Less(i, j int) bool {
	return h[i] > h[j]
}

func (h maxHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
}

func (h *maxHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}

func (h *maxHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[:n-1]
	return x
}

// 题目：小红闯关
// 试卷：2024年秋招-阿里云-研发岗-第一批笔试
// 题目直链：https://www.nowcoder.com/questionTerminal/7ce4b75f7a304be481e73bc4dd2705a4
//
// 题意摘要：小红在玩一个游戏,这个游戏有 n 个关卡,通过第 i 个关卡需要消耗 a_i 个单位时间,小红必须按从前往后的顺序通过每一个关卡。 每通过 k 个关卡,小红会获得一个跳关道具,跳关道具可以在任意一个关卡使用,使用跳关道具后可以不消耗时间直接通过关卡。 小红想知道她通过这 n 个关卡,最少需要多少时间。
// 输入：第一行输入两个整数 n, k(1 ≤ n, k ≤ 10^5) 代表关卡数量和获得跳关道具的条件。 第二行输入 n 个整数 a_1, a_2, ..., a_n(1 ≤ a_i ≤ 10^5) 代表通过每个关卡需要消耗的时间。
// 输出：在一行上输出一个整数,表示小红通过这 n 个关卡所需的最少时间。
// 时间限制：1 秒；其他语言 2 秒
// 空间限制：256 MB；其他语言 512 MB
//
// 说明：本文件仅保留简明题意，不含输入输出代码模板或解法；完整题面以原链接为准。
