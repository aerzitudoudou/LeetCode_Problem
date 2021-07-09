import java.util.Random;

//from huifeng: https://www.youtube.com/watch?v=nlJ8XSIWLvo

/*
index  = 0      1       2           3              4        5
w      = 1      3       4           5              3        2
presum = 1      4       8           13             16       18
        [1],[2,3,4],[5,6,7,8],[9,10,11,12,14],[14,15,16],[17,18]   1 + rand.nextInt(18)

        take random number [1,18], first number >= rand in presum array, it's index is the result




*/
public class RandomPickWithWeight_LC528 {
    int[] presum;
    //O(n), O(n)
    public RandomPickWithWeight_LC528(int[] w) {
        presum = new int[w.length];
        int sum = 0;
        for(int i = 0; i < w.length; i++){
            presum[i] = sum + w[i];
            sum = presum[i];
        }
    }

    //O(logn), O(1)
    public int pickIndex() {
        Random rand = new Random();
        int len = presum.length;
        //O(1)
        //The time complexity of the random number generator is O(1). The time it takes does not increase as you have more random numbers.
        int target = 1 + rand.nextInt(presum[len - 1]);
        //run binary search on presum finding first index >= right
        int left = 0, right = len - 1;
        while(left + 1 < right){
            int mid = left + (right - left) / 2;
            if(presum[mid] == target){
                return mid;
            }else if(presum[mid] > target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        return (presum[left] >= target) ? left : right;

    }

}
