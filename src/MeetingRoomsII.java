//leetcode 253

import java.util.Arrays;

public class MeetingRoomsII {
        /*

     0                   30
         5  10
                 15   20

     inOrder[i][0]: the number
     intOrder[i][1]: start or end
     (0,0) (5,0) (10, 1) (15, 0) (20,1) (30,1)

     -->
     find global max of cur
     if start
     cur++
     if end
     cur--
    */
    //Sol1: sort in a customized class, when meet start, cur++, when meet end, cur-- find max cur .
    //be aware of start == end case. doesn't need an extra meeting room, first cur-- then cur++
    //T: nlogn S: n
//     public class TimePoint{
//         int time;
//         int isStart;
//         public TimePoint(int time, int isStart){
//             this.time = time;
//             this.isStart = isStart;
//         }
//     }
//     public int minMeetingRooms(int[][] intervals) {
//         TimePoint[] ary = new TimePoint[intervals.length * 2];
//         int a = 0;
//         for(int i = 0; i < intervals.length; i++){
//             ary[a++] = new TimePoint(intervals[i][0], 0);
//             ary[a++] = new TimePoint(intervals[i][1], 1);

//         }

//         Arrays.sort(ary, (TimePoint t1, TimePoint t2) -> {
//             if(t1.time == t2.time){
//                 if(t1.isStart == t2.isStart){
//                     return 0;
//                 }
//                 return t1.isStart < t2.isStart ? 1 : -1;
//             }
//             return t1.time < t2.time ? -1 : 1;

//         });

//         int max = 0, cur = 0;
//         for(int i = 0; i < intervals.length * 2; i++){
//             if(ary[i].isStart == 0){
//                 cur++;

//             }else{
//                 cur--;
//             }
//             max = Math.max(max, cur);
//         }
//         return max;

//     }

    //sol2: see explaination https://i.loli.net/2018/09/24/5ba81e5ea9d15.jpg and https://i.loli.net/2018/09/24/5ba81e7c04aee.jpg

    /*sort start and end separately, two pointer.i for start, j for end
    start < end need new room i++ room++
    start >= end doesn't need new room because we know one of the previous meeting will end at this end time and reuse the room, j++
    T: nlogn S: n
    */
    public int minMeetingRooms(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        //find start and end
        for(int i = 0; i < intervals.length; i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int j = 0, room = 0;
        for(int i = 0; i < start.length; i++){
            if(start[i] < end[j]){
                room++;
            }else{
                j++;
            }
        }
        return room;

    }
}
