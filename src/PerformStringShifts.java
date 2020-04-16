public class PerformStringShifts {

     /*my way:
     s'length is m  shift's length is n
     T: O(n + m)
     S: O(m)

    */

    public String stringShift(String s, int[][] shift) {
        int count = 0;
        for(int i = 0; i < shift.length; i++){
            if(shift[i][0] == 0){
                count = count - shift[i][1];
            }else{
                count = count + shift[i][1];
            }
        }
        count = count % s.length();
        //找到分隔的index
        int index = count < 0 ? Math.abs(count) : s.length() - count;
        char[] ary = s.toCharArray();
        //i love yahoo

        reverse(ary, 0, index - 1);
        reverse(ary, index, s.length() - 1);
        reverse(ary, 0, s.length() - 1);
        return new String(ary);
    }
    private char[] reverse(char[] ary, int i, int j){
        while(i < j){
            swap(ary, i++, j--);
        }
        return ary;
    }
    private void swap(char[] ary, int i, int j){
        char tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }

    /*
    * solution way: use substring directly
    * */

    public String stringShift2(String s, int[][] shift) {
        int count = 0;
        for (int i = 0; i < shift.length; i++) {
            if (shift[i][0] == 0) {
                count = count - shift[i][1];
            } else {
                count = count + shift[i][1];
            }
        }
        count = count % s.length();
        //找到分隔的index
        int index = count < 0 ? Math.abs(count) : s.length() - count;
        //subString[)
        String res = s.substring(0, index) + s.substring(index, s.length());
        return res;
    }

}
