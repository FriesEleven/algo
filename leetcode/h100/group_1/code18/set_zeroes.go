package code18

/*给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。*/

func setZeroes(matrix [][]int) {
	n := len(matrix)
	m := len(matrix[0])
	firstRowHasZero := false
	for _, val := range matrix[0] {
		if val == 0 {
			firstRowHasZero = true
			break
		}
	}
	firstColHasZero := false
	for i := 0; i < n; i++ {
		if matrix[i][0] == 0 {
			firstColHasZero = true
			break
		}
	}
	for i := 1; i < n; i++ {
		for j := 1; j < m; j++ {
			if matrix[i][j] == 0 {
				matrix[i][0] = 0
				matrix[0][j] = 0
			}
		}
	}
	for i := 1; i < n; i++ {
		for j := 1; j < m; j++ {
			if matrix[i][0] == 0 || matrix[0][j] == 0 {
				matrix[i][j] = 0
			}
		}
	}
	if firstColHasZero {
		for i := 0; i < n; i++ {
			matrix[i][0] = 0
		}
	}
	if firstRowHasZero {
		for j := 0; j < m; j++ {
			matrix[0][j] = 0
		}
	}
}
