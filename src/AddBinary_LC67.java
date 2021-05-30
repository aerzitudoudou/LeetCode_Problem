public class AddBinary_LC67 {
    //lc 67


    //sol2. % is binary add result  / is binary add carry

    public String addBinary2(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while(i >= 0 || j >= 0){
            int aN = 0, bN = 0;
            if(i >= 0) aN = a.charAt(i--) - '0';
            if(j >= 0) bN = b.charAt(j--) - '0';
            int curBinary = (aN + bN + carry) % 2;
            //carry 位记得加！！！！
            carry = (aN + bN + carry) / 2;
            sb.insert(0, curBinary);
        }
        return carry == 1 ? sb.insert(0, carry).toString() : sb.toString();


    }
    //sol 1. my sol. lots of code.
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int[] counter = new int[1];
        while(i >= 0 && j >= 0){
            int aN = Integer.parseInt(String.valueOf(a.charAt(i))), bN = Integer.parseInt(String.valueOf(b.charAt(j)));
            add(aN, bN, counter, sb);
            i--;
            j--;
        }

        while(i >= 0){
            int aN =  Integer.parseInt(String.valueOf(a.charAt(i)));
            add(aN, 0, counter, sb);
            i--;
        }

        while(j >= 0){
            int bN =  Integer.parseInt(String.valueOf(b.charAt(j)));
            add(bN, 0, counter, sb);
            j--;
        }
        return counter[0] == 1 ? sb.insert(0, 1).toString() : sb.toString();

    }
    private void add(int a, int b, int[] counter,  StringBuilder sb){
        if(a == 1 && b == 1){
            if(counter[0] == 1){
                sb.insert(0, 1);
            }else{
                sb.insert(0, 0);
                counter[0] = 1;
            }
        }else if(a == 1 || b == 1){
            if(counter[0] == 1){
                sb.insert(0, 0);
                counter[0] = 1;
            }else{
                sb.insert(0, 1);
            }
        }else{
            if(counter[0] == 1){
                sb.insert(0, 1);
                counter[0] = 0;
            }else{
                sb.insert(0, 0);
            }
        }
    }


}
