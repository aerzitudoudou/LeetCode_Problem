public class CopyBooks_Lint437 {
    //sol1, from https://blog.csdn.net/weixin_50899769/article/details/116237663?spm=1001.2101.3001.6650.2&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-2.pc_relevant_aa&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-2.pc_relevant_aa&utm_relevant_index=5
    //O(logm * n), O(1), m = MaxTime - minTime, n is pages' length
    public int copyBooks(int[] pages, int k) {
        int minTime = 0, maxTime = 0;
        //time range
        for(int i = 0; i < pages.length; i++){
            minTime = Math.max(pages[i], minTime);
            maxTime += pages[i];
        }

        //run binary search on the time range, find smallest time limit beyond which k people can copy all pages
        //2 parts: time range that k people can't copy within the time --> time range that k people can copy
        int l = minTime, r = maxTime;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(canCopy(pages, mid, k)){
                r = mid;
            }else l = mid + 1;
        }
        return r;
    }

    //greedy, as long as the current total pages/time > timeLimit, need a new person, find at least how many persons is needed to finish the pages within given timeLimit
    //O(n)
    private boolean canCopy(int[] pages, int timeLimit, int k){
        int personTotalPage = 0, personNum = 0;
        for(int page : pages){
            if(page > timeLimit) return false;
            personTotalPage += page;
            if(personTotalPage > timeLimit){
                personNum++;
                personTotalPage = page;
            }
        }
        return personNum + 1 <= k;

    }
}
