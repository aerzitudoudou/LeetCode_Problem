/*
*
* Laicode
* 114. 95 Percentile
Medium
Given a list of integers representing the lengths of urls, find the 95 percentile of all lengths (95% of the urls have lengths <= returned length).

Assumptions

The maximum length of valid url is 4096

The list is not null and is not empty and does not contain null

Examples

[1, 2, 3, ..., 95, 96, 97, 98, 99, 100], 95 percentile of all lengths is 95.
*
*
*
*
* */

import java.util.List;

public class Percentile95 {
    //way 1: my way
    public int percentile95(List<Integer> lengths) {
        //counter 物理意义：记录每个Length的个数
        //%95 percentile就是当数量是总的url数量的95%时 url长度落在的counter的index
        int[] counter = new int[4097];
        //把所有length的distribution分布做出来
        for(int i = 0; i < lengths.size(); i++){
            counter[lengths.get(i)]++;
        }

        //找%95
        //物理意义: counter的直方图面积是url个数i.e. lengths.size的95%时候，counter[i]的值
        int res = 0;
        int area = 0;
        for(int i = 0; i < counter.length; i++){
            if(area < lengths.size() * 0.95){
                area += counter[i];
            }else{
                //坑: 第一次做错。i的时候面积不比95%小了意味着index = i - 1时候已经符合了95%的定义 所以res 是i - 1 不是i
                res = i - 1;
                break;
            }

        }
        return res;
    }

    //way 2: 正着做，算出95%
    public int percentile952(List<Integer> lengths) {

        int[] counter = new int[4097];
        //把所有length的distribution分布做出来
        for(int i = 0; i < lengths.size(); i++){
            counter[lengths.get(i)]++;
        }


        int res = 0;
        int area = 0;
        //more elegant way to handle 边界判断
        while(area < lengths.size() * 0.95 ){
            res++;
            area += counter[res];
        }

        return res;
    }

    //way 3: 返着做，算出5%
    public int percentile953(List<Integer> lengths) {

        int[] counter = new int[4097];
        //把所有length的distribution分布做出来
        for(int i = 0; i < lengths.size(); i++){
            counter[lengths.get(i)]++;
        }

        //找5%
        int res = 4097;
        int area = 0;
        while(area <= lengths.size() * 0.05 ){
            res--;
            area += counter[res];
        }

        return res;
    }
}
