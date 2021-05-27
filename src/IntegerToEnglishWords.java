public class IntegerToEnglishWords {
    //lc 273
    //from https://www.youtube.com/watch?v=qwotMTggot0
    //除模，注意在sb前方插入的写法sb.insert(0, str)
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
    private final String[] LESS_THAN_TWENTY = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] LESS_THAN_HUNDRED = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};


    public String numberToWords(int num) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        if(num == 0) return "Zero";
        while(num > 0){
            if (num % 1000 != 0) {
                int tmp = num % 1000;
                StringBuilder tmpSb = new StringBuilder();
                helper(tmp, tmpSb);
                sb.insert(0, tmpSb.append(THOUSANDS[index]).append(" "));

            }
            num /= 1000;
            index++;

        }


        return sb.toString().trim();
    }

    private void helper(int tmp, StringBuilder sb) {
        if (tmp == 0) {
            return;
        } else if (tmp < 20) {
            sb.append(LESS_THAN_TWENTY[tmp]).append(" ");
        } else if (tmp < 100) {
            sb.append(LESS_THAN_HUNDRED[tmp / 10]).append(" ");
            helper(tmp % 10, sb);
        } else {
            helper(tmp / 100, sb);
            sb.append("Hundred").append(" ");
            helper(tmp % 100, sb);
        }
    }
}
