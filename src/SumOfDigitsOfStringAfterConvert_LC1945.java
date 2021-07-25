public class SumOfDigitsOfStringAfterConvert_LC1945 {
    //sol1: my, O(n + k*average number length after convert each time), O(n)
    public int getLucky(String s, int k) {
        StringBuilder cStr = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            cStr.append(String.valueOf(s.charAt(i) - 'a' + 1));
        }

        int res = 0;
        for(int i = 0; i < cStr.length(); i++){
            res += cStr.charAt(i) - '0';
        }
        while(k - 1 > 0){
            res = transform(res);
            k--;
        }
        return res;
    }

    private int transform(int n){
        int res = 0;
        while(n != 0){
            int digit = n % 10;
            res += digit;
            n /= 10;
        }

        return res;
    }

}
