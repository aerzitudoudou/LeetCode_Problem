public class ReadNCharactersGivenRead4_LC157 {
    /**
     * The read4 API is defined in the parent class Reader4.
     *     int read4(char[] buf4);
     */



    /*
    case 1:
    file : ****
    read
    n = 2
    read4 = 4
    read4 actually returns 4 but only 2 copied


    case2:
    file : ****
    read4 = 4
    n = 4
    4 copied

    case3:
    file: **
    read4 = 2
    n = 4
    2 copied


    */
        public int read(char[] buf, int n) {
            int total = 0; //total numbers have been read so far

            while(total < n){
                char[] buf4 = new char[4]; //buf to be send to read 4
                int count4 = read4(buf4); //how many read 4 have read
                int needed = n - total;
                //case 1 needed < what's read: e.g. need 2 but read 4 since file is long. then only copy 2 chars: n - total
                int count = Math.min(count4, needed); //count: how many actually need to be copyed in this loop

                //copy
                int i = 0;
                while(i < count){
                    buf[total++] = buf4[i++];
                }

                //end of file
                if(count < 4) break;

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
