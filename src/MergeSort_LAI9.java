public class MergeSort_LAI9 {
    //sol1, O(nlogn), O(n)
    //divide = 1 + 2 + 4 + ....+ n/2 + n= (1 - n * 2)/(1 - 2) = O(n)
    //conquer = logn * n , level nums = logn, each level does n time's merge
    public int[] mergeSort(int[] array) {
        if(array == null || array.length <= 1) return array;
        mergeSort(array, 0, array.length - 1);
        return array;
    }

    private void mergeSort(int[] array, int l, int r){
        if(l == r){
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(array, l, mid);
        mergeSort(array, mid + 1, r);
        merge(array, l, r, mid);
    }

    private void merge(int[] array, int l, int r, int mid){
        int[] helper = new int[r - l + 1];
        int s = 0, left = l, right = mid + 1;
        while(left <= mid && right <= r){
            if(array[left] < array[right]){
                helper[s++] = array[left++];
            }else{
                helper[s++] = array[right++];
            }
        }
        while(right <= r){
            helper[s++] = array[right++];
        }
        while(left <= mid){
            helper[s++] = array[left++];
        }

        s = 0;
        for(int i = l; i <= r; i++) {
            array[i] = helper[s++];
        }
    }

}
