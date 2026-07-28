package code21

/*
搜索二维矩阵 II

给定一个 m × n 的整数矩阵 matrix，每行从左到右升序排列，每列从上到下升序排列。
请判断目标值 target 是否存在于矩阵中。
*/
func searchMatrix(matrix [][]int, target int) (found bool) {
	i,j:=0,len(matrix[0])-1
	for i<len(matrix) && j>=0{
		if matrix[i][j]==target {
			found=true
			return
		}
		if matrix[i][j] >=target {
			j--
		}else {
			i++
		}
	}
	return
}
