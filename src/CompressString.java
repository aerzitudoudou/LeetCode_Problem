public class CompressString {
    public String compress(String input) {
        if(input == null || input.length() == 0){
            return input;
        }
        char[] ary = input.toCharArray();
        //slow 左边的都是被处理过的 to be kept
        //fast: letter being processed
        int slow = 0;
        int fast = 0;
        int newLength = 0;
        //step 1: left to right: compress all letter whose occurence > 1
        while(fast < ary.length){
            int begin = fast;
            while(fast < ary.length && ary[fast] == ary[begin]){
                fast++;
            }
            if(fast - begin == 1){
                ary[slow++] = ary[begin];
                newLength += 2;
            }else{
                ary[slow++] = ary[begin];
                //from index = slow copy the count as char into the ary
                int len = copyCount(ary, slow, fast - begin);
                slow += len;
                newLength += len + 1;
            }
        }

        //step2: right to left:

        fast = slow - 1;
        slow = newLength - 1;
        char[] res = new char[newLength];
        while(fast >= 0){
            if(Character.isDigit(ary[fast])){
                while(fast >= 0 && Character.isDigit(ary[fast])){
                    res[slow--] = ary[fast--];
                }
            }else{
                res[slow--] = '1';
            }
            res[slow--] = ary[fast--];
        }
        return new String(res);
    }

    private int copyCount(char[] ary, int index, int count){
        int len = 0;
        for(int i = count; i > 0; i /= 10){
            len++;
            index++;
        }
        for(int i = count; i > 0; i /= 10){
            int tmp = i % 10;
            ary[--index] = (char)('0' + tmp);
        }
        return len;
    }
}
