public class LargestNumberAfterMutatingSubstring_LC1946 {
    //sol1, my, O(n), O(1)
    public String maximumNumber(String num, int[] change) {
        StringBuilder res = new StringBuilder();
        int i = 0;
        boolean changed = false;
        boolean visited = false;
        while(i < num.length()){

            while(i < num.length() && (change[num.charAt(i) - '0'] <= num.charAt(i) - '0' || changed)){
                res.append(String.valueOf(num.charAt(i) - '0'));
                i++;
            }
            while(i < num.length() && change[num.charAt(i) - '0'] >= num.charAt(i) - '0' && !changed){
                res.append(String.valueOf(change[num.charAt(i) - '0']));
                i++;
                visited = true;
            }
            changed = visited;


        }


        return res.toString();

    }

}
