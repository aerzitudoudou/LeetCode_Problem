import java.util.HashMap;
import java.util.Map;

public class TextToNumber {
    public String textToNum(String test){
        Map<String, String> map = new HashMap<>();

        map.put("One", "1");
        map.put("Two", "2");
        map.put("Eight", "8");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < test.length(); i++){
            if(Character.isUpperCase(test.charAt(i))){
                int j = i + 1;
                while(j < test.length() && !Character.isDigit(test.charAt(j)) && !Character.isUpperCase(test.charAt(j))){
                    j++;
                }
                String cur = test.substring(i, j);
                String curNum = map.get(cur);
                sb.append(curNum);
            }else if(Character.isDigit(test.charAt(i))){
                sb.append(test.charAt(i));
            }
        }
        return sb.toString();

    }




}



