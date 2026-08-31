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
	ans := countArrays(n, k)
	fmt.Fprintln(out, ans)
	// TODO: 在这里完成算法并将答案写入 out。
}

const mod int64 = 1_000_000_007

func countArrays(n, k int) int64 {
	//n个数字的同一位，一共有total种可能
	total := fastPow(2, mod, n)
	//n个数字的同一位，有奇数个1和偶数个1分别有half种可能
	half := fastPow(2, mod, n-1)
	//所有数字最终XOR结果等于和小于AND的结果有多少种
	var equalWays, lessWays int64
	//n是奇数的时候
	if n&1 == 1 {
		//针对这一位，偶数个1 和全为 1 XOR与AND相等
		equalWays = (half + 1) % mod
		//奇数个1，XOR=1，AND=0
		lessWays = 0
	} else {
		//针对这一位，全为1 XOR=0，AND=1，排除这一种情况，其余偶数个1，XOR=AND=0
		equalWays = (half - 1 + mod) % mod
		//只有全为1，满足XOR<AND
		lessWays = 1
	}
	//没有开始的时候，XOR=AND，有一种情况
	equal := int64(1)
	//没有开始的时候，没有XOR<AND
	less := int64(0)
	//从最高位开始处理
	for bit := 0; bit < k; bit++ {
		//相等情况：高位相同，当前位也相同，高位：equal，当前：equalWays
		nextEqual := equal * equalWays % mod
		//小于情况：高位小于和当前任意，高位相等和当前小于
		nextLess := (less*total + equal*lessWays) % mod
		//处理之后，记录当前这一位的结果，进入下一位，equal和less就是之前的信息
		equal = nextEqual
		less = nextLess
	}
	return (equal + less) % mod
}

func fastPow(base, mod int64, pow int) int64 {
	//初始化result
	result := int64(1)
	//防止base过大
	base %= mod
	//需要计算
	for pow > 0 {
		//当前这一位是1,需要将base的这个幂乘进去
		if pow&1 == 1 {
			result = result * base % mod
		}
		//base 变为 base^2
		base = base * base % mod
		//幂次减少
		pow >>= 1
	}
	return result
}
