public class FastPower_Lint140 {

    public int fastPower(int a, int b, int n) {
        //!!!sol1, O(log(n)), O(1)
        //(a*b) % k = ((a % k) * (b % k))% k
        //a^n = a ^(1001) = a^(2^3 + 2^0) = a^2^3 * a^2^0
        if(b == 1) return 0;
        if(b == 0) return -1;
        long tmp = a % b;
        long res = 1;
        while(n != 0){
            if(n % 2 == 1){
                res = (res * tmp) % b; //res = res * (tmp % b)
            }
            tmp = (tmp * tmp) % b;
            n /= 2;
        }

        return (int)res;
    }
}
