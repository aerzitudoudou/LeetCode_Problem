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
* - 大量的StringBuilder操作
* - 合理利用offset记录cur指针在原ip上的位置
*
*
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
        //base case
        if(index == 4 && rem.length() == 0){
            res.add(pre.substring(0, pre.length() - 1));
            return;
        }

        //挪一个


        if(rem.length() >= 1){
            char c = rem.charAt(0);
            pre.append(c + ".");
            rem.deleteCharAt(0); //因为加的动作在recursion call 前面，所以 index = 0时候对应的是recursion tree叉出来的第一层，一个doc已经加好。同理 Index = 3时候表示3个doc已经加好，index = 4 时候就是base case
            dfs(pre, index + 1, res, rem);
            pre.delete(pre.length() - 2, pre.length());
            rem.insert(0, c);
        }


        //挪两个
        if(rem.length() >= 2 && rem.charAt(0) != '0'){
            String str = rem.substring(0, 2);
            pre.append(str + '.');
            rem.delete(0, 2);
            dfs(pre, index + 1, res, rem);
            pre.delete(pre.length() - 3, pre.length());
            rem.insert(0, str);
        }


        //挪三个
        if(rem.length() >= 3 && rem.charAt(0) != '0' && Integer.valueOf(rem.substring(0, 3)) <= 255){
            String str = rem.substring(0, 3);
            pre.append(str + '.');
            rem.delete(0, 3);
            dfs(pre, index + 1, res, rem);
            pre.delete(pre.length() - 4, pre.length());
            rem.insert(0, str);
        }
    }

    //way 2: 用offset记录当前指针的位置
    public List<String> restore2(String ip) {
        StringBuilder pre = new StringBuilder();
        StringBuilder ipSb = new StringBuilder(ip);
        List<String> res = new ArrayList<>();
        dfs2(pre, 0, 0,  res, ipSb);
        return res;

    }
    //               global variable    level     which index cur is at
    private void dfs2(StringBuilder pre, int index, int offset, List<String> res, StringBuilder ipSb){
        //make sure rem is not empty
        if(index == 4){
            if(pre.length() == ipSb.length() + 4){
                res.add(pre.substring(0, pre.length() - 1));
            }
            return;
        }

        //有一个可以被挪
        if(offset < ipSb.length()){
            dfs2(pre.append(ipSb.charAt(offset)).append('.'), index + 1, offset + 1, res, ipSb);
            pre.delete(pre.length() - 2, pre.length());
        }

        //有两个可以挪
        if(offset + 1 < ipSb.length()){
            char c = ipSb.charAt(offset);
            if(c != '0'){
                dfs2(pre.append(ipSb.substring(offset, offset + 2)).append('.'), index + 1, offset + 2, res, ipSb);
                pre.delete(pre.length() - 3, pre.length());
            }

        }

        //有三个可以挪
        if(offset + 2 < ipSb.length()){
            String str = ipSb.substring(offset, offset + 3);
            char c = ipSb.charAt(offset);
            if(c != '0' && Integer.valueOf(str) <= 255){
                dfs2(pre.append(str).append('.'), index + 1, offset + 3, res, ipSb);
                pre.delete(pre.length() - 4, pre.length());
            }

        }
        return;
    }

}
