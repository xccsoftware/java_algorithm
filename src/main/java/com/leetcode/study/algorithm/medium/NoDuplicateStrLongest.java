package com.leetcode.study.algorithm.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuchangcheng
 * @Date 2023/5/12 11:18
 * @Description : 无重复字符的最长子串
 */
public class NoDuplicateStrLongest {

    /**
     * 力扣 第3题
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
     */

    /**
     * 采用indexof判断方式  耗时 11s
     * 示例：abcabcbb
     * @param s
     * @return
     */
    public static int useIndexOf(String s){
        int maxLen = 0;
        if(!"".equals(s)){
            String s1 = s.substring(0,1); // 第一个字符串 a
            maxLen = s1.length();
            for (int i = 1; i < s.length(); i++) {
                char c = s.charAt(i);
                int index = s1.indexOf(c);
                if(index == -1){
                    s1 = s1 + c;
                    int length = s1.length();
                    if(length > maxLen){
                        maxLen = length;
                    }
                }else{
                    int length = s1.length();
                    if(length > maxLen){
                        maxLen = length;
                    }
                    s1 = s1.substring( (index + 1) ) + c;
                }
            }
        }
        return maxLen;
    }

    public static int lengthOfLongestSubstring(String s) {
        /**
         * 分为2个坐标（start,end）
         * end一直递增，当碰见重复的字符时，修改start的起始位置，end继续递增，直到末尾
         */
        int le = s.length();
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        // end从坐标0开始，逐渐递增
        for (int end = 0; end < le; end++) {
            char c = s.charAt(end);
            // 判断否包含重复字符串，如果包含，改变start的起始位置
            if (map.containsKey(c)) {
                // 此处用max是避免出现如"abba"这种情况，比如起始位置在第二个b是为2，但是走到第二个a后变成了1，这就是错误的。通过max取出最大值
                start = Math.max(map.get(c),start);
            }
            // 这里为了避免输入" "的情况，需要加1，" "也代表一个字符
            // 使用max是因为办法保证后面的值是不重复的，如"abcabcbb",就是前面不重复，后面重复。在cbb这以后end-start的值会越来越小，所有这块也要取出最大值
            res = Math.max(res,(end - start +1));
            map.put(s.charAt(end), end+1);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "";
        /*int i = lengthOfLongestSubstring(s);
        System.out.println(i)*/;
        int r = useIndexOf(s);
        System.out.println(r);
    }
}
