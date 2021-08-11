import java.util.ArrayList;
import java.util.List;

public class FizzBuzz_LC412 {
    //sol1.0, my, using %
    public List<String> fizzBuzz1(int n) {
        List<String> res = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            if(i % 3 == 0 && i % 5 == 0){
                res.add("FizzBuzz");
            }else if(i % 3  == 0){
                res.add("Fizz");
            }else if(i % 5 == 0){
                res.add("Buzz");
            }else{
                res.add(String.valueOf(i));
            }
        }
        return res;

    }


    //sol1.1, from https://leetcode.com/problems/fizz-buzz/discuss/89931/Java-4ms-solution-Not-using-%22%22-operation
    //without using % for better performance
      public List<String> fizzBuzz(int n) {
          List<String> res = new ArrayList<>();
          for (int i = 1, b = 0, f = 0; i <= n; i++){
             b++;
             f++;
             if(f == 3 && b == 5){
                 res.add("FizzBuzz");
                 f = 0; b= 0;
             }else if(f == 3){
                 res.add("Fizz");
                 f = 0;
             }else if(b == 5){
                 res.add("Buzz");
                 b = 0;
             }else{
                 res.add(String.valueOf(i));
             }


          }
           return res;
      }
}
