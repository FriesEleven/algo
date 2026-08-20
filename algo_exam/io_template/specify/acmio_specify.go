package main

import (
	"bufio"
	"os"
	"fmt"
)

func main() {
	// 缓冲输入
	in := bufio.NewReaderSize(os.Stdin, 1<<20)

	// 缓冲输出
	out := bufio.NewWriterSize(os.Stdout, 1<<20)
	defer out.Flush()

	for {
		var n, m int

		// 一直读取到文件结束 EOF
		if _, err := fmt.Fscan(in, &n, &m); err != nil {
			break
		}

		// 创建 n 行 m 列的二维切片
		mat := make([][]int, n)
		for i := range mat {
			mat[i] = make([]int, m)

			for j := range mat[i] {
				fmt.Fscan(in, &mat[i][j])
			}
		}

		fmt.Println(mat)
	}
}