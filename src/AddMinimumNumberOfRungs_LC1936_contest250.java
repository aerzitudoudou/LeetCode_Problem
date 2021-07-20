public class AddMinimumNumberOfRungs_LC1936_contest250 {


    public int addRungs(int[] rungs, int dist) {
        int count = 0;
        int cur = 0;
        for(int i = 0; i < rungs.length; i++){
            count += (rungs[i] - cur - 1) / dist;
            cur = rungs[i];

        }

        return count;
    }
}
