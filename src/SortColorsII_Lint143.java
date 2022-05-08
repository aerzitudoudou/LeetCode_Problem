public class SortColorsII_Lint143 {
    //sol1, my, O(nlogk), O(logk)
    public void sortColors2(int[] nums, int k) {
        rainbowSort(nums, 0, nums.length - 1, 1, k);
    }

    //[0, i) <= mid color
    //[i, j] tbd
    //(j, len - 1] > mid color
    private void rainbowSort(int[] nums, int start, int end, int minColor, int maxColor){
        if(start >= end || minColor >= maxColor) return;

        int midColor = minColor + (maxColor - minColor) / 2;
        int i = start, j = end;
        while(i <= j){
            if(nums[i] <= midColor){
                i++;
            }else if(nums[j] > midColor){
                j--;
            }else{
                swap(nums, i, j);
                i++;
                j--;
            }
        }


        rainbowSort(nums, start, i - 1, minColor, midColor);
        rainbowSort(nums, j + 1, end, midColor + 1, maxColor);

    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
