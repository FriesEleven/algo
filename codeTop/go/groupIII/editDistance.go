package groupIII

func minDistance(word1 string, word2 string) int {
	return editDistance(word1,word2,1,1,1)
}

func editDistance(str, target string, insertW, deleteW, replaceW int) int {
	source := []rune(str)
	dest := []rune(target)
	m, n := len(source), len(dest)
	dp := make([][]int, m+1)
	for i := range dp {
		dp[i] = make([]int, n+1)
	}
	for i := 0; i <= m; i++ {
		dp[i][0] = i * deleteW
	}
	for j := 0; j <= n; j++ {
		dp[0][j] = j * insertW
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if source[i-1] == dest[j-1] {
				dp[i][j] = dp[i-1][j-1]
				continue
			}
			replaceCost := dp[i-1][j-1] + replaceW
			deleteCost := dp[i-1][j] + deleteW
			insertCost := dp[i][j-1] + insertW
			dp[i][j] = min(replaceCost, insertCost, deleteCost)
		}
	}
	return dp[m][n]
}
