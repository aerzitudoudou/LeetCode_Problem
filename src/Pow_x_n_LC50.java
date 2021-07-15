public class Pow_x_n_LC50 {
/*
x: + or -
n: + or -
x   n
+/-   +    a
+/-   -    1/a


0               0                   0
0            other than 0           0
other than 0     0                  1


*/
    //sol1, recursive, my, O(logn) O(logn)
    public double myPow(double x, int n) {
        //corner case: 0 ^ 0 = 1  anything's power of 0 is 1 in this case
        if(n == 0) return 1;
        if(x == 0) return 0;
        int a = Math.abs(n);
        double res = helper(x, a);
        return n > 0 ? res : 1 / res;
    }

    private double helper(double x, int a){
        if(a == 0) return 1;
        double tmp = helper(x, a / 2);
        if(a % 2 == 0){
            return tmp * tmp;
        }
        return tmp * tmp * x;
    }
}
