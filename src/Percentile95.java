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
        //bucket 物理意义：记录每个Length的个数
        //%95 percentile就是当数量是总的url数量的95%时 url长度落在的bucket的index
        int[] bucket = new int[4097];
        //把所有length的distribution分布做出来
        for(int i = 0; i < lengths.size(); i++){
            bucket[lengths.get(i)]++;
        }

        //找%95
        //物理意义: bucket的直方图面积是url个数i.e. lengths.size的95%时候，bucket[i]的值
        int res = 0;
        int area = 0;
        for(int i = 0; i < bucket.length; i++){
            if(area < lengths.size() * 0.95){
                area += bucket[i];
            }else{
                //坑: 第一次做错。i的时候面积不比95%小了意味着index = i - 1时候已经符合了95%的定义 所以res 是i - 1 不是i
                res = i - 1;
                break;
            }

        }
        return res;
    }

    //way 2: TODO: 对比答案做法
}
