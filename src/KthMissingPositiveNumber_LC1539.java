public class KthMissingPositiveNumber_LC1539 {

   //sol1,my, O(mlogn), O(1) m is the number of search done till find the kth missing, worst case arr[length -1 ], n is the length of arr
    public int findKthPositive(int[] arr, int k) {
        int count = 1, num = -1, positiveNum = 1;
        while(count <= k){
            if(!find(arr, positiveNum)){
                num = positiveNum;
                count++;
            }
            positiveNum++;


        }
        return num;

    }

    private boolean find(int[] arr, int num){
        int left = 0, right = arr.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(arr[mid] == num){
                return true;
            }else if(arr[mid] < num){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return false;

    }
}
