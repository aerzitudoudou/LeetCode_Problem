import java.util.HashMap;
import java.util.Map;

public class CountArray {
    public int[] countArray(int[] array) {
    /*
    use merge sort.
    update the couter array by using the lookup map to get the index of a number in the array
        i     mid      j
    xxxxXxxxxx x   yyyyY
    if X < Y merge X , X's counter + = j - mid - 1, i++;
    if X > Y merge Y , j++
    */
        //num    number's index in array
        if(array == null || array.length == 0){
            return array;
        }
        int[] res = new int[array.length];
        int[] index = new int[array.length];
        initIndex(index);
        int[] helper = new int[array.length];
        int[] indexCopy = new int[array.length];
        mergeSort(array, res, helper, index, indexCopy, 0, array.length - 1);
        return res;

    }

    private void initIndex(int[] index){
        for(int i = 0; i < index.length; i++){
            index[i] = i;
        }
    }

    private void mergeSort(int[] array, int[] res, int[] helper, int[] index, int[] indexCopy, int l, int r){
        if(l >= r){
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(array, res, helper, index, indexCopy,  l, mid);
        mergeSort(array, res, helper, index, indexCopy, mid + 1, r);
        merge(array, res, helper, index, indexCopy, l, mid, r);
    }

    private void merge(int[] array, int[] res, int[] helper, int[] index, int[] indexCopy, int l, int mid, int r){
        for(int i = l; i <= r; i++){
            helper[i] = array[i];
            indexCopy[i] = index[i];
        }

        int left = l;
        int right = mid + 1;
        int cur = l;
        while(left <= mid && right <= r){
            if(helper[left] <= helper[right]){
                array[cur] = helper[left];
                index[cur] = indexCopy[left];
                res[index[cur]] += right - mid - 1;
                cur++;
                left++;

            }else{
                array[cur] = helper[right];
                index[cur] = indexCopy[right];
                cur++;
                right++;
            }
        }
        while(left <= mid){
            array[cur] = helper[left];
            index[cur] = indexCopy[left];
            res[index[cur]] += right - mid - 1;
            cur++;
            left++;
        }


    }


}
