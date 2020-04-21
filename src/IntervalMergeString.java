/*
* lintcode
*1889. Interval Merge

Now give you two string intervals (in lexicographic order), please judge whether the two intervals can be merged.
The string intervals [a, b) includes all strings that begin with a.
For example, the interval [a, b) and the interval [ab, c) can be merged, and the interval [a, b) and the interval [b, c] can also be merged.
If two string intervals can be merged, return true, otherwise, return false.

Example
Example 1

input："[a,b]" "[b,c]"
output： true
Example 2

input："[a,b]" "(b,c]"
output： true
Example 3

input："[a,b)" "(b,c]"
output： false
Example 4

input："(b,c)" "[a,b]"
output： true
Clarification
If two intervals A and B satisfy that A⋃B is a continuous interval, then A and B can be merged.
*
*
* 1. String的字典序（lexicographic）可以用sting1.compareTo(string2)
* 2. sting1.compareTo(string2) = -1 string1 优先级高 , = 0 两个优先级一样 = 1 string2 优先级高 （
* 3.  compareTo 只有-1， 1， 0 三种结果。判断两个String 是否lexicographical continuous, 需要用 a.equals(b + "a")判断。 两个连续字符串只相差一个a
* 4. String 的 + 后面不能是character 只能是string
* 5. 让A的第一部分string lexicographic order 优先于B的第一部分（不是的话switch顺序）， 分类讨论
*       5.1 string1 == string 2 ([], [), (], ())
*
*       5.2 string1 < String2
*            5.2.1 string2 = string1 + a ([], [), (], ())
*            5.2.2 string2 != string1 + a ([], [), (], ())
*       5.3 string1 > string 2
* suppose average string length = n
* T: n
* S: n
* */

public class IntervalMergeString {
    public boolean MergeJudge(String interval_A, String interval_B) {
        //corner case check
        if(interval_A == null || interval_B == null){
            return false;
        }
        if(interval_A.length() < 3 || interval_B.length() < 3){
            return false;
        }
        String a = interval_A.substring(1, interval_A.indexOf(','));
        String b = interval_B.substring(1, interval_B.indexOf(','));
        if(a.compareTo(b) < 0){
            return valid(interval_A, interval_B);
        }else{
            return valid(interval_B, interval_A);
        }



    }

    private boolean valid(String interval_A, String interval_B){

        String i1 = interval_A.substring(interval_A.indexOf(',') + 1, interval_A.length() - 1); //第一个interval 后一个值
        char i2 = interval_A.charAt(interval_A.length() - 1); //第一个interval 右括号


        String j1 = interval_B.substring(1, interval_B.indexOf(',')); //第二个interval 前一个值
        char j2 = interval_B.charAt(0);//第二个interval 左括号

        if(j1.equals(i1)){
            if(i2 == ']' || j2 == '[') return true;
            return false;
        }
        if(i1.compareTo(j1) < 0){
            if(j1.equals(i1 + "a") && i2 == ']' && j2 == '['){
                return true;
            }
            return false;
        }
        return true;


    }
}
