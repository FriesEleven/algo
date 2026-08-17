package groupII

/*
题目：200. 岛屿数量
链接：https://leetcode.cn/problems/number-of-islands/

题目内容：
给定由字符 '1'（陆地）和 '0'（水）组成的二维网格。上下左右相邻的陆地属于同一座岛屿，
网格边界外均视为水；返回网格中互不连通的岛屿数量。
*/

func numIslands(grid [][]byte) int {
	ans := 0
	for i := range len(grid) {
		for j := range len(grid[0]) {
			if grid[i][j] == '1' {
				ans++
				dfs(grid, i, j)
			}
		}
	}
	return ans
}

func dfs(grid [][]byte, i, j int) {
	if i < 0 || i == len(grid) || j < 0 || j == len(grid[0]) || grid[i][j] != '1' {
		return
	}
	grid[i][j] = '0'
	dfs(grid, i+1, j)
	dfs(grid, i-1, j)
	dfs(grid, i, j+1)
	dfs(grid, i, j-1)
}
