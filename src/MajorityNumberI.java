/*
*Laicode
*206. Majority Number I
Easy
Given an integer array of length L, find the number that occurs more than 0.5 * L times.

Assumptions

The given array is not null or empty
It is guaranteed there exists such a majority number
Examples

A = {1, 2, 1, 2, 1}, return 1
*
*
* --打擂台， 黑帮火并( Boyer-Moore Voting Algorithm)
* --在擂台上一个数，上来打擂一个数。如果一样，当前数继续留在擂台（count++）, 如果只剩一个当前数且上来打擂的数和当前不一样，则火并同归于尽，等下一个上来打擂的
*
* 证明：
* 假设a是数目超过 n/2的数
* a > n / 2 ==> a - 1 > n/2 - 1 = (n - 2)/2  ==> 火并后总数为n - 2, 因为火并，肯定有且只有一个a, 所以剩下的a个数为a - 1
*
* 所以火并后仍然满足a的个数 > 剩下的数字的总数 / 2
*
* */


public class MajorityNumberI {
    //way 1: my way
    //T:O(n)
    //S:O(1)
    public int majority(int[] array) {
        //index 0 : number   index 1: count
        int[] count = {array[0], -1};

        for(int i = 0; i < array.length; i++){
            if(count[1] != -1){
                if(array[i] == count[0]){
                    count[1]++;
                }else{
                    if(count[1] > 1){
                        count[1]--;
                    }else{
                        count[1] = -1;
                    }
                }
            }else{
                count[0] = array[i];
                count[1] = 1;
            }

        }

        return count[0];
    }


    //way2: 同样的思路，开两个variable 就好了
    public int majority2(int[] array) {
        int candidate = array[0];
        int count = 1;
        for(int i = 1; i < array.length; i++){
            if(array[i] == candidate){
                count++;
            }else if(count == 0){
                candidate = array[i];
                count++;
            }else{
                count--;
            }
        }
        return candidate;
    }

}
