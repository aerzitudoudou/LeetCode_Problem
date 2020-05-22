/*
* fb 面试题
* design一个minheap
*
*
*
* */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//key points:
//a. parent = (child - 1) / 2
//b. leftChild = parent * 2 + 1
//c. rightChild = parent * 2 + 2
public class MinHeap {
    List<Integer> list = new ArrayList<>();
    int size;
    public MinHeap(List<Integer> list){
        this.list = list;
        this.size = list.size();
        heapify();
    }

    private void heapify(){
        for(int i = size / 2 - 1; i >= 0; i--){
            perculateDown(i);
        }

    }

    public int size(){
        return size;
    }

    public void offer(int ele){
        list.add(ele);
        size++;
        perculateUp(size - 1);
    }

    public int peek(){
        if(size > 0){
            return list.get(0);
        }
        return Integer.MAX_VALUE;

    }

    public boolean isEmpty(){
        return size > 0 ? false : true;
    }



    public int poll(){
        int res = Integer.MAX_VALUE;
        if(size > 0){
            res = list.get(0);
            list.set(0, list.get(size - 1));
            list.remove(size - 1);
            size--;
            perculateDown(0);
        }
        return res;
    }

    private void perculateDown(int index){
        //右下第一个不是叶子节点的求法
        while(index <= size / 2 - 1){
            int leftChild = index * 2 + 1;
            int rightChild = index * 2 + 2;
            int swapCandidate = leftChild;
            if(rightChild <= size - 1 && list.get(rightChild) < list.get(leftChild)){
                swapCandidate = rightChild;
            }
            if(list.get(index) > list.get(swapCandidate)){
                Collections.swap(list, index, swapCandidate);
            }
            index = swapCandidate;
        }


    }


    private void perculateUp(int index){
        while(index >= 1){
            //parent 坐标求法
            int parent = (index - 1) / 2;
            if(list.get(parent) > list.get(index)){
                Collections.swap(list, parent, index);
            }
            index = parent;
        }

    }
}
