public class Atoi {
    //sol1: tricky , less code , hard to remember
    public int myAtoi(String s) {
        int res = 0;
        if(s == null || s.length() ==0) {
            return res;
        }
        int sign = 1;
        char[] charAry = s.toCharArray();
        int l = charAry.length;
        int i = 0;

        //remove leading spaces
        while(i < l && charAry[i] == ' ' ){
            i++;
        }
        if(i < l){
            if(charAry[i] == '-'){
                i++;
                sign = -1;
            }else if(charAry[i] == '+'){
                i++;
            }else if(!Character.isDigit(charAry[i])){
                return 0;
            }
        }
        /*
        assume -123, 122



        */
        while(i < l && Character.isDigit(charAry[i])){
            //背诵 如何判断出没出界
            //                                      [123, +inf) or (-inf, -123]
            if(res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && charAry[i] - '0' > Integer.MAX_VALUE % 10)){
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            }
            res = res * 10 + Integer.valueOf(charAry[i++] - '0');

        }


        return res * sign;


    }

    //sol 2: 更好记 分类讨论 负数 = res * 10 - (char[i] - '0')
    public int myAtoi2(String s) {
        int res = 0;
        if(s == null || s.length() ==0) {
            return res;
        }
        int sign = 1;
        char[] charAry = s.toCharArray();
        int l = charAry.length;
        int i = 0;

        //remove leading spaces
        while(i < l && charAry[i] == ' ' ){
            i++;
        }
        if(i < l){
            if(charAry[i] == '-'){
                i++;
                sign = -1;
            }else if(charAry[i] == '+'){
                i++;
            }else if(!Character.isDigit(charAry[i])){
                return 0;
            }
        }
        /*
        assume -123, 122



        */
        while(i < l && Character.isDigit(charAry[i])){
            if(sign == 1){
                /*
                    122

                    res > 12 --> bad
                    res == 12 && charAry[i] - '0' > MAX % 10 --> bad
                    res < 12  --> 11(?from 1 - 9) good


                */
                if(res > Integer.MAX_VALUE / 10 || res == Integer.MAX_VALUE / 10 && charAry[i] - '0' > Integer.MAX_VALUE % 10){
                    return Integer.MAX_VALUE;
                }
                res = res * 10 + charAry[i++] - '0';
            }else{
                /*
                -123

                -123 % 10 = -3

                res < -12 --> bad e.g. -13
                res == -12 && charAry[i] - '0' > |MIN % 10|  --> bad e.g. -124
                res > -12 --> good e.g. -11

                java： 除法 / 结果永远是最终的符号
                       mod % 结果永远和被除数保持一致 e.g. -123 % 10 = -3   -123 % -10 = -3 所以要去绝对值
                */
                if(res < Integer.MIN_VALUE / 10 || res == Integer.MIN_VALUE / 10 && charAry[i] - '0' > Math.abs(Integer.MIN_VALUE % 10) ){
                    return Integer.MIN_VALUE;
                }
                res = res * 10 - (charAry[i++] - '0');
            }

        }


        return res;

    }


}
