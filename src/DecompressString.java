public class DecompressString {
    public String decompress(String input) {
        if(input == null || input.length() == 0){
            return input;
        }
        char[] ary = input.toCharArray();

        int len = decodeShort(ary);
        //handle case where occurance time > 2
        //calculate new length
        int newLength = len;
        for(int i = 0; i < len; i++){
            if(getDigit(ary[i]) > 2 && getDigit(ary[i]) <= 9){
                newLength += getDigit(ary[i]) - 2;
            }
        }
        char[] res = new char[newLength];
        int slow = newLength - 1;
        for(int fast = len - 1; fast >= 0; fast--){
            int temp = getDigit(ary[fast]);
            if(temp > 2 && temp <= 9){
                fast--;
                for(int i = 0; i < temp; i++){
                    res[slow--] = ary[fast];
                }
            }else{
                res[slow--] = ary[fast];
            }
        }
        return new String(res);




    }


    private int decodeShort(char[] ary){
        int slow  = 0;
        //handle where occurance time <= 2
        for(int fast = 0; fast < ary.length; fast+=2){
            int temp = getDigit(ary[fast + 1]);
            if(temp >= 0 && temp<= 2){
                for(int i = 0; i < temp; i++){
                    ary[slow++] = ary[fast];
                }
            }else{
                ary[slow++] = ary[fast];
                ary[slow++] = ary[fast + 1];
            }
        }
        return slow;
    }
    private int getDigit(char c){
        return (int)(c - '1');
    }
}
