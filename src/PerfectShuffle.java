import java.util.Random;
/*
*
* Laicode
*
* 108. Perfect Shuffle
Medium
Given an array of integers (without any duplicates), shuffle the array such that all permutations are equally likely to be generated.

Assumptions

The given array is not null
*
*
* -permutation 每一种出现概率为1/N!
*
*
* */


public class PerfectShuffle {
    public void shuffle(int[] array) {
        Random rand = new Random();
        for(int i = 0; i < array.length; i++){
            //从[i, array.length - 1] index里随机选一个数，跟当前的index i交换   [a, b]生成随机数公式： a + rand.nextInt(b - a + 1)
            int index = i + rand.nextInt(array.length - i);
            swap(array, index, i);
        }
        return;
    }

    private void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
