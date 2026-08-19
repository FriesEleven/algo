package groupIII

import "strconv"

func addStrings(num1 string, num2 string) string {
	carry := 0
	ans := ""
	for i, j := len(num1)-1, len(num2)-1; i >= 0 || j >= 0 || carry > 0; i, j = i-1, j-1 {
		x, y := 0, 0
		if i >= 0 {
			x = int(num1[i] - '0')
		}
		if j >= 0 {
			y = int(num2[j] - '0')
		}
		sum := x + y + carry
		ans = strconv.Itoa(sum%10) + ans
		carry = sum / 10
	}
	return ans
}
