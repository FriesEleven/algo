package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	// 按行读取标准输入
	in := bufio.NewScanner(os.Stdin)

	// 调大单行最大长度，Scanner 默认最大约为 64 KB
	in.Buffer(make([]byte, 1024), 1<<20)

	// 缓冲输出
	out := bufio.NewWriterSize(os.Stdout, 1<<20)
	defer out.Flush()

	// Scan 返回 false，表示读取结束或发生错误
	for in.Scan() {
		line := in.Text()
		parts := strings.Fields(line)

		sum := 0
		for _, num := range parts {
			value, err := strconv.Atoi(num)
			if err != nil {
				return
			}
			sum += value
		}

		fmt.Fprintln(out, sum)
	}

	if err := in.Err(); err != nil {
		fmt.Fprintln(os.Stderr, "读取输入失败：", err)
	}
}
