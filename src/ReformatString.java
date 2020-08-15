/*
* Description
Giving a string str and an array sublen[],
sublen[0] + sublen[1] +...+ sublen[sublen.size() - 1] == str.length(),
according to sublen[],slice the string,
sublen[i] is the length of the i-th substring.Swap the (2k-1)-th substring with the (2k)-th substring.
If the len(subset) is odd, the last one remains the same.Returns the new string.
sublen.length \leq str.length \leq 100000sublen.length≤str.length≤100000
*
*
*
* */

public class ReformatString {
    public String reformatString(String str, int[] sublen) {
        if(str == null || str.length() == 0 || sublen == null || sublen.length == 0){
            return str;
        }
        char[] ary = str.toCharArray();
        int offset = 0;
        for(int k = 1; 2 * k <= sublen.length; k++){
            //reverse first part
            int s1 = offset;
            int e1 = offset + sublen[2 * k - 2] - 1;

            reverse(ary, s1, e1);
            offset += sublen[2 * k - 2];

            //reverse seconde part
            int s2 = offset;
            int e2 = offset + sublen[2 * k - 1] - 1;
            reverse(ary, s2, e2);
            offset += sublen[2 * k - 1];

            //reverse whole part
            reverse(ary, s1, e2);

        }
        return String.valueOf(ary);






    }
    private void reverse(char[] ary, int start, int end){
        while(start < end){
            char tmp = ary[start];
            ary[start] = ary[end];
            ary[end] = tmp;
            start++;
            end--;
        }
    }

}
