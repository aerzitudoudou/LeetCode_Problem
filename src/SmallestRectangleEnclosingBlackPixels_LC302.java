import java.util.Deque;
import java.util.LinkedList;

public class SmallestRectangleEnclosingBlackPixels_LC302 {
    //sol1.0, from https://www.jiuzhang.com/solution/smallest-rectangle-enclosing-black-pixels/
    //O(mlogn + nlogm), O(1)
    public int minArea(char[][] image, int x, int y) {
        int row = image.length, col = image[0].length;
        //left: [0,y], right[y, col - 1], up: [0, x], down:[x, row - 1]
        int left = findLeft(image, 0, y);
        int right = findRight(image, y, col - 1);
        int up = findUp(image, 0, x);
        int down = findDown(image, x, row - 1);

        return (right - left + 1 ) * (down - up + 1);
    }

    private int findLeft(char[][] image, int l, int r){
        while(l < r){
            int mid = l + (r - l) / 2;
            if(validColumn(image, mid)){
                r = mid;
            }else l = mid + 1;
        }

        return r;
    }



    private int findRight(char[][] image, int l, int r){
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(validColumn(image, mid)){
                l = mid;
            }else r = mid - 1;
        }

        return r;
    }


    private int findUp(char[][] image, int l, int r){
        while(l < r){
            int mid = l + (r - l) / 2;
            if(validRow(image, mid)){
                r = mid;
            }else l = mid + 1;
        }

        return r;
    }


    private int findDown(char[][] image, int l, int r){
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(validRow(image, mid)){
                l = mid;
            }else r = mid - 1;
        }

        return r;
    }


    private boolean validColumn(char[][] image, int col){
        for(int i = 0; i < image.length; i++){
            if(image[i][col] == '1') return true;
        }

        return false;
    }

    private boolean validRow(char[][] image, int row){
        for(int i = 0; i < image[0].length; i++){
            if(image[row][i] == '1') return true;
        }

        return false;
    }



    //!!!sol1,1, optimized code, O(mlogn + nlogm)
    public int minArea1(char[][] image, int x, int y) {
        int row = image.length, col = image[0].length;
        //left: [0,y], right[y, col - 1], up: [0, x], down:[x, row - 1]
        int left = findSmaller(image, 0, y, true);
        int right = findLarger(image, y, col - 1, true);
        int up = findSmaller(image, 0, x, false);
        int down = findLarger(image, x, row - 1, false);

        return (right - left + 1 ) * (down - up + 1);
    }

    private int findSmaller(char[][] image, int l, int r, boolean isValidColumn){
        boolean flag;
        while(l < r){
            int mid = l + (r - l) / 2;
            flag = isValidColumn ? validColumn(image, mid) : validRow(image, mid);
            if(flag){
                r = mid;
            }else l = mid + 1;
        }

        return r;
    }



    private int findLarger(char[][] image, int l, int r, boolean isValidColumn){
        boolean flag;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            flag = isValidColumn ? validColumn(image, mid) : validRow(image, mid);
            if(flag){
                l = mid;
            }else r = mid - 1;
        }

        return r;
    }


    //sol2, dfs, O(mn), O(mn)
    int left = Integer.MAX_VALUE, right = -1, up = Integer.MAX_VALUE, down = -1;

    public int minArea2(char[][] image, int x, int y) {
        search(image, x, y);

        return (right - left + 1) * (down - up + 1);

    }

    private void search(char[][] image, int x, int y){
        if(!isValid(image, x, y) || image[x][y] == '0') return;

        left = y < left ? y : left;
        right = y > right ? y : right;
        up = x < up ? x : up;
        down = x > down ? x : down;
        //!!!remember to mark visited coordinate!
        image[x][y] = '0';

        search(image, x - 1, y);
        search(image, x + 1, y);
        search(image, x, y - 1);
        search(image, x, y + 1);


    }

    private boolean isValid(char[][] image, int x, int y){
        int m = image.length, n = image[0].length;
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    //sol3, bfs, O(mn), O(mn)
    public int minArea3(char[][] image, int x, int y) {
        bfs(image, x, y);
        return (right - left + 1) * (down - up + 1);
    }


    private void bfs(char[][] image, int x, int y){
        Deque<int[]> queue = new LinkedList<>();
        queue.offerFirst(new int[]{x,y});
        image[x][y] = '0';

        while(!queue.isEmpty()){

            int[] cur = queue.pollLast();
            int curX = cur[0], curY = cur[1];
            left = curY < left ? curY : left;
            right = curY > right ? curY : right;
            up = curX < up ? curX : up;
            down = curX > down ? curX : down;

            int[] dirX = {-1,0,1,0};
            int[] dirY = {0,1,0,-1};

            for(int a = 0; a < 4; a++){
                curX = cur[0] + dirX[a];
                curY = cur[1] + dirY[a];
                if(isValid(image, curX, curY) && image[curX][curY] == '1'){

                    image[curX][curY] = '0';
                    queue.offerFirst(new int[]{curX, curY});
                }
            }
        }
    }



}
