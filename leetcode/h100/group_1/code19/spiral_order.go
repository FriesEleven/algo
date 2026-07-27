package code19

/*
螺旋矩阵

给你一个 m 行 n 列的矩阵 matrix，请按照顺时针螺旋顺序，返回矩阵中的所有元素。
*/

var dirs = [][]int{
	{0, 1},
	{1, 0},
	{0, -1},
	{-1, 0},
}

func spiralOrder(matrix [][]int) (result []int) {
	n, m := len(matrix), len(matrix[0])
	result = make([]int, 0, n*m)
	i, j := 0, -1
	for dir := 0; len(result) < len(matrix)*len(matrix[0]); dir = (dir + 1) % 4 {
		for k := 0; k < m; k++ {
			i += dirs[dir][0]
			j += dirs[dir][1]
			result = append(result, matrix[i][j])
		}
		n, m = m, n-1
	}
	return
}
