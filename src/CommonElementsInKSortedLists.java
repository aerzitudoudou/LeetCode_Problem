import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CommonElementsInKSortedLists {
    //way 1: best way
    //iterative
    //T: O(kn)
    //S: O(n)
    public List<Integer> commonElementsInKSortedArrays(List<List<Integer>> input) {
        List<Integer> a = input.get(0);
        for(int i = 1; i < input.size(); i++){
            a = helper(a, input.get(i));
        }
        return a;

    }


    private List<Integer> helper(List<Integer> a, List<Integer> b){
        int i = 0, j = 0;
        List<Integer> res = new ArrayList<>();
        while(i < a.size() && j < b.size()){
            //这里Integer可以直接比，遇到不好直接比的，如果class implement了comparable, 则可以用compare判断优先级
            int compare = a.get(i).compareTo(b.get(j));
            if(compare == 0){
                res.add(a.get(i));
                i++;
                j++;
            }else if(compare < 0){
                i++;
            }else{
                j++;
            }

        }
        return res;
    }

    //way2: binary reduction
    /*

      size: k lists
    * list 1
    * list 2   ===> list 12
    * list 3
    * list 4   ===> list 34  ===> list 14
    * list 5
    * list 6   ===> list 56
    * list 7
    * list 8   ===> list 78  ===> list 58   ===> list 18
    *
    * T:             k/2 * n       k/4 * n        k/8 * n = (1/2 + 1/4 + 1/8) *kn = kn
    * S:             k/2 * n       k/4 * n        k/8 * n = (1/2 + 1/4 + 1/8) *kn = kn
    *
    *
    * */

    public List<Integer> commonElementsInKSortedArrays2(List<List<Integer>> input) {
        while(input.size() > 1){
            input = helper(input);
        }
        return input.get(0);
    }

    private List<List<Integer>> helper(List<List<Integer>> input){
        List<List<Integer>> res = new ArrayList<>();
        int size = input.size();
        if(size % 2 == 0){
            for(int i = 0; i < size; i += 2){
                res.add(findElement(input.get(i), input.get(i + 1)));
            }
        }else{
            for(int i = 0; i < size - 1; i += 2){
                res.add(findElement(input.get(i), input.get(i + 1)));
                res.add(input.get(size - 1));
            }

        }
        return res;

    }

    private List<Integer> findElement(List<Integer> a, List<Integer> b){
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0;
        while(i < a.size() && j < b.size()){
            int compare = a.get(i).compareTo(b.get(j));
            if(compare == 0){
                res.add(a.get(i));
                i++;
                j++;
            }else if(compare < 0){
                i++;
            }else{
                j++;
            }

        }
        return res;
    }


    //way 3: min heap: 找相同element这个方法麻烦且复杂度高。不推荐。 merge k sorted list 是推荐方法，注意变通
    /*
    list 1
    list 2
    ...
    list k
    * min heap size = k store elements from each list
    * initialization: heap = {list1(0), list2(0)....listk(0)}
                      max = max(list1(0), list2(0)....listk(0))

    each cell is (i, j) in the matrix
    row number = k
    * algo:

    while(heap.size() == k)
      if(heap.peek() === max)
               res.add(heap.peek())
               pop all elements in heap
               clear max
               for each i belonging to [0, k - 1]
                   j++
                   if(j < list i .size)
                        heap.offer(list.get(j))
                        update max
      else
                pop()
                add next element from the same list as the popped element
                update max

    T: number of lists k * number of elements in each list n * element operation through heap log k = nk* logk
    S: O(k)

    * */
    static class MyCell{
        int i;
        int j;
        int value;
        public MyCell(int i, int j, int value){
            this.i = i;
            this.j = j;
            this.value = value;
        }
    }
    public List<Integer> commonElementsInKSortedArrays3(List<List<Integer>> input) {
        List<Integer> res = new ArrayList<>();
        int rowNum = input.size();
        PriorityQueue<MyCell> minHeap = new PriorityQueue<>(rowNum, new Comparator<MyCell>(){
            @Override
            public int compare(MyCell c1, MyCell c2){
                if(c1.value == c2.value){
                    return 0;
                }
                return c1.value < c2.value ? -1 : 1;
            }
        });
        int max = Integer.MIN_VALUE;
        //initialize the heap
        for(int i = 0; i < rowNum; i++){
            //corner case check:
            if(input.get(i).size() == 0){
                return res;

            }
            MyCell c = new MyCell(i, 0, input.get(i).get(0));
            minHeap.offer(c);
            max = Math.max(max, c.value);
        }

        while(minHeap.size() == rowNum){
            int cur = minHeap.peek().value;
            if(cur == max){
                res.add(cur);
                List<MyCell> tmpList = new ArrayList<>();
                while(minHeap.size() > 0){
                    MyCell tmp = minHeap.poll();
                    tmpList.add(tmp);
                }
                //clear max
                max = Integer.MIN_VALUE;
                for(int i = 0; i < tmpList.size(); i++){
                    MyCell tmp = tmpList.get(i);
                    int row = tmp.i;
                    int column = tmp.j;
                    int value = tmp.value;
                    //把当前MyCell 的下一个MyCell加到heap 里
                    if(column + 1 < input.get(row).size()){
                        minHeap.offer(new MyCell(row, column + 1, input.get(row).get(column + 1)));
                        max = Math.max(max, input.get(row).get(column + 1));

                    }

                }
            }else{
                MyCell tmp = minHeap.poll();
                int row = tmp.i;
                int column = tmp.j;
                int value = tmp.value;
                //把当前MyCell 的下一个MyCell加到heap 里
                if(column + 1 < input.get(row).size()){
                    minHeap.offer(new MyCell(row, column + 1, input.get(row).get(column + 1)));
                    max = Math.max(max, input.get(row).get(column + 1));
                }

            }

        }
        return res;
    }


}
