package groupII

func maxProfit(prices []int) int {
	ans, minPrice := 0, prices[0]
	for i := 1; i < len(prices); i++ {
		minPrice = min(minPrice, prices[i])
		ans = max(ans, prices[i]-minPrice)
	}
	return ans
}
