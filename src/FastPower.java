/*

lintcode 140

Calculate the an % b where a, b and n are all 32bit non-negative integers.

Example
For 231 % 3 = 2

For 1001000 % 1000 = 0

Challenge
O(logn)
*
*
* */



public class FastPower {
    public int fastPower(int a, int b, int n) {
        if(n == 0){
            return 1 % b;
        }
        if(n == 1){
            return a % b;
        }
        long tmp = fastPower(a, b, n / 2);


        if(n % 2 == 0){
            tmp = (tmp * tmp) % b;

        }
        if(n % 2 == 1){

            tmp = (((tmp * tmp ) % b) * (a % b)) % b;
        }
        return (int)tmp;


    }

    /*
   这道题考查同余定理的应用：
    同余定理：
    (a+b) % p = (a%p + b%p) % p
    (a-b) % p = (a%p - b%p) % p
    (a*b) % p = (a%p * b%p) % p
    (a^b) %p = ((a%p) ^ b) %p



    (a*b*d)%c = ((a%c)*(b%c)*(d%c)) % c;

    a = 2
    b = 3
    n = 4

    2^4 % 3 = 1
    2^2 % 3 = 1

    a = 2
    b = 3
    n = 5


    2^5 % 3 = 2
    2^2 % 3 = 1

    (1 % 3 * 2 % 3) % 3 = 2




    tmp = a^ n/2 % b   1


    n 是偶数

    结果 =( tmp * tmp ) % b


    n是奇数

    结果 ={[( tmp * tmp ) % b] * a % b} % b

    */


}
