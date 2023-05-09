package com.leetcode.study.algorithm.simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuchangcheng
 * @Date 2023/5/8 17:28
 * @Description : 两数之和
 */
public class SumOfTwoNum {

    /**
     * 力扣 第1题
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值target的那两个整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现
     *  注意：数组中同一个元素在答案里不能重复出现，也就是相同的元素，数组下标应该不一样
     *  比如[3,3]的下标为0,1
     * 时间复杂度：https://blog.csdn.net/xinhang10/article/details/127574454
     */
    
    /**
     * 采用双for实现  简单粗暴  时间复杂度：O(n^2)
     * 思路：挨个遍历相加
     * @param nums
     * @param target
     * @return
     */
    public static int [] _2for(int [] nums,int target){
        int [] result = new int [2];
        for (int i = 0; i<nums.length;i++){
            for (int j = i+1; j<nums.length;j++){
                if(nums[i]+nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     * 采用哈希表方式，哈希表获取值的时间复杂度为O(1)，最终的时间复杂度为O(2n)
     * 思路：采用俩次迭代，第一次存储值和下标，重复值记录最后一次下标位置
     *      第二次迭代通过差值获取temp中是否存在差值的key，存在则获取下标
     *      由于不能同元素，相同的下标，因此增加temp.get(dif) != i的判断避免此种情况
     * @param nums
     * @param target
     * @return
     */
    public static int [] _2map(int [] nums,int target){
        int [] result = new int [2];
        Map<Integer,Integer> temp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            temp.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            int dif = target - nums[i];
            if(temp.containsKey(dif) && temp.get(dif) != i){
                result[0] = i;
                result[1] = temp.get(dif);
            }
        }
        return result;
    }

    /**
     * 采用哈希表方式，哈希表获取值的时间复杂度为O(1)，最终的时间复杂度为O(n)
     * 思路：采用一次迭代，边插入边比较
     * temp.put(nums[i],i);放在后面，避免了由于不能同元素，相同的下标的情况
     * @param nums
     * @param target
     * @return
     */
    public static int [] _1map(int [] nums,int target){
        int [] result = new int [2];
        Map<Integer,Integer> temp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int dif = target - nums[i];
            if(temp.containsKey(dif)){
                result[0] = i;
                result[1] = temp.get(dif);
            }
            temp.put(nums[i],i);
        }
        return result;
    }
    
    public static void main(String[] args) {
        int target = 6;
        int [] nums = {1,3,3};

        int[] result = _1map(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
