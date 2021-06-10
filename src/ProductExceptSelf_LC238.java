/*
               1       2    3      4

                      0   0*1    0*1*2
pre product    1       1    2      6
              1*2*3 2*3     3
               24     12    4      1
              1*2*3   0*2*1 0*1*3  0* 1* 2
               24      12      8     6


res[i] = res[i] * right
right = right * num[i]
*/


class ProductExceptSelf_LC238 {

    //!!!!sol1 only 1 array O(n) O(n)
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for(int i = 1; i < nums.length; i++){
            res[i] = nums[i - 1] * res[i - 1];
        }

        int right = 1;
        for(int i = nums.length -1 ; i >= 0; i--){
            res[i] = res[i] * right;
            right = right * nums[i];
        }
        return res;


    }

    //my O(n) O(n)
    public int[] productExceptSelf1(int[] nums) {
        int[] preProd = new int[nums.length];
        int[] postProd = new int[nums.length];
        int[] res = new int[nums.length];
        preProd[0] = 1;
        postProd[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            preProd[i] = preProd[i - 1] * nums[i - 1];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            postProd[i] = postProd[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            res[i] = preProd[i] * postProd[i];

        }
        return res;


    }




}