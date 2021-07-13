public class MergeSortedArray_LC88 {
    //sol1, my, from end to beginning
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        int index = nums1.length - 1;
        while(i >= 0 && j >= 0){
            if(nums1[i] > nums2[j]){
                nums1[index--] = nums1[i--];
            }else{
                nums1[index--] = nums2[j--];
            }
        }

        while(j >= 0){
            nums1[index--] = nums2[j--];
        }

    }
}
