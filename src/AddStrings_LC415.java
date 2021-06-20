public class AddStrings_LC415 {
    /*
 from right to left

 (6 + 8) / 10 = 1
 (6+8)%10 = 4

 carry = (d1 + d2 + carry) / 10
 cur = (d1 + d2 + carry) % 10
 similar to "add binary" question

 */
    //Sol1: my, O(max(m,n)) , O(max(m,n))
    public String addStrings(String num1, String num2) {
        int cur = 0, carry = 0;
        int i = num1.length() - 1, j = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        while(i >= 0 || j >= 0){
            int one = 0, two = 0;
            if(i >= 0) one = num1.charAt(i) - '0';
            if(j >= 0) two = num2.charAt(j) - '0';
            cur = (one + two + carry) % 10;
            sb.insert(0, cur);
            carry = (one + two + carry) / 10;
            i--;
            j--;

        }
        //don't forget to add carry!
        if(carry == 1) sb.insert(0, 1);
        return sb.toString();

    }
}
