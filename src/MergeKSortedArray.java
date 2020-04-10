import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedArray {
    public int[] merge(int[][] arrayOfArrays) {
        //caculate the number of elements in sum in the [][]
        int rowSize = arrayOfArrays.length;
        int totalNum = 0;

        //populate priority queue with rowSize number of element
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(rowSize, new Comparator<Cell>(){
            @Override
            public int compare(Cell c1, Cell c2){
                if(c1.value == c2.value){
                    return 0;
                }
                return c1.value < c2.value ? -1 : 1;
            }
        });

        for(int i = 0; i < rowSize; i++){
            if(arrayOfArrays[i].length > 0){
                minHeap.offer(new Cell(i, 0, arrayOfArrays[i][0]));
            }
            int columnSize = arrayOfArrays[i].length;
            totalNum += columnSize;
        }
        int[] result = new int[totalNum];
        for(int i = 0; i < totalNum; i++){
            Cell cell = minHeap.poll();
            result[i] = cell.value;
            int row = cell.row;
            int column = cell.column;
            if(column < arrayOfArrays[row].length - 1){
                minHeap.offer(new Cell(row, column + 1, arrayOfArrays[row][column + 1]));
            }
        }

        return result;
    }
}

class Cell{
    int row;
    int column;
    int value;
    public Cell(int row, int column, int value){
        this.row = row;
        this.column = column;
        this.value = value;
    }
}
