public class KClosest {
    //2021/03/28
    public int[] kClosest(int[] array, int target, int k) {
        if(array == null || array.length == 0){
            return null;
        }
        int[] res = new int[k];
        int l = 0, r = array.length - 1;
        while(l + 1 < r){
            int mid = l + (r - l) / 2;
            //1 2 3 |4|
            //1 2 |3| 4 5
            if(array[mid] == target){
                l = mid;
                r = mid + 1;
            }else if(array[mid] > target){
                r = mid;
            }else{
                l = mid;
            }
        }

        //from center move l and r to generate the result
        int i = 0;
        for(i = 0; i < k; i++){

                if(r > array.length - 1 || l >= 0 && Math.abs(array[l] - target) <= Math.abs(array[r] - target)){
                    res[i] = array[l--];

                }else{
                    res[i] = array[r++];

                }


        }



        return res;



    }
}
