import java.util.Arrays;

public class CountNumbers {
    public int countElements(int[] arr) {
        Arrays.sort(arr);
        //slow: the number before has been checked for counter
        //fast pointer: next number for checking against
        int slow = 0;
        int fast = 0;
        int counter = 0;
        while(fast < arr.length){
            while(fast < arr.length && arr[fast] == arr[slow]){
                fast++;
            }
            if(fast == arr.length){
                break;
            }
            while(arr[slow] + 1 == arr[fast]){
                slow++;
                counter++;
            }
            while(arr[slow] + 1 < arr[fast]){
                slow++;
            }


        }
        return counter;

    }
}
