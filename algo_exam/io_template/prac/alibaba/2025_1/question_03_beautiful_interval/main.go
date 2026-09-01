package main

import (
	"bufio"
	"fmt"
	"os"
)

/*
题目：小苯的美丽区间
来源：2025 年春招-阿里云-研发岗-第一批笔试（编程题 3）

题目描述：
定义整数 x 的“美丽值” f(x)：

- 如果 x 是 2 的非负整数次幂，即 x = 2^p（p >= 0），那么 f(x) = p；
- 否则 f(x) = 0。

等价地说，对于一个正整数 x，不断把它除以 2；如果最终恰好得到 1，则它是美丽数，美丽值
就是除以 2 的次数。非美丽数（包括 0）的美丽值为 0。

给定长度为 n 的非负整数数组 a。对于每一个连续子数组 [l,r]，先计算它的元素和：

   sum(l,r) = a[l] + a[l+1] + ... + a[r]

求所有连续子数组之和的美丽值总和：

   sum(f(sum(l,r)))，其中 1 <= l <= r <= n。

输入描述：
输入包含多组测试数据。
第一行输入测试数据组数 T。
对于每组数据：
第一行输入整数 n，表示数组长度；
第二行输入 n 个整数 a[1], a[2], ..., a[n]。

输出描述：
对于每组测试数据，单独输出一行整数，表示该数组所有连续子数组之和的美丽值总和。

数据范围：
1 <= T <= 10000
1 <= n <= 300000
0 <= a[i] < 2^30
同一测试文件中所有测试数据的 n 之和不超过 300000。

时间限制：Go 语言 6 秒
空间限制：Go 语言 512 MB

样例输入：
2
5
2 4 4 3 5
4
2 2 2 2

样例输出：
15
13

样例说明（第二组）：
长度为 1 的四个子数组之和均为 2，每个美丽值为 1，总贡献为 4；
长度为 2 的三个子数组之和均为 4，每个美丽值为 2，总贡献为 6；
长度为 3 的子数组之和均为 6，美丽值为 0；
长度为 4 的唯一子数组之和为 8，美丽值为 3。
因此总和为 4 + 6 + 0 + 3 = 13。
*/

func main() {
	in := bufio.NewReaderSize(os.Stdin, 1<<20)
	out := bufio.NewWriterSize(os.Stdout, 1<<20)
	defer out.Flush()

	var group int
	if _, err := fmt.Fscan(in, &group); err != nil {
		return
	}

	for ; group > 0; group-- {
		var n int
		fmt.Fscan(in, &n)

		a := make([]int64, n)
		for i := range a {
			fmt.Fscan(in, &a[i])
		}
		ans := solve(a)
		fmt.Fprintln(out, ans)
	}
}

func solve(arr []int64) int64 {
	preSumMap := make(map[int64]int64, len(arr)+1)
	preSumMap[0] = 1
	preSum, ans := int64(0), int64(0)
	for _, val := range arr {
		preSum += val
		exponent, power := int64(1), int64(2)
		for power <= preSum {
			target := preSum - power
			cnt := preSumMap[target]
			ans += cnt * exponent
			exponent++
			power <<= 1
		}
		preSumMap[preSum]++
	}
	return ans
}
