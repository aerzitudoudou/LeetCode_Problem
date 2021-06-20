public class BestTimeToBuyAndSellStock_LC121 {
//     min: pointer at position i, the min value within the range [0, i - 1]
//     res: pointer at position i, the max difference of array[i] - min
//      when i = s.length() - 1 res is what we need
//               i                  i
//           7   1   5  3   6   4



    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        //i = 0
        int min = Integer.MAX_VALUE, res = 0;
        for(int i = 0; i < prices.length; i++){
            res = Math.max(res, prices[i] - min);
            min  = Math.min(min, prices[i]);

        }

        return res;

    }

}

