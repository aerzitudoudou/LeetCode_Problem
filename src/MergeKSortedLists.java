/**
 *
 *
 *
 *
 * Laicode 134
 * 134. Merge K Sorted Lists
 * Medium
 * Merge K sorted lists into one big sorted list in ascending order.
 *
 * Assumptions
 *
 * ListOfLists is not null, and none of the lists is null.
 *
 */






import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//my way
//T: O(kn * logk) k is the number of elements in listOfLists, n is the average number of listNodes in each row
//S: O(k)
public class MergeKSortedLists {

    public ListNode merge(List<ListNode> listOfLists) {
        if(listOfLists == null || listOfLists.size() == 0){
            return null;
        }
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(listOfLists.size(), new Comparator<Cell>(){
            @Override
            public int compare(Cell c1, Cell c2){
                if(c1.value == c2.value){
                    return 0;
                }
                return c1.value < c2.value ? -1 : 1;
            }
        });

        //initialize the heap
        //k
        for(int i = 0; i < listOfLists.size(); i++){
            if(listOfLists.get(i) != null){
                Cell tmp = new Cell(i, listOfLists.get(i).val);
                minHeap.offer(tmp);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        //kn
        while(minHeap.size() > 0){
            Cell tmp = minHeap.poll();
            int row = tmp.i;
            cur.next = listOfLists.get(row);
            listOfLists.set(row, listOfLists.get(row).next);
            cur = cur.next;
            if(listOfLists.get(row) != null){
                minHeap.offer(new Cell(row, listOfLists.get(row).val));
            }
        }

        //对比way2: minheap为空一定能保证所有的点都遍历完了，没有孤岛所以下面for loop可删掉不写
        for(int i = 0; i < listOfLists.size(); i++){
            if(listOfLists.get(i) != null){
                cur.next = listOfLists.get(i);
                break;
            }
        }
        return dummy.next;

    }
    //listnode 性质决定其可以找到下一个，没有必要记录所在行数。
    // 需要创建Cell是array情况，给一个array的element，没有信息说明它的下一位，这时候才需要记录下当前element 坐标，以便寻找当前位的下一个
    static class Cell{
        int i;
        int value;
        public Cell(int i, int value){
            this.i = i;
            this.value = value;
        }
    }


    //way 2: more element save ListNode in the heap directly, no need for helper class cell
    //T and S are same like above
    public ListNode merge2(List<ListNode> listOfLists) {
        if(listOfLists == null || listOfLists.size() == 0){
            return null;
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue(listOfLists.size(), new Comparator<ListNode>(){
            @Override
            public int compare(ListNode l1, ListNode l2){
                if(l1.val == l2.val){
                    return 0;
                }
                return l1.val < l2.val ? -1 : 1;
            }
        });

        //initialize the minheap
        for(ListNode listNode : listOfLists){
            if(listNode != null){
                minHeap.offer(listNode);
            }
        }
        ListNode dummy = new ListNode(0), cur = dummy;

        //loop through each element by the min heap
        while(!minHeap.isEmpty()){
            ListNode tmp = minHeap.poll();
            cur.next = tmp;
            if(cur.next.next != null){
                minHeap.offer(cur.next.next);
            }
            cur = cur.next;
        }
        return dummy.next;
    }



    //way 3: iterative, 时空复杂度分析，为什么不是最优解
    /**
     * list 1
     * list 2   ==> 2n
     * list 3       ==>3n
     * list 4          ==> 4n
     * ...
     * list k               ==>kn
     *
     * T = (2 + 3 + 4 + ....+ k)n = k^2 * n
     * S = O(1)
     *
     *
     *
     *
     */



    //way 4: binary reduction
    /**TODO: 写一下这个解法
     * list 1
     * list 2   ==> 2n
     * list 3
     * list 4   ==> 2n                   ==> 4n
     * list 5
     * list 6   ==> 2n
     * list 7
     * list 8   ==> 2n                   ==> 4n                 ==> 8n
     *             (k/2) * 2n= kn     (k/4) * 4n = kn        (k/8) * 8n = kn
     *
     * T = logk * kn  一共有logk层
     * S = O(1)
     *
     *
     *
     *
     */

}
