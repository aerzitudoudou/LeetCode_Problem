import java.util.Deque;
import java.util.LinkedList;

public class MovingAverageFromDataStream_LC346 {
    private Deque<Integer> queue;
    private int size;
    private double sum;

    public MovingAverageFromDataStream_LC346(int size) {
        queue = new LinkedList<Integer>();
        this.size = size;
        sum = 0;

    }

    //T:O(1), S:O(n)
    public double next(int val) {
        if (queue.size() == size) {
            sum -= queue.pollLast();
        }
        queue.offerFirst(val);
        sum += val;
        return sum / queue.size();
    }
}
