package code20

/*
旋转图像

给定一个 n × n 的二维矩阵 matrix 表示一个图像，请将图像顺时针旋转 90 度。
要求直接在原矩阵上修改，不能使用另一个矩阵来完成旋转。
*/
func rotate(matrix [][]int) {
	n, m := len(matrix), len(matrix[0])
	for i := 0; i < n; i++ {
		for j := 0; j < i; j++ {
			matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]
		}
	}
	for _, row := range matrix {
		for j := 0; j < m/2; j++ {
			row[j], row[m-j-1] = row[m-j-1], row[j]
		}
	}
}
