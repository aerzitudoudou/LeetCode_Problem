import java.util.Arrays;

public class ReorderDataInLogFiles_LC937 {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (l1, l2) ->{
            int idx1 = l1.indexOf(' '), idx2 = l2.indexOf(' ');
            String str1 = l1.substring(idx1 + 1), str2 = l2.substring(idx2 + 1);
            boolean str1IsDigit = Character.isDigit(str1.charAt(0)), str2IsDigit = Character.isDigit(str2.charAt(0));
            if(!str1IsDigit && !str2IsDigit){
                int comp = str1.compareTo(str2);
                if(comp == 0) return l1.substring(0, idx1).compareTo(l2.substring(0, idx2));
                else return comp;
            }else if(str1IsDigit && str2IsDigit){
                return 0;
            }else if(str1IsDigit && !str2IsDigit){
                return 1;
            }else{
                return -1;
            }
        });

        return logs;



    }

}
