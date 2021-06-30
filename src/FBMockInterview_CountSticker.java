/*

Facebook logo stickers cost $2 each from the company store. I have an idea.
I want to cut up the stickers, and use the letters to make other words/phrases.
A Facebook logo sticker contains only the word 'facebook', in all lower-case letters.

Write a function that, given a string consisting of a word or words made up
of letters from the word 'facebook', returns an integer with the number of
stickers I will need to buy.

get_num_stickers('coffee kebab') -> 3  f f e e e
get_num_stickers('book') -> 1
get_num_stickers('boooook') -> 3

You can assume the input you are passed is valid, that is, does not contain
any non-'facebook' letters, and the only potential non-letter characters

in the string are spaces.
*/


import java.util.HashMap;
import java.util.Map;

public class FBMockInterview_CountSticker {
    //sol1, my, O(m + n), O(m + n) m length of input str, n: length of facebook
    public int calculate(String str){
        Map<Character, Integer> inputMap = new HashMap<>();
        for(char c : str.toCharArray()){
            if(c != ' '){
                Integer value = inputMap.getOrDefault(c, 0);
                value++;
                inputMap.put(c, value);
            }

        }

        Map<Character, Integer> facebookMap = new HashMap<>();
        for(char c : "facebook".toCharArray()){
            Integer value = facebookMap.getOrDefault(c, 0);
            value++;
            facebookMap.put(c, value);
        }

        int res = 0;
        for(Map.Entry<Character, Integer> entry : inputMap.entrySet()){
            char c = entry.getKey();
            //res = 6
            //1 + 1 + 1
            //curCount = 1 count = 3 res = 3    count % curCount = 0  count / curCount= 3
            int count = entry.getValue(); //3

            int curCount = facebookMap.get(c); //1

            if(curCount >= count){
                res = Math.max(res, 1);
            }else{
                int cur = count % curCount == 0 ? count / curCount : count / curCount + 1;
                res = Math.max(res, cur);
            }
        }

        return res;
    }


    //todo: sol without divide
}
