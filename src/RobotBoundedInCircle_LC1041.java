public class RobotBoundedInCircle_LC1041 {
    //!!!sol1, from Lee, O(n), O(1)
    //after one round, not facing north or return to (0,0) is a cycle trajectory
    public boolean isRobotBounded(String str) {
        int[] point = new int[2];
        // 0  1      2     3
        // up right  down  left
        int[] dirX = {0,1,0,-1};
        int[] dirY = {1,0,-1,0};

        int dir = 0;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            //[] or LL
            if(c == 'L'){
                dir = (dir + 3) % 4;
            }else if(c == 'R'){
                dir =(dir + 1) % 4;
            }else{
                point[0] += dirX[dir];
                point[1] += dirY[dir];
            }

        }

        return (point[0] == 0 && point[1] == 0) || (dir != 0);

    }
}
