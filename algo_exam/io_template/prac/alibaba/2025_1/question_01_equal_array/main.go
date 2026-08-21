package main

import (
	"bufio"
	"fmt"
	"os"
)

/*
题目：小红的相等数组
来源：2025 年春招-阿里云-研发岗-第一批笔试（编程题 1）

题目描述：
构造一个长度为 n 的整数数组 a，并满足以下条件：

1. 对任意下标 i，都有 0 <= a[i] < 2^k。
2. 数组所有元素的按位异或结果，不大于所有元素的按位与结果，即：

   a[1] XOR a[2] XOR ... XOR a[n]
   <=
   a[1] AND a[2] AND ... AND a[n]

求满足上述条件的数组共有多少种。由于答案可能很大，输出答案对 1_000_000_007 取模后的值。

输入描述：
一行输入两个整数 n 和 k。

输出描述：
输出一个整数，表示合法数组的数量对 1_000_000_007 取模后的结果。

数据范围：
1 <= n <= 100000
0 <= k <= 100000

时间限制：Go 语言 2 秒
空间限制：Go 语言 512 MB

样例输入：
2 2

样例输出：
6

样例说明：
共有 6 个合法数组：
[0,0]、[1,1]、[2,2]、[3,3]、[2,3]、[3,2]。
*/

func main() {
	in := bufio.NewReaderSize(os.Stdin, 1<<20)
	out := bufio.NewWriterSize(os.Stdout, 1<<20)
	defer out.Flush()

	var n, k int
	if _, err := fmt.Fscan(in, &n, &k); err != nil {
		return
	}

	// TODO: 在这里完成算法并将答案写入 out。
}
