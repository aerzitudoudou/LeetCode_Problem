public class SqrtX_LC69 {
    //sol2, my, binary search, O(logx), O(1)
    public int mySqrt(int x) {
        long l = 0, r = Integer.MAX_VALUE;
        while(l < r){
            long mid = l + (r - l + 1) / 2;
            if(mid * mid <= x){
                l = mid;
            }else{
                r = mid - 1;
            }
        }

        return (int)r;


    }

    //sol2.1, moban 2
    public int mySqrt2(int x) {
        long l = 0, r = Integer.MAX_VALUE;
        while(l < r){
            long mid = l + (r - l) / 2;
            if(mid * mid > x) r = mid;
            else l = mid + 1;
        }
        return (int)r - 1;
    }

    //sol1, my, brute force, O(sqrt(x)), O(1)
//     public int mySqrt(int x) {
//         long i = 0;
//         while(i * i <= x){
//             i++;
//         }

//         return (int)i - 1;

//     }
}
