public class Pow_x_n_LC50 {
/*
x: + or -
n: + or -
x   n
+/-   +    a
+/-   -    1/a


0               0                   1
0            other than 0           0
other than 0     0                  1


*/
    //sol1, from lai, recursive,  O(logn), O(logn)
    public double myPow(double x, int n) {
        //n = 0, <0, >0
        //x = 0, x < 0, x > 0

        if(x == 0) return 0;
        if(n == 0) return 1;
        int a = Math.abs(n);
        if(n > 0) return helper(x, n);
        else return (1 / helper(x, n));

    }

    //O(logn), O(logn)
    private double helper(double x, int n){
        if(n == 0) return 1;
        double tmp = helper(x, n / 2);
        if(n % 2 == 0) return tmp * tmp;
        else return tmp * tmp * x;
    }


    //!!!sol2, iterative, O(logn), O(1)
    public double myPow2(double x, int n) {
        //o^0 = 1
        if(n == 0) return 1;
        if(x == 0) return 0;

        //don't need to care about negative/positive of n, n is only used for cutting the range into half
        if(n < 0) x = 1 / x;
        double tmp = x;
        double res = 1;
        while(n != 0){
            //-3%2 = -1 3%2 = 1, so careful not to use n%2 == 1 to decide whether the last digit = 1
            if(n % 2 != 0){
                res *= tmp;
            }

            tmp = tmp * tmp;
            //-3/2 = -1
            n /= 2;
        }



        return res;
    }


    //sol3, my,with consideration of n's sign,  O(logn), O(1)
    public double myPow3(double x, int n) {
        if(n == 0) return 1;
        if(x == 0) return 0;
        //Math.abs(Integer.MIN_VALUE) = Integer.MIN_VALUE, so need a long value to hold the abs value of Integer.MIN_VALUE
        long a = Math.abs(n);
        double tmp = x;
        double res = 1;
        while(a != 0){
            //-3%2 = -1 3%2 = 1, so careful not to use n%2 == 1 to decide the last digit = 1
            if(a % 2 != 0){
                res *= tmp;
            }

            tmp = tmp * tmp;
            a /= 2;
        }



        return n < 0 ? 1 / res : res;
    }
}
