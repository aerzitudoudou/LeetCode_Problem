/*
*
*
*
*laicode
*682. Longest Ascending Subsequence II
Medium
Given an array A[0]...A[n-1] of integers, find out the longest ascending subsequence. If there are multiple results, then return any valid result.

Assumptions

A is not null
Examples
Input: A = {5, 2, 6, 3, 4, 7, 5}
Output: [2,3,4,5]
Because [2, 3, 4, 5] is one of the longest ascending subsequences.
*
*
*
*
*
* */


import java.util.ArrayList;
import java.util.List;

public class LongestAscendingSubsequenceII {
    //way1: T: O(n^2)
    //S: O(n)
    public int[] longest(int[] a) {
        //m[i] 值从[0, i] 包括i the length of longest ascending subsquence
    /*
    index:              0  1  2  3  4  5  6

    原数组：
    a[i]                5  2  6  3  4  7  5
    dp辅助数组：
    m[i]                1  1  2  2  3  4  4
    前序的index：
    pred[i]            -1 -1  1  1  3  4  4

    res 记录m[i] 最大时i的值， 以及最大值的个数(方便开数组记录结果)
    通过i 找pred[i] --> a[i] --> 一路追下去
    */

        if(a == null || a.length == 0){
            return new int[0];
        }
        int[] m = new int[a.length];
        int[] pred = new int[a.length];
        int[] res = new int[2];
        //global max
        res[0] = 0; //longest length's index so far
        res[1] = 1; //longest length so far
        //base case
        m[0] = 1;
        pred[0] = -1;
        for(int i = 1; i < a.length; i++){
            m[i] = 1;
            pred[i] = -1;
            for(int j = i - 1; j >= 0; j--){
                if(a[j] < a[i] && m[j] + 1 > m[i]){
                    m[i] = m[j] + 1;
                    pred[i] = j;
                }
            }
            //update global
            if(m[i] > res[1]){
                res[0] = i;
                res[1] = m[i];
            }
        }

        int length = res[1];
        int index = res[0];
        int[] resAry = new int[length];
        resAry[length - 1] = a[index];
        int predIndex = pred[index];
        for(int i = 1; i < resAry.length; i++){
            resAry[length - 1 - i] = a[predIndex];
            predIndex = pred[predIndex];
        }

        return resAry;

    }



    //way 2: 优化代码，只记录最大值时候的index, post processing 用了arraylist
    //T: O(n ^ 2) S: O(n)
    public int[] longest1(int[] a) {
        //m[i] 值从[0, i] 包括i the length of longest ascending subsquence
    /*
    index:              0  1  2  3  4  5  6

    原数组：
    a[i]                5  2  6  3  4  7  5
    dp辅助数组：
    m[i]                1  1  2  2  3  4  4
    前序的index：
    pred[i]            -1 -1  1  1  3  4  4

    res 记录m[i] 最大时i的值
    通过i 找pred[i] --> a[i] --> 一路追下去
    */

        if(a == null || a.length == 0){
            return new int[0];
        }
        int[] m = new int[a.length];
        int[] pred = new int[a.length];
        int maxIndex = 0; //index for the longest ascending subsequence

        //base case
        m[0] = 1;
        pred[0] = -1;
        for(int i = 1; i < a.length; i++){
            m[i] = 1;
            pred[i] = -1;
            for(int j = i - 1; j >= 0; j--){
                if(a[j] < a[i] && m[j] + 1 > m[i]){
                    m[i] = m[j] + 1;
                    pred[i] = j;
                }
            }
            //update global
            if(m[i] > m[maxIndex]){
                maxIndex = i;
            }
        }

//        int length = m[maxIndex];
//        int[] resAry = new int[length];
//        resAry[length - 1] = a[maxIndex];
//        int predIndex = pred[maxIndex];
//        for(int i = 1; i < resAry.length; i++){
//            resAry[length - 1 - i] = a[predIndex];
//            predIndex = pred[predIndex];
//        }
//
//        return resAry;

        List<Integer> list = new ArrayList();
        while(pred[maxIndex] != -1){
            list.add(a[maxIndex]);
            maxIndex = pred[maxIndex];
        }
        list.add(a[maxIndex]);
        int[] res = new int[list.size()];
        for(int i = list.size() - 1, j = 0; i >= 0 && j < list.size(); i--, j++){
            res[i] = list.get(j);
        }
        return res;

    }




}
