package groupII

func merge1(nums1 []int, m int, nums2 []int, n int) {
	ans := make([]int, 0, m+n)
	i, j := 0, 0
	for i < m && j < n {
		if nums1[i] <= nums2[j] {
			ans = append(ans, nums1[i])
			i++
		} else {
			ans = append(ans, nums2[j])
			j++
		}
	}
	for i < m {
		ans = append(ans, nums1[i])
		i++
	}
	for j < n {
		ans = append(ans, nums2[j])
		j++
	}
	copy(nums1, ans)
}

func merge(nums1 []int, m int, nums2 []int, n int) {
	i, j, k := m-1, n-1, m+n-1
	for i >= 0 && j >= 0{
		if nums1[i]>=nums2[j] {
			nums1[k] = nums1[i]
			i--
		}else {
			nums1[k]=nums2[j]
			j--
		}
		k--
	}
	for j >= 0{
		nums1[k]=nums2[j]
		j--
		k--
	}
}
