public class PowXOfN {
  //my way: recursive
  //T: O(logn)
  //S: O(logn)
    /*
    * 这道题先用n的绝对值做最后在取倒数会有integer overflow 问题
    * Integer.MIN_VALUE - 1 = Integer.MAX_VALUE
      Integer.MAX_VALUE + 1 = Integer.MIN_VALUE
      所以如果给-2147483648 先取绝对值，取反就变成了 2147483648， overflow最大的integer =  2147483647 + 1， Math.abs(-2147483648) = -2147483648
    *
    * */
  public double myPow(double x, int n) {
      if(x == 0){
          return 0;
      }
      if(n == 0){
          return 1;
      }
      if(n == 1){
          return x;
      }
      if(n == -1){ //先做负数的处理就不会出现overflow 问题
          return 1 / x;
      }
      double tmp = myPow(x, n / 2);

      if(n % 2 == 0){
          tmp = tmp * tmp;
      }else{
          if(n > 0) {
              tmp = tmp * tmp * x;
          }else{
              tmp = tmp * tmp * 1 / x;
          }

      }

      return tmp;
  }



}
