//leetcode 621
public class TaskScheduler {
    /*

     Lintcode 945
     Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order.
     Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

     However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

     You need to return the least number of intervals the CPU will take to finish all the given tasks.

     Example
     Example1

     Input: tasks = ['A','A','A','B','B','B'], n = 2
     Output: 8
     Explanation:
     A -> B -> idle -> A -> B -> idle -> A -> B.
     Example2

     Input: tasks = ['A','A','A','B','B','B'], n = 1
     Output: 6
     Explanation:
     A -> B -> A -> B -> A -> B.
     Notice
     The number of tasks is in the range [1, 10000].
     The integer n is in the range [0, 100].
     *
     *
     *
     *
    /
    是一道数学题。一开始没想出来。看答案的。https://www.cnblogs.com/grandyang/p/7098764.html

     */

    public int leastInterval(char[] tasks, int n) {
        int[] counter = new int[26];
        for(int i = 0; i < tasks.length; i++){
            counter[tasks[i] - 'A']++;
        }
        int max = 0; //字母出现的最多的次数
        int letterNum = 0; //出现最多次数字母的个数
        for(int i = 0; i < counter.length; i++){
            if(counter[i] > max){
                max = counter[i];
                letterNum = 1;
            }else if(counter[i] == max){
                letterNum++;
            }
        }

        //corner case, AAABBB, n = 0    按照公式 = 4 , 这种情况要和原数组的长度比较，取较大值
        return Math.max(tasks.length, (max - 1) * (n + 1) + letterNum);
    }

    //O(n) O(1)
    public int leastInterval2(char[] tasks, int n) {
        int[] charNum = new int[26];
        int max = 0;
        for(char ch : tasks){
            charNum[ch - 'A']++;
            max = Math.max(charNum[ch - 'A'], max);
        }

        int maxCount = 0;
        for(int a : charNum){
            if(a == max){
                maxCount++;
            }
        }

        return Math.max(tasks.length, (max - 1) * (n + 1) + maxCount);

    }


}
