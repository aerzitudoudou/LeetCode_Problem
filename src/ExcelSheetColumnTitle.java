/*

lintcode
*1350. Excel Sheet Column Title
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

Example
Example1

Input: 28
Output: "AB"
Example2

Input: 29
Output: "AC"
Notice
1 -> A
2 -> B
3 -> C
 ...
26 -> Z
27 -> AA
28 -> AB
* */

public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        //一直对26取模，最后一次取模对应结果的第一位‘’
        //INT -> CHAR: (char)('A' + (i - 1))
        StringBuilder sb = new StringBuilder();
        int mod = 0;

        while(n > 0){
            n--; //第一次做错了！ 必须先减一，再取模 e.g. Z 就是A + 25 , Z 本身是26，所以要-1 = 25 再取模 因为这里A是对应的1
            //如果A对应的是0就不用减1了
            mod = n % 26;
            sb.insert(0, (char)('A' + mod));
            n /= 26;

        }
        return sb.toString();

    }
}
