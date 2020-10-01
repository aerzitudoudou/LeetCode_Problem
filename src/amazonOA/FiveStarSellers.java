package amazonOA;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//https://leetcode.com/discuss/interview-question/854110/


/*
* use max heap for storing the ratings as to naturally remain the order of pair by giving highest priority to the pair with biggest percentage change when added 5 star rating by 1.
* in each run, we find the biggest percentage change within the productRatings when adding one 5 star rating by poping from the maxheap,
* then push it back to the maxheap with updated review numbers for the next round of pop.
* keeps doing this while updating the new rating percentage sum  until it exceeds the ratingsThreshold that was gaven.
* then number of times we do (pop, push) against the maxheap is therefore the number of reviews needed.
 * */

//T: O(nlogn) where n the is size of the list of productRatings
//S: O(n)
public class FiveStarSellers {
    public int fiveStarReviews(List<List<Integer>> productRatings, int ratingsThreshold){
        int num = 0;
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((l1, l2) -> diff(l2) - diff(l1)); // max-heap.
        for(List<Integer> rating : productRatings) {// initialize PriorityQueue.
            pq.offer(rating);
        }
        double curRating = 0;
        for(List<Integer> rating : productRatings) {
            curRating += 100.0 * rating.get(0) / rating.get(1); // initialize curRating.
        }
        while(curRating < ratingsThreshold * productRatings.size()) {
            num++;
            List<Integer> rating = pq.poll();
            List<Integer> newRating = Arrays.asList(rating.get(0)+1, rating.get(1)+1);
            curRating = curRating - 100.0 * rating.get(0) / rating.get(1) + 100.0 * newRating.get(0) / newRating.get(1); // keep updating the rating.
            pq.offer(newRating);
        }
        return num;
    }

    // the diff between the current and (cur + 1) 5 star percentage
    private int diff(List<Integer> p) {
        double curRate = 100.0 * p.get(0) / p.get(1);
        double newRate = 100.0 * (p.get(0) + 1) / (p.get(1) + 1);
        int res = (int)(newRate - curRate);
        return res;
    }

}
