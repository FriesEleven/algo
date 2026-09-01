package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
)

/*
题目：小红的数组切割
来源：2025 年春招-阿里云-研发岗-第一批笔试（编程题 2）

题目描述：
给定一个长度为 n 的整数数组 a，以及一个长度为 n、仅由字符 '0' 和 '1' 组成的字符串 s。
你可以把数组按原有顺序切成至多 k 个连续非空块，块编号从 1 开始。

设元素 a[i] 位于第 j 块，它产生的权值为：

   op(i) * (a[i] + j)

其中 op(i) 由 s[i] 决定：

- 当 s[i] = '1' 时，op(i) = 1；
- 当 s[i] = '0' 时，op(i) = -1。

数组总权值等于所有元素权值之和。请选择合适的切割位置和块数，使总权值最大，并输出这个
最大值。

输入描述：
第一行输入两个整数 n 和 k，分别表示数组长度和最多允许的块数。
第二行输入 n 个整数 a[1], a[2], ..., a[n]。
第三行输入一个长度为 n 的二进制字符串 s。

输出描述：
输出一个整数，表示能够得到的最大总权值。

数据范围：
2 <= n <= 100000
1 <= k <= n
1 <= a[i] <= 1000000000

时间限制：Go 语言 2 秒
空间限制：Go 语言 512 MB

样例输入：
4 2
1 2 3 4
1001

样例输出：
1

样例说明：
一种最优方案是把数组切成 [1,2,3] 和 [4] 两块。
*/

func main() {
	in := bufio.NewReaderSize(os.Stdin, 1<<20)
	out := bufio.NewWriterSize(os.Stdout, 1<<20)
	defer out.Flush()

	var n, k int
	if _, err := fmt.Fscan(in, &n, &k); err != nil {
		return
	}

	a := make([]int64, n)
	for i := range a {
		fmt.Fscan(in, &a[i])
	}

	var s string
	fmt.Fscan(in, &s)

	ans := solve(a, s, k)
	fmt.Fprintln(out, ans)
}

func solve(a []int64, s string, k int) int64 {
	weight, base := weightAndBase(a, s)
	score := cut(weight)
	best := selectBestK(score, k-1)
	return base + best
}

func weightAndBase(a []int64, s string) ([]int64, int64) {
	weights := make([]int64, len(a))
	base := int64(0)
	for i := range s {
		if s[i] == '1' {
			weights[i] = 1
		} else {
			weights[i] = -1
		}
		base += weights[i] * (a[i] + 1)
	}
	return weights, base
}

func cut(weight []int64) []int64 {
	score := make([]int64, len(weight)-1)
	sufSum := int64(0)
	for i := len(weight) - 1; i >= 1; i-- {
		sufSum += weight[i]
		score = append(score, sufSum)
	}
	return score
}

func selectBestK(score []int64, k int) int64 {
	sort.Slice(score, func(i, j int) bool {
		return score[i] > score[j]
	})
	result := int64(0)
	for i := 0; i < len(score) && i < k; i++ {
		if score[i] <= 0 {
			break
		}
		result += score[i]
	}
	return result
}
