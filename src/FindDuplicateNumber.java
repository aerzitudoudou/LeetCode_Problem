//leetcode 287
public class FindDuplicateNumber {
    class Solution {
        //sol 1: T: N S:N
//     public int findDuplicate(int[] nums) {
//         boolean[] ary = new boolean[nums.length];
//         for(int i = 0; i < nums.length; i++){
//             if(ary[nums[i]]){
//                 return nums[i];
//             }
//             ary[nums[i]] = true;
//         }
//         return -1;

//     }



    /*
    0 1 2 3 4     index

    1 3 4 2 2

    3 1 4 2 2

    2 1 4 3 2

    4 1 2 3 2

    2 1 2 3 4

    2

    */

        //sol2 T:O(N) S:O(1) in place, change the orginal array
//     public int findDuplicate(int[] nums) {
//       if(nums == null){
//           return -1;
//       }

//       while(nums[0] != nums[nums[0]]){
//           swap(nums, 0, nums[0]);
//       }
//       return nums[0];

//     }

//     private void swap(int[] nums, int i, int j){
//         int tmp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = tmp;
//     }

        //cycle detection

    /*

    0 1 2 3 4
    1 3 4 2 2

    1-> 3 -> 2 -> 4
             ^  |
             | -|
    */


    /*
                b
        a     /---\meet point
      -------     |              total length of the circle: c | till meet the first time:b
             \----/


    s: a + b
    f = a + b + c
    2s = f  f's speed is twice the speed of s

    2(a + b) = a + b + c
    a = c - b    garantee if s moves to the beginning, and f move from the meet point, they will encounter at the duplicate point

    */

        //sol3:  cycle detection , in place, doesn't change orignial array, see solution
        public int findDuplicate(int[] nums) {
            //find where f and s meet
            //wrong at the first time! once init f and s are the same
            //init state has to be different in order to trigger the loop
//         int init = nums[0], f = nums[nums[init]], s = nums[init];
//         while(f != s){
//             //f move 2 steps, s move 1 step
//             f = nums[nums[f]];
//             s = nums[s];
//         }

            //or do "do while" to avoid not triggered issue
            int f = nums[0], s = nums[0];
            do{
                f = nums[nums[f]];
                s = nums[s];
            }while(f != s);

            s = nums[0];
            while(s != f){
                s = nums[s];
                f = nums[f];
            }
            return s;

        }
    }



}
