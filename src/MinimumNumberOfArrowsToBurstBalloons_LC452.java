import java.util.Arrays;

public class MinimumNumberOfArrowsToBurstBalloons_LC452 {
    public int findMinArrowShots(int[][] points) {
        int size = points.length;
        //!!careful about case  [[-2147483646,-2147483645],[2147483646,2147483647]], a[1] - b[1] = -2147483645 - 2147483647 = 4 integer overflow
        Arrays.sort(points, (a, b) -> {
            if(a[1] == b[1]) return 0;
            else if(a[1] < b[1]) return -1;
            else return 1;
        });


        int res = 1;
        int r = points[0][1];
        for(int i = 1; i < size; i++){


            if(points[i][0] > r){
                r = points[i][1];
                res++;
            }
        }

        return res;
    }

}
