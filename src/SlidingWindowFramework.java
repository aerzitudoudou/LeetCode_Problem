import java.util.Map;

public class SlidingWindowFramework {
    //sliding window 模板： https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/hua-dong-chuang-kou-tong-yong-si-xiang-jie-jue-zi-/
    public void slidingWindow(String s){
        Map<Character, Integer> window;
        int left = 0, right = 0;

        while(right < s.length()){
            //c is the character that will be added to the window
            char c = s.charAt(right);
            //extend the window to the right by 1
            right++;
            //do updates based on the window extension...
            // while(window needs shrink){
                //d is the character that will be removed out of the window
                char d = s.charAt(left);
                //shrink the window to the left by 1
                left++;
                //do updates based on the window shrink...
            }



    }

}
