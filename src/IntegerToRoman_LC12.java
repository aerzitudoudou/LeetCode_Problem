import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;

public class IntegerToRoman_LC12 {
    //!!!!!!sol2, from acwing https://www.acwing.com/video/1327/
    public String intToRoman1(int num) {
        StringBuilder sb = new StringBuilder();
        int[] ary = {1,4,5,9,10,40,50,90,100,400,500,900,1000};
        String[] rep = {"I","IV","V","IX","X","XL","L", "XC","C","CD","D","CM","M"};
        for(int i = ary.length - 1; i >= 0; i--){
            while(num - ary[i] >= 0){
                sb.append(rep[i]);
                num-=ary[i];
            }
        }
        return sb.toString();
    }

    //sol1, my, complecated...
    public String intToRoman(int num) {
        Map<Integer, String> map = new HashMap<>(){{
            put(1, "I");
            put(4, "IV");
            put(5, "V");
            put(9, "IX");
            put(10, "X");
            put(40, "XL");
            put(50, "L");
            put(90, "XC");
            put(100, "C");
            put(400, "CD");
            put(500, "D");
            put(900, "CM");
            put(1000, "M");
        }};

        int[] ary = {1,4,5,9,10,40,50,90,100,400,500,900,1000};

        StringBuilder sb = new StringBuilder();
        int i = 1;
        while(num != 0){
            int tmp = num % 10;
            int cur = tmp * i;
            //do something with cur and generate the string
            sb.insert(0, getRoman(cur, map, ary));
            i *= 10;
            num /= 10;
        }

        return sb.toString();
    }


    private String getRoman(int cur, Map<Integer, String> map, int[] ary){
        StringBuilder sb = new StringBuilder();
        while(cur != 0){
            int l = 0, r = ary.length - 1;
            while(l < r){
                int mid = l + (r - l + 1) /2;
                if(ary[mid] <= cur){
                    l = mid;
                }else r = mid - 1;
            }

            //ary[r] is the lartest number that is equal or smaller than the cur number
            int tmp = ary[r];
            int count = 0;
            while(cur - tmp >= 0){
                cur -= tmp;
                count++;
            }
            for(int i = 0; i < count; i++){
                sb.append(map.get(tmp));
            }
        }

        return sb.toString();
    }
}
