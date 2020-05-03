/*
* Description

TinyURL is a URL shortening service where you enter a URL such as https://lintcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

Have you met this question in a real interview?
Example
Example 1:

Input："https://lintcode.com/problems/design-tinyurl"
Output：http://tinyurl.com/4e9iAk
Explanation：encode and decode by your own algorithm.
Example 2:

Input："https://lintcode.com/problems/solution"
Output：http://tinyurl.com/5d7fiu
Explanation：encode and decode by your own algorithm.
*
*
*
*
* */

import java.util.*;

public class TinyUrl {
    //sol 1 my way url -> incremented number
    //use arraylist as map for <index(shorturl), longurl>

    /*
    *
    * 1. long --> short one to many , short --> long many to one
    * 2. limit to the size of int(2147483647), overflow will degrade the performance
    * 3. security concern, others know how many url stored
    * 4. the length is no always shorter
    *
    * */
    private int count = 0;
    private List<String> longUrlList = new ArrayList<>();

    public String encode(String longUrl) {
        String encodedStr = String.valueOf(count);
        longUrlList.add(longUrl);
        count++;
        return encodedStr;
    }


    public String decode(String shortUrl) {
        return longUrlList.get(Integer.valueOf(shortUrl));
    }

    //solution 2
    Map<String, String> longToShort = new HashMap<>();
    Map<String, String> shortToLong = new HashMap<>();


    private String generateShortUrl(){
        Random rand = new Random();
        int randInt = rand.nextInt();
        //rand to 6  chars
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 6; i++){
            sb.append((char)(randInt % 62));
            randInt /= 62;
        }
        return sb.toString();

    }
    //assume long have a-zA-Z0-9 total 62 chars
    public String encode2(String longUrl) {
        if(longToShort.containsKey(longUrl)){
            return longToShort.get(longUrl);
        }
        String res = generateShortUrl();
        while(shortToLong.containsKey(res)){
            res = generateShortUrl();
        }
        shortToLong.put(res, longUrl);
        longToShort.put(longUrl, res);
        return res;
    }


    public String decode2(String shortUrl) {
        return shortToLong.get(shortUrl);
    }







}
