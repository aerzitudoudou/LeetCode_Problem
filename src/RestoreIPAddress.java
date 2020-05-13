import java.util.ArrayList;
import java.util.List;
/*
*
*147. Restore IP Addresses
Medium
Given a string containing only digits, restore it by retiring all possible valid IP address combinations.

Input:  ”25525511135”

Output: [“255.255.11.135”, “255.255.111.35”]
*
*["1.18.19.111", "1.18.191.11", "1.181.9.111", "1.181.91.11", "11.8.19.111", "11.8.191.11", "11.81.9.111", "11.81.91.11", "118.1.9.111", "118.1.91.11", "118.19.1.11", "118.19.11.1", "118.191.1.1"]
* ["1.18.19.111", "1.18.191.11", "1.181.9.111", "1.181.91.11", "11.8.19.111", "11.8.191.11", "11.81.9.111", "11.81.91.11", "118.1.9.111", "118.1.91.11", "118.19.1.11", "118.19.11.1"]
*
*
*
* */




public class RestoreIPAddress {

    //1. my way
    public List<String> restore(String ip) {
        StringBuilder pre = new StringBuilder();
        StringBuilder rem = new StringBuilder(ip);
        List<String> res = new ArrayList<>();
        dfs(pre, 0, res, rem);
        return res;

    }

    private void dfs(StringBuilder pre, int index, List<String> res, StringBuilder rem){
        //make sure rem is not empty
        if(index == 3){
            if(rem.length() > 0 && Integer.valueOf(rem.toString()) <= 255){
                if(rem.length() == 1 || (rem.length() > 1 && rem.charAt(0) != '0')){
                    res.add(pre.append(rem).toString());
                    pre.delete(pre.length() - rem.length(), pre.length());
                }
            }
            return;
        }
        if(rem.length() == 0){
            return;
        }
        //rem length is > 3
        //挪一个
        char c = rem.charAt(0);
        pre.append(c + ".");
        rem.deleteCharAt(0);
        dfs(pre, index + 1, res, rem);
        pre.delete(pre.length() - 2, pre.length());
        rem.insert(0, c);

        //挪两个
        if(c != '0' && rem.length() >= 2){
            String str = rem.substring(0, 2);
            pre.append(str + '.');
            rem.delete(0, 2);
            dfs(pre, index + 1, res, rem);
            pre.delete(pre.length() - 3, pre.length());
            rem.insert(0, str);
        }


        //挪三个
        if(c != '0' && rem.length() >= 3 && Integer.valueOf(rem.substring(0, 3)) <= 255){
            String str = rem.substring(0, 3);
            pre.append(str + '.');
            rem.delete(0, 3);
            dfs(pre, index + 1, res, rem);
            pre.delete(pre.length() - 4, pre.length());
            rem.insert(0, str);
        }
    }

    //TODO: 看答案更新解题报告

}
