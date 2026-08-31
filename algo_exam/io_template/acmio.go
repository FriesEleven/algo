package main

import (
	"bufio"
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
	s := make([]string, 0)
	var cur string
	for i := 0; i < n; i++ {
		fmt.Fscan(in, &cur)
		s = append(s, cur)
	}
	line, err := in.ReadString('\n')
	fmt.Fprintln(out, n, k)
	fmt.Fprintln(out, a)
	fmt.Fprintln(out, s)
	fmt.Fprintln(out, line)
	if err != nil {
		fmt.Fprintln(out, err)
		return
	}
}

/*
3 4
1 5 7
hello
good
nice
brave
algorithm

*/
