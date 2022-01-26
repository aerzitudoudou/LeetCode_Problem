public class SearchInABigSortedArray_Lint447 {
    static int[] arr;
    public int searchBigSortedArray(ArrayReader reader, int target) {

        int l = 0, r = 1;
        while(reader.get(r) < target){
            r *= 2;
        }

        while(l < r){
            int mid = l + (r - l) / 2;
            if(reader.get(mid) >= target){
                r = mid;
            }else l = mid + 1;
        }

        return reader.get(r) == target ? r : -1;

    }


    public static class ArrayReader {

        public ArrayReader(int[] input){
            arr = input;
        }
        private static int get(int r){
            return arr[r];
        }
    }
}
