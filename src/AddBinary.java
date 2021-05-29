public class AddBinary {
    //lc 67
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
