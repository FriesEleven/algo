package groupII

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
