public class ReadNCharactersGivenRead4II_LC158 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */

    //sol1: from happygirlzt: https://www.youtube.com/watch?v=HcmRR2If2Hk

    //class variable to remember the current read state.
    private int curIndexInBuf4 = 0; //index of cur char to be processed in the buf4
    private int curResNumFromBuf4 = 0; //how many number i can get from read 4 currently
    private char[] buf4 = new char[4]; //current buf used as destination by calling read4


    public int read(char[] buf, int n) {
        int total = 0; //res
        while(total < n){
            //nothing in buf4
            if(curIndexInBuf4 == 0){
                curResNumFromBuf4 = read4(buf4);
            }
            //copy
            while(total < n && curIndexInBuf4 < curResNumFromBuf4){
                buf[total++] = buf4[curIndexInBuf4++];
            }

            //used up current buf4
            if(curIndexInBuf4 == curResNumFromBuf4) curIndexInBuf4 = 0;
            //end of file
            if(curResNumFromBuf4 < 4) break;
        }
        return total;


    }

    /*
      Definition of read4:

       Parameter:  char[] buf4
       Returns:    int

       buf4[] is a destination, not a source. The results from read4 will be copied to buf4[].
    */
    private int read4(char[] buf4){

        return 0;//helper function:
    }

}
